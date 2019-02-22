package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.TalonSRXEncoderPositionSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ArmZero extends State {

	private Motor arm;

	private TalonSRXEncoderPositionSensor armPositionSensor;

	public ArmZero(In in, Out out) {
		this.arm = out.motors.get(IO.MOTOR_ARM);
		this.armPositionSensor = in.get(IO.SENSOR_ARM_POSITION);

	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {

		this.arm.set(Constants.ARM_ZERO_OUTPUT);
	}
 
	@Override
	protected void dispose() {

		this.armPositionSensor.zero(-105);

	}

}
