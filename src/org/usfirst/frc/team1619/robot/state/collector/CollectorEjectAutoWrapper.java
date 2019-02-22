package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;
import org.usfirst.frc.team1619.robot.misc.EjectSpeedProfile;

public class CollectorEjectAutoWrapper extends Wrapper<CollectorEject> {

	private EjectSpeedProfile ejectSpeedProfile;

	public CollectorEjectAutoWrapper(EjectSpeedProfile ejectSpeedProfile) {
		this.ejectSpeedProfile = ejectSpeedProfile;
	}

	public CollectorEjectAutoWrapper() {
		this.ejectSpeedProfile = Constants.TELEOP_EJECT_PROFILE;
	}

	Timer timer = new Timer();

	@Override
	protected CollectorEject createState() {
		return new CollectorEject(in, out, this.ejectSpeedProfile);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		if (!this.timer.isStarted()) {
			this.timer.start(Constants.EJECT_TIME);
		}

		return timer.isDone();
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.collector.getId();
	}

}
