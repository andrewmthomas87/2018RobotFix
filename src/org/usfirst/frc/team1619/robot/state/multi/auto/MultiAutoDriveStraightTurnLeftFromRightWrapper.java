package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;

public class MultiAutoDriveStraightTurnLeftFromRightWrapper extends MultiSubsystemSequencerStateWrapper<MultiAutoDriveStraightTurnLeftFromRight>{

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
	protected MultiAutoDriveStraightTurnLeftFromRight createState() {
		return new MultiAutoDriveStraightTurnLeftFromRight(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return !RobotState.autoDone;
	}

}
