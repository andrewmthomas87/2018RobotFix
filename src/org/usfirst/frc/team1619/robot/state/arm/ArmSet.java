package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.MotorProxy;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.controller.PID;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ArmSet extends State {

	private PID armPID;
	private MotorProxy armMotor;

	private NumericSensor armPosition;

	private NumericSensor elevatorPosition;

	private double setpoint;

	public ArmSet(In in, Out out, double setpoint) {
		this.armMotor = (MotorProxy) out.motors.get(IO.MOTOR_ARM);
		this.armPID = IO.armPID;
		this.setpoint = setpoint;

		this.armPosition = in.get(IO.SENSOR_ARM_POSITION);
		this.elevatorPosition = in.get(IO.SENSOR_ELEVATOR_POSITION);

	}

	@Override
	protected void initialize() {
		this.armMotor.setMotor(IO.MOTOR_PROXY_ARM_POSITION_CONTROLLED);
		// f term for idle only?
		this.armPID.setProfile(IO.PROFILE_ARM_POSITION_NO_CUBE);

		if (this.setpoint < Constants.MAX_ARM_POSITION_INTAKE_SIDE_BELOW_STAGE_1) {
			RobotState.armDesiredPosition = ArmDesiredPostion.INTAKE;
		} else if (this.setpoint > Constants.MIN_ARM_POSITION_RESTRICTED_SIDE_BELOW_STAGE_1) {
			RobotState.armDesiredPosition = ArmDesiredPostion.RESTRICTED;
		}
	}

	@Override
	protected void update() {

		if (!RobotState.isCubeAcquired) {
			this.armPID.setProfile(IO.PROFILE_ARM_POSITION_NO_CUBE);
		} else {
			this.armPID.setProfile(IO.PROFILE_ARM_POSITION_CUBE);
		}

		boolean isArmIntakeSide = this.armPosition.get() < Constants.MAX_ARM_POSITION_INTAKE_SIDE_BELOW_STAGE_1;
		boolean isArmRestrictedSide = this.armPosition.get() > Constants.MIN_ARM_POSITION_RESTRICTED_SIDE_BELOW_STAGE_1;

		if (this.elevatorPosition.get() < Constants.ELEVATOR_ARM_TRANSITION_HEIGHT_MINIMUM) {
			if (isArmIntakeSide && this.setpoint > Constants.MAX_ARM_POSITION_INTAKE_SIDE_BELOW_STAGE_1) {
				this.armMotor.set(Constants.MAX_ARM_POSITION_INTAKE_SIDE_BELOW_STAGE_1);
			} else if (isArmRestrictedSide
					&& this.setpoint < Constants.MIN_ARM_POSITION_RESTRICTED_SIDE_BELOW_STAGE_1) {
				this.armMotor.set(Constants.MIN_ARM_POSITION_RESTRICTED_SIDE_BELOW_STAGE_1);
			} else {
				this.armMotor.set(setpoint);
			}

		} else {
			this.armMotor.set(setpoint);
		}

	}

	@Override
	protected void dispose() {
		RobotState.armDesiredPosition = ArmDesiredPostion.NONE;
		this.armMotor.setMotor(IO.MOTOR_PROXY_ARM_DEFAULT);
	}

}
