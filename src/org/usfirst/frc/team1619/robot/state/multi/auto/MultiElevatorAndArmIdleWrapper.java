package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;

public class MultiElevatorAndArmIdleWrapper extends ParallelStateWrapper<MultiElevatorAndArmIdle> {

	private double elevatorSetpoint;
	private double armSetpoint;

	public MultiElevatorAndArmIdleWrapper(double elevatorSetpoint, double armSetpoint) {
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;
	}

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiElevatorAndArmIdle createState() {
		return new MultiElevatorAndArmIdle(this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
