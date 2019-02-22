package org.usfirst.frc.team1619.robot.state.multi;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;
import org.usfirst.frc.team1619.robot.state.multi.MultiElevatorAndArmTwoSet;

public class MultiElevatorAndArmTwoSetWrapper extends MultiSubsystemSequencerStateWrapper<MultiElevatorAndArmTwoSet> {

	private double elevatorInitSetpoint;
	private double armInitSetpoint;

	private double distance;

	private double elevatorSetpoint;
	private double armSetpoint;

	public MultiElevatorAndArmTwoSetWrapper(double elevatoInitSetpoint, double armInitSetpoint, double distance,
			double elevatorSetpoint, double armSetpoint) {

		this.elevatorInitSetpoint = elevatoInitSetpoint;
		this.armInitSetpoint = armInitSetpoint;

		this.distance = distance;

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
	protected MultiElevatorAndArmTwoSet createState() {
		return new MultiElevatorAndArmTwoSet(this.elevatorInitSetpoint, this.armInitSetpoint, this.distance,
				this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
