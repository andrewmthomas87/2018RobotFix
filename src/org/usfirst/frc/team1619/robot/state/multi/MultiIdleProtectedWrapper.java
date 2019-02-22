package org.usfirst.frc.team1619.robot.state.multi;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;

public class MultiIdleProtectedWrapper extends ParallelStateWrapper<MultiIdleProtected> {

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiIdleProtected createState() {
		return new MultiIdleProtected(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
