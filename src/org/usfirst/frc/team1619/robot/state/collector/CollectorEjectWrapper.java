package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerButtonSensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.misc.EjectSpeedProfile;

public class CollectorEjectWrapper extends Wrapper<CollectorEject> {

	private ControllerButtonSensor triggerRight = in.get(IO.OPERATOR_RIGHT_TRIGGER);

	private EjectSpeedProfile ejectSpeedProfile;

	public CollectorEjectWrapper(EjectSpeedProfile ejectSpeedProfile) {
		this.ejectSpeedProfile = ejectSpeedProfile;
	}

	public CollectorEjectWrapper() {
		this.ejectSpeedProfile = Constants.TELEOP_EJECT_PROFILE;
	}

	@Override
	protected CollectorEject createState() {
		return new CollectorEject(in, out, this.ejectSpeedProfile);
	}

	@Override
	protected boolean isReady() {
		return (this.triggerRight.get());
	}

	@Override
	protected boolean isDone() {
		return (!this.triggerRight.get());
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.collector.getId();
	}
}
