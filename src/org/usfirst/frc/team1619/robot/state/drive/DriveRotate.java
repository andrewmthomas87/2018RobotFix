package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.MotorProxy;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NavXHeadingSensor;
import org.usfirst.frc.team1619.robot.framework.controller.PID;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class DriveRotate extends State {

	private double heading;
	private MotorProxy driveLeft, driveRight;
	private NavXHeadingSensor headingSensor;
	private PID drivePID;
	private int profileId;

	public DriveRotate(In in, Out out, double heading, int profileId) {

		this.headingSensor = in.get(IO.SENSOR_NAVX_HEADING);
		this.driveLeft = (MotorProxy) out.motors.get(IO.MOTOR_DRIVE_LEFT);
		this.driveRight = (MotorProxy) out.motors.get(IO.MOTOR_DRIVE_RIGHT);
		this.heading = heading;
		this.profileId = profileId;
		this.drivePID = IO.driveRotatePID;
	}

	@Override
	protected void initialize() {
		this.headingSensor.zero();
		this.driveLeft.setMotor(IO.MOTOR_PROXY_DRIVE_LEFT_DEFAULT);
		this.driveRight.setMotor(IO.MOTOR_PROXY_DRIVE_RIGHT_DEFAULT);
		this.drivePID.setProfile(this.profileId);
	}

	@Override
	protected void update() {
		double heading = this.heading;

		double measuredHeading = this.headingSensor.get();

		this.drivePID.set(0);
		double headingError = this.heading - measuredHeading;

		if (headingError < -180) {
			headingError += 360.0;
		} else if (headingError > 180) {
			headingError -= 360.0;
		}

		double headingPIDValue = this.drivePID.get(headingError);

		// TODO: Logic for rotating the correct direction is neccessary. Only turns one
		// way right now.
		this.driveLeft.set(headingPIDValue);
		this.driveRight.set(-headingPIDValue);

	}

	@Override
	protected void dispose() {
	}

}
