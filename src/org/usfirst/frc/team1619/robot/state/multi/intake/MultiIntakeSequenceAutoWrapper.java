package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;

public class MultiIntakeSequenceAutoWrapper extends MultiSubsystemSequencerStateWrapper<MultiIntakeSequenceAuto> {

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());
		subsystemsIds.add(Subsystems.collector.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiIntakeSequenceAuto createState() {
		return new MultiIntakeSequenceAuto(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
