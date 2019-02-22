package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class DriveRotateWrapper extends Wrapper<DriveRotate> {

	private Timer timer = new Timer();

	private double heading;
	private int profileId;
	private double headingErrorThreshold;
	private int thresholdTimeout;
	private NumericSensor headingSensor = in.get(IO.SENSOR_NAVX_HEADING);

	public DriveRotateWrapper(double heading, int profileId, double headingErrorThreshold, int thresholdTimeout) {
		this.heading = heading;
		this.profileId = profileId;
		this.headingErrorThreshold = headingErrorThreshold;
		this.thresholdTimeout = thresholdTimeout;
	}

	@Override
	protected DriveRotate createState() {
		this.timer.reset();
		return new DriveRotate(in, out, this.heading, this.profileId);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {

		double headingError = this.heading - this.headingSensor.get();

		if (headingError < -180) {
			headingError += 360.0;
		} else if (headingError > 180) {
			headingError -= 360.0;
		}

		if (Math.abs(headingError) <= this.headingErrorThreshold) {
			if (!timer.isStarted()) {
				timer.start(this.thresholdTimeout);
			}
		} else if (timer.isStarted()) {
			timer.reset();
		}

		return timer.isDone();

	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.drive.getId();
	}

}
