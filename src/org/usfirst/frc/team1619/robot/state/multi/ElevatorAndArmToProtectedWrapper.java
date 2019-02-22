package org.usfirst.frc.team1619.robot.state.multi;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;

public class ElevatorAndArmToProtectedWrapper extends ParallelStateWrapper<ElevatorAndArmToProtected> {

	@Override
	protected Set getSubsystemIds() {
		Set<Integer> subsystemIds = new HashSet<>();

		subsystemIds.add(Subsystems.elevator.getId());
		subsystemIds.add(Subsystems.arm.getId());

		return subsystemIds;
	}

	@Override
	protected ElevatorAndArmToProtected createState() {
		return new ElevatorAndArmToProtected(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
