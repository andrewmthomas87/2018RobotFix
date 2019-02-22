package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ArmSetVoltage extends State {

	private Motor armMotor;
	private double voltage;

	public ArmSetVoltage(double voltage, In in, Out out) {
		this.armMotor = out.motors.get(IO.MOTOR_ARM);
		this.voltage = voltage;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void update() {
		this.armMotor.set(this.voltage);
	}

	@Override
	protected void dispose() {

	}

}