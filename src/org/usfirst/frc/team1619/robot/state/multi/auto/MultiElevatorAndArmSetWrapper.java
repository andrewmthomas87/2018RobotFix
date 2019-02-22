package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;

public class MultiElevatorAndArmSetWrapper extends ParallelStateWrapper<MultiElevatorAndArmSet> {

	private double elevatorSetpoint;
	private double armSetpoint;

	public MultiElevatorAndArmSetWrapper(double elevatorSetpoint, double armSetpoint) {
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;
	}

	@Override
	protected Set getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiElevatorAndArmSet createState() {
		return new MultiElevatorAndArmSet(this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
