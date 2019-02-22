package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.MotorProxy;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.controller.PID;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ArmIdle extends State {
	private PID armPID;
	private MotorProxy armMotor;
	protected double setpoint;

	private NumericSensor operatorRightY;

	public ArmIdle(In in, Out out, double setpoint) {
		this.armMotor = (MotorProxy) out.motors.get(IO.MOTOR_ARM);
		this.armPID = IO.armPID;
		this.setpoint = setpoint;

		this.operatorRightY = in.get(IO.OPERATOR_RIGHT_Y_AXIS);
	}

	@Override
	protected void initialize() {
		this.armMotor.setMotor(IO.MOTOR_PROXY_ARM_POSITION_CONTROLLED);
		this.armPID.setProfile(IO.PROFILE_ARM_POSITION_CUBE);

	}

	@Override
	protected void update() {
		this.setpoint += (this.operatorRightY.get() * Math.abs(this.operatorRightY.get())) / 2.00;
		this.armMotor.set(this.setpoint);
	}

	@Override
	protected void dispose() {
		this.armMotor.setMotor(IO.MOTOR_PROXY_ELEVATOR_DEFAULT);
	}

}
