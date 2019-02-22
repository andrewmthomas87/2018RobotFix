package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerAxisSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.controller.PID;
import org.usfirst.frc.team1619.robot.framework.state.State;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveManual extends State {

	private NumericSensor currentSensor;

	private ControllerAxisSensor yAxis;
	private ControllerAxisSensor xAxis;

	private NumericSensor elevatorPosition;
	private BooleanSensor driverLeftTrigger;
	private BooleanSensor driverRightTrigger;

	private Motor driveLeft, driveRight;

	private PID currentLimiter;

	public DriveManual(In in, Out out) {
		this.yAxis = in.get(IO.DRIVER_LEFT_AXIS_Y);
		this.xAxis = in.get(IO.DRIVER_RIGHT_AXIS_X);

		this.driverLeftTrigger = in.get(IO.DRIVER_LEFT_TRIGGER);
		this.driverRightTrigger = in.get(IO.DRIVER_RIGHT_TRIGGER);

		this.driveLeft = out.motors.get(IO.MOTOR_DRIVE_LEFT);
		this.driveRight = out.motors.get(IO.MOTOR_DRIVE_RIGHT);

		this.elevatorPosition = in.get(IO.SENSOR_ELEVATOR_POSITION);

		this.currentSensor = in.get(IO.SENSOR_DRIVE_CURRENT);
		this.currentLimiter = IO.currentLimiter;
	}

	@Override
	protected void initialize() {
		this.currentLimiter.setProfile(IO.PROFILE_DRIVE_CURRENT_LIMTING);
	}

	@Override
	protected void update() {

		double elevatorMultiplierY = -0.0101 * this.elevatorPosition.get() + 1.00;
		double elevatorMultiplierX = -0.0072 * this.elevatorPosition.get() + 1.00;

		double yAxis = 0.0;
		double xAxis = 0.0;

		if (this.driverLeftTrigger.get()) {
			yAxis = elevatorMultiplierY * (((Math.abs(this.yAxis.get()) * this.yAxis.get())) / 2.0);

			xAxis = elevatorMultiplierX * (((Math.abs(this.xAxis.get()) * this.xAxis.get())) / 2.0);
		} else {
			yAxis = elevatorMultiplierY * this.yAxis.get();
			xAxis = elevatorMultiplierX * this.xAxis.get();
		}

		double right = yAxis + xAxis;
		double left = yAxis - xAxis;

		// Limiting drivetrain sides to 1.0 voltage output
		if (right < -1.0) {
			right = -1.0;
		} else if (right > 1.0) {
			right = 1.0;
		}

		if (left < -1.0) {
			left = -1.0;
		} else if (left > 1.0) {
			left = 1.0;
		}

		// double outputAdjustment = 0.0;
		//
		// if (!this.driverRightTrigger.get()) {
		//
		// this.currentLimiter.set(Constants.DRIVE_CURRENT_THRESHOLD);
		// if ((right < 0.25 && right > -0.25) && (left < 0.25 && left > -0.25)) {
		// this.currentLimiter.integral = 0.0;
		// }
		//
		// outputAdjustment = this.currentLimiter.get(this.currentSensor.get());
		// }
		//
		// if (right < 0) {
		// right -= outputAdjustment;
		// } else {
		// right += outputAdjustment;
		// }
		//
		// if (left < 0) {
		// left -= outputAdjustment;
		// } else {
		// right += outputAdjustment;
		// }

		this.driveRight.set(right);
		this.driveLeft.set(left);

	}

	@Override
	protected void dispose() {

	}
}
