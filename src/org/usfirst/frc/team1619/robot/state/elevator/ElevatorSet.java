package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.MotorProxy;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.controller.PID;
import org.usfirst.frc.team1619.robot.framework.state.State;
import org.usfirst.frc.team1619.robot.state.arm.ArmDesiredPostion;

public class ElevatorSet extends State {

	private PID elevatorPID;
	private MotorProxy elevatorMotor;

	private BooleanSensor cubeSensor;

	private NumericSensor elevatorPosition;
	private NumericSensor armPosition;

	private double setpoint;

	public ElevatorSet(In in, Out out, double setpoint) {
		this.elevatorMotor = (MotorProxy) out.motors.get(IO.MOTOR_ELEVATOR);
		this.elevatorPID = IO.elevatorPID;
		this.setpoint = setpoint;

		this.elevatorPosition = in.get(IO.SENSOR_ELEVATOR_POSITION);
		this.cubeSensor = in.get(IO.CUBE_SENSOR);

		this.armPosition = in.get(IO.SENSOR_ARM_POSITION);

	}

	@Override
	protected void initialize() {
		RobotState.elevatorSetpoint = this.setpoint;
		this.elevatorMotor.setMotor(IO.MOTOR_PROXY_ELEVATOR_POSITION_CONTROLLED);
		this.elevatorPID.setProfile(IO.PROFILE_ELEVATOR_POSITION_STAGE_1);

	}

	@Override
	protected void update() {

		// if (this.elevatorPosition.get() <= Constants.ELEVATOR_STAGE_1_HEIGHT) {
		// if (RobotState.isCubeAcquired) {
		// this.elevatorPID.setProfile(IO.PROFILE_ELEVATOR_POSITION_STAGE_1);
		// } else {
		// this.elevatorPID.setProfile(IO.PROFILE_ELEVATOR_POSITION_STAGE_1_WITH_CUBE);
		// }
		//
		// this.elevatorPID.setProfile(IO.PROFILE_ELEVATOR_POSITION_STAGE_1);
		//
		// } else {
		// if (RobotState.isCubeAcquired) {
		// this.elevatorPID.setProfile(IO.PROFILE_ELEVATOR_POSITION_STAGE_2);
		// } else {
		// this.elevatorPID.setProfile(IO.PROFILE_ELEVATOR_POSITION_STAGE_1_WITH_CUBE);
		// }
		//
		// this.elevatorPID.setProfile(IO.PROFILE_ELEVATOR_POSITION_STAGE_2);
		// }

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
