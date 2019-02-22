package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class CollectorHoldAutoWrapper extends Wrapper<CollectorHoldAuto> {

	@Override
	protected CollectorHoldAuto createState() {
		return new CollectorHoldAuto(in, out);
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
		return subsystemId == Subsystems.collector.getId();
	}

}
