package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ArmTeleopControl extends State {

	private BooleanSensor scaleHigh;
	private BooleanSensor exchange;
	private BooleanSensor scaleNuetral;
	private BooleanSensor switchButton;
	private BooleanSensor modifierButton;
	private BooleanSensor cancelButton;
	private BooleanSensor climbButton;

	private NumericSensor armPositionSensor;

	private boolean isIdling = true;

	private ArmSetWrapper armSetWrapper = new ArmSetWrapper(-1, Constants.ARM_ACCEPTABLE_RANGE,
			Constants.TIME_IN_ACCEPTABLE_RADIUS_PID);
	private ArmIdleWrapper armIdleWrapper = new ArmIdleWrapper(-1);

	public ArmTeleopControl(In in, Out out) {
		this.scaleHigh = in.get(IO.OPERATOR_BUTTON_Y);
		this.exchange = in.get(IO.OPERATOR_BUTTON_A);
		this.scaleNuetral = in.get(IO.OPERATOR_BUTTON_X);
		this.switchButton = in.get(IO.OPERATOR_BUTTON_B);
		this.modifierButton = in.get(IO.OPERATOR_BUTTON_LEFT_BUMPER);
		this.cancelButton = in.get(IO.OPERATOR_BUTTON_RIGHT_BUMPER);
		this.climbButton = in.get(IO.OPERATOR_BUTTON_DPAD_UP);

		this.armPositionSensor = in.get(IO.SENSOR_ARM_POSITION);
	}

	@Override
	protected void initialize() {
		double currentPosition = this.armPositionSensor.get();

		this.armIdleWrapper.setSetpoint(currentPosition);

		RobotState.cancelZero = false;
		this.armIdleWrapper.prepareState();
		this.armIdleWrapper.getState().initializeState(Subsystems.arm.getId());

	}

	@Override
	protected void update() {

		double newSetpoint = -1;
		if (this.scaleHigh.get() && this.modifierButton.get()) {
			newSetpoint = Constants.ARM_SCALE_HIGH_BACK;
		} else if (this.scaleHigh.get()) {
			newSetpoint = Constants.ARM_SCALE_HIGH_FRONT;
		} else if (this.scaleNuetral.get() && this.modifierButton.get()) {
			newSetpoint = Constants.ARM_SCALE_NUETRAL_BACK;
		} else if (this.scaleNuetral.get()) {
			newSetpoint = Constants.ARM_SCALE_NUETRAL_FRONT;
		} else if (this.switchButton.get() && this.modifierButton.get()) {
			newSetpoint = Constants.ARM_SWITCH_BACK;
		} else if (this.switchButton.get()) {
			newSetpoint = Constants.ARM_SWITCH_FRONT;
		} else if (this.cancelButton.get()) {
			newSetpoint = Constants.ARM_PROTECTED_POSITION;
		} else if (this.exchange.get()) {
			newSetpoint = Constants.ARM_EXCHANGE_HEIGHT;
		} else if (this.climbButton.get()) {
			newSetpoint = Constants.ARM_PROTECTED_POSITION;
		}

		if (newSetpoint != -1) {
			if (this.isIdling) {
				this.armIdleWrapper.getState().disposeState();
			} else {
				this.armSetWrapper.getState().disposeState();
			}

			this.isIdling = false;
			this.armSetWrapper.setSetpoint(newSetpoint);
			this.armSetWrapper.prepareState();
			this.armSetWrapper.getState().initializeState(Subsystems.elevator.getId());

		}

		if (!isIdling && this.armSetWrapper.isDoneState()) {
			this.armSetWrapper.getState().disposeState();
			this.armIdleWrapper.setSetpoint(this.armSetWrapper.getSetpoint());
			this.armIdleWrapper.prepareState();
			this.armIdleWrapper.getState().initializeState(Subsystems.elevator.getId());
			this.isIdling = true;
		}

		if (this.isIdling) {
			this.armIdleWrapper.getState().updateState(Subsystems.elevator.getId());
		} else {
			this.armSetWrapper.getState().updateState(Subsystems.elevator.getId());
		}

	}

	@Override
	protected void dispose() {
		if (this.isIdling) {
			this.armIdleWrapper.getState().disposeState();
		} else {
			this.armSetWrapper.getState().disposeState();
		}

	}

}
