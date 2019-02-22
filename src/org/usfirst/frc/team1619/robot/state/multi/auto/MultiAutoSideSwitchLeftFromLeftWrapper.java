package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;

public class MultiAutoSideSwitchLeftFromLeftWrapper extends MultiSubsystemSequencerStateWrapper<MultiAutoSideSwitchLeftFromLeft> {

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemIds = new HashSet<>();
		
		subsystemIds.add(Subsystems.drive.getId());
		subsystemIds.add(Subsystems.elevator.getId());
		subsystemIds.add(Subsystems.arm.getId());
		subsystemIds.add(Subsystems.collector.getId());
		
		return null;
	}

	@Override
	protected MultiAutoSideSwitchLeftFromLeft createState() {
		return new MultiAutoSideSwitchLeftFromLeft(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

}
