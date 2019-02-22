package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;
import org.usfirst.frc.team1619.robot.framework.state.SequencerState;
import org.usfirst.frc.team1619.robot.framework.state.SequencerStateWrapper;

public class DriveSetVoltageAfterPauseWrapper extends SequencerStateWrapper<DriveSetVoltageAfterPause> {
	private long timeout;
	private double voltage;
	private int driveTime;
	
	public DriveSetVoltageAfterPauseWrapper(long timeout, double voltage, int driveTime) {
		this.timeout = timeout;
		this.voltage = voltage;
		this.driveTime = driveTime;
	}

	@Override
	protected SequencerState createState() {
		return new DriveSetVoltageAfterPause(this.timeout, this.voltage, this.driveTime);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return Subsystems.drive.getId() == subsystemId;
	}

}
