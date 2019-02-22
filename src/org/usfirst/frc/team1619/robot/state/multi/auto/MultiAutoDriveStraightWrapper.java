package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;

public class MultiAutoDriveStraightWrapper extends MultiSubsystemSequencerStateWrapper<MultiAutoDriveStraight> {

	private boolean isCenter = false;

	public MultiAutoDriveStraightWrapper(boolean isCenter) {
		this.isCenter = isCenter;
	}

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemIds = new HashSet<>();

		subsystemIds.add(Subsystems.drive.getId());
		subsystemIds.add(Subsystems.elevator.getId());
		subsystemIds.add(Subsystems.arm.getId());
		subsystemIds.add(Subsystems.collector.getId());

		return subsystemIds;
	}

	@Override
	protected MultiAutoDriveStraight createState() {
		return new MultiAutoDriveStraight(this.getSubsystemIds(), this.isCenter);
	}

	@Override
	protected boolean isReady() {
		return !RobotState.autoDone;
	}
}
