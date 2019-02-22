package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class DriveManualWrapper extends Wrapper<DriveManual> {

	@Override
	protected DriveManual createState() {
		return new DriveManual(in, out);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		return false;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.drive.getId();
	}
}
