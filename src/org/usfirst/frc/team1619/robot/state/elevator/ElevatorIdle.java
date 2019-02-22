package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.MotorProxy;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.controller.PID;
import org.usfirst.frc.team1619.robot.framework.state.State;
import org.usfirst.frc.team1619.robot.state.arm.ArmDesiredPostion;

public class ElevatorIdle extends State {

	private PID elevatorPID;
	private MotorProxy elevatorMotor;
	protected double setpoint;
	private NumericSensor armPosition;

	private NumericSensor operatorLeftY;

	public ElevatorIdle(In in, Out out, double setpoint) {
		this.elevatorMotor = (MotorProxy) out.motors.get(IO.MOTOR_ELEVATOR);
		this.elevatorPID = IO.elevatorPID;
		this.setpoint = setpoint;
		this.armPosition = in.get(IO.SENSOR_ARM_POSITION);

		this.operatorLeftY = in.get(IO.OPERATOR_LEFT_Y_AXIS);
	}

	@Override
	protected void initialize() {
		this.elevatorMotor.setMotor(IO.MOTOR_PROXY_ELEVATOR_POSITION_CONTROLLED);
		this.elevatorPID.setProfile(IO.PROFILE_ELEVATOR_POSITION_STAGE_1);

	}

	@Override
	protected void update() {

		this.setpoint += (Math.abs(this.operatorLeftY.get()) * this.operatorLeftY.get()) / 4.00;

		if (this.setpoint >= Constants.ELEVATOR_STAGE_2_HEIGHT) {
			this.setpoint = Constants.ELEVATOR_STAGE_2_HEIGHT;
		} else if (this.setpoint <= 0.0) {
			this.setpoint = 0.0;
		}

		boolean isArmIntakeSide = this.armPosition.get() < Constants.MAX_ARM_POSITION_INTAKE_SIDE_BELOW_STAGE_1;
		boolean isArmRestrictedSide = this.armPosition.get() > Constants.MIN_ARM_POSITION_RESTRICTED_SIDE_BELOW_STAGE_1;
		boolean isTransitioning = !isArmIntakeSide && !isArmRestrictedSide;

		if (this.setpoint < Constants.ELEVATOR_TRANSITION_HEIGHT_MINIMUM) {
			if ((RobotState.armDesiredPosition == ArmDesiredPostion.RESTRICTED && isArmIntakeSide)
					|| (RobotState.armDesiredPosition == ArmDesiredPostion.INTAKE && isArmRestrictedSide)
					|| isTransitioning) {
				this.elevatorMotor.set(Constants.ELEVATOR_TRANSITION_HEIGHT_MINIMUM);
			} else {
				this.elevatorMotor.set(this.setpoint);
			}
		} else {
			this.elevatorMotor.set(this.setpoint);
		}
	}

	@Override
	protected void dispose() {
		this.elevatorMotor.setMotor(IO.MOTOR_PROXY_ELEVATOR_DEFAULT);
	}

}
