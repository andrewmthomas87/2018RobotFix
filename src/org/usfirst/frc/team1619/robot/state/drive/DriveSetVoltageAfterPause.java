package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.framework.state.SequencerState;
import org.usfirst.frc.team1619.robot.framework.util.DelayWrapper;

public class DriveSetVoltageAfterPause extends SequencerState {
	public DriveSetVoltageAfterPause(long timeout, double voltage, int driveTime) {
		this.add(new DelayWrapper(timeout));
		this.add(new DriveSetVoltageWrapper(voltage, driveTime));
	}
}
