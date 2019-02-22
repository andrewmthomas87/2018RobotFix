package org.usfirst.frc.team1619.robot;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;

public class MultiAutoChooserCubeHoldWrapper extends ParallelStateWrapper<MultiAutoChooserCubeHold> {

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemIds = new HashSet<>();

		subsystemIds.add(Subsystems.arm.getId());
		subsystemIds.add(Subsystems.collector.getId());
		subsystemIds.add(Subsystems.drive.getId());
		subsystemIds.add(Subsystems.elevator.getId());

		return subsystemIds;
	}

	@Override
	protected MultiAutoChooserCubeHold createState() {
		return new MultiAutoChooserCubeHold(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
