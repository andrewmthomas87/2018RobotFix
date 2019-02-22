package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerAxisSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class DriveSetVoltage extends State {

	private ControllerAxisSensor yAxis;
	private ControllerAxisSensor xAxis;
	private double voltage;

	private Motor driveLeft, driveRight;

	public DriveSetVoltage(In in, Out out, double voltage) {
		this.yAxis = in.get(IO.DRIVER_LEFT_AXIS_Y);
		this.xAxis = in.get(IO.DRIVER_RIGHT_AXIS_X);

		this.voltage = voltage;

		this.driveLeft = out.motors.get(IO.MOTOR_DRIVE_LEFT);
		this.driveRight = out.motors.get(IO.MOTOR_DRIVE_RIGHT);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {

		this.driveRight.set(this.voltage);
		this.driveLeft.set(this.voltage);

	}

	@Override
	protected void dispose() {

	}
}
