package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ElevatorTeleopControl extends State {

	private BooleanSensor scaleHighButton;
	private BooleanSensor scaleNuetralButton;
	private BooleanSensor exchange;
	private BooleanSensor switchButton;
	private BooleanSensor cancelButton;
	private BooleanSensor climbButton;

	private NumericSensor elevatorPositionSensor;

	private double previousSetpoint;

	private boolean isIdling = true;

	private ElevatorSetWrapper elevatorSetWrapper = new ElevatorSetWrapper(-1, Constants.ELEVATOR_ACCEPTABLE_RANGE,
			Constants.TIME_IN_ACCEPTABLE_RADIUS_PID);

	private ElevatorIdleWrapper elevatorIdleWrapper = new ElevatorIdleWrapper(-1);

	public ElevatorTeleopControl(In in, Out out) {
		this.scaleHighButton = in.get(IO.OPERATOR_BUTTON_Y);
		this.scaleNuetralButton = in.get(IO.OPERATOR_BUTTON_X);
		this.exchange = in.get(IO.OPERATOR_BUTTON_A);
		this.switchButton = in.get(IO.OPERATOR_BUTTON_B);
		this.cancelButton = in.get(IO.OPERATOR_BUTTON_RIGHT_BUMPER);
		this.climbButton = in.get(IO.OPERATOR_BUTTON_DPAD_UP);

		this.elevatorPositionSensor = in.get(IO.SENSOR_ELEVATOR_POSITION);

	}

	@Override
	protected void initialize() {
		RobotState.cancelZero = false;
		double currentPosition = this.elevatorPositionSensor.get();

		this.elevatorIdleWrapper.setSetpoint(currentPosition);

		RobotState.cancelZero = false;
		this.elevatorIdleWrapper.prepareState();
		this.elevatorIdleWrapper.getState().initializeState(Subsystems.elevator.getId());
	}

	@Override
	protected void update() {
		double newSetpoint = -1;
		if (scaleHighButton.get()) {
			newSetpoint = Constants.ELEVATOR_SCALE_HIGH_POSITION;
		} else if (this.scaleNuetralButton.get()) {
			newSetpoint = Constants.ELEVATOR_SCALE_NUETRAL_POSITION;
		} else if (this.exchange.get()) {
			newSetpoint = Constants.ELEVATOR_EXCHANGE_POSITION;
		} else if (this.switchButton.get()) {
			newSetpoint = Constants.ELEVATOR_SWITCH_POSITION;
		} else if (this.cancelButton.get()) {
			newSetpoint = Constants.ELEVATOR_FLOOR_POSITION;
		} else if (this.climbButton.get()) {
			newSetpoint = Constants.ELEVATOR_STAGE_2_HEIGHT;
		}

		if (newSetpoint != -1) {
			if (this.isIdling) {
				this.elevatorIdleWrapper.getState().disposeState();
			} else {
				this.elevatorSetWrapper.getState().disposeState();
			}

			this.isIdling = false;
			this.elevatorSetWrapper.setSetpoint(newSetpoint);
			this.elevatorSetWrapper.prepareState();
			this.elevatorSetWrapper.getState().initializeState(Subsystems.elevator.getId());

		}

		if (!isIdling && this.elevatorSetWrapper.isDoneState()) {
			this.elevatorSetWrapper.getState().disposeState();
			this.elevatorIdleWrapper.setSetpoint(this.elevatorSetWrapper.getSetpoint());
			this.elevatorIdleWrapper.prepareState();
			this.elevatorIdleWrapper.getState().initializeState(Subsystems.elevator.getId());
			this.isIdling = true;
		}

		if (this.isIdling) {
			this.elevatorIdleWrapper.getState().updateState(Subsystems.elevator.getId());
		} else {
			this.elevatorSetWrapper.getState().updateState(Subsystems.elevator.getId());
		}

	}

	@Override
	protected void dispose() {
		if (this.isIdling) {
			this.elevatorIdleWrapper.getState().disposeState();
		} else {
			this.elevatorSetWrapper.getState().disposeState();
		}
	}

}
