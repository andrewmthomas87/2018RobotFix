package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;

public class ElevatorAndArmToPreIntakeWrapper extends ParallelStateWrapper<ElevatorAndArmToPreIntake> {

	@Override
	protected Set getSubsystemIds() {
		Set<Integer> subsystemIds = new HashSet<>();

		subsystemIds.add(Subsystems.elevator.getId());
		subsystemIds.add(Subsystems.arm.getId());

		return subsystemIds;
	}

	@Override
	protected ElevatorAndArmToPreIntake createState() {
		return new ElevatorAndArmToPreIntake(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}
	
	@Override
	protected boolean isDone() {
		return super.isDone() || RobotState.cancelZero;
	}

}
