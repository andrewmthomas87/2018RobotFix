package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class DriveSetVoltageWrapper extends Wrapper<DriveSetVoltage> {

	private int time;
	private double voltage;
	private Timer timer = new Timer();

	public DriveSetVoltageWrapper(double voltage, int time) {
		this.time = time;
		this.voltage = voltage;
	}

	@Override
	protected DriveSetVoltage createState() {
		System.out.println("DriveSetVoltage");
		return new DriveSetVoltage(in, out, this.voltage);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		if (time == -1) {
			return false;
		} else {
			if (!this.timer.isStarted()) {
				this.timer.start(this.time);
			}

			return timer.isDone();
		}
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.drive.getId();
	}
}