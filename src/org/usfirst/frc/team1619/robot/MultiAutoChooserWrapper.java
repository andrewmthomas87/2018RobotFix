package org.usfirst.frc.team1619.robot;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;

public class MultiAutoChooserWrapper extends MultiSubsystemSequencerStateWrapper<MultiAutoChooser> {

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
	protected MultiAutoChooser createState() {
		return new MultiAutoChooser(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return (RobotState.gameData.length() > 1) && !RobotState.autoDone;
	}

	@Override
	protected boolean isDone() {
		return this.getState().getisStateNull();
	}

}
