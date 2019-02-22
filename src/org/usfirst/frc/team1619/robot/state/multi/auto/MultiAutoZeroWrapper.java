package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;

public class MultiAutoZeroWrapper extends MultiSubsystemSequencerStateWrapper<MultiAutoZero> {

	private double elevatorSetpoint;
	private double armSetpoint;

	private double distance;

	private double elevatorInitSetpoint;
	private double armInitSetpoint;

	public MultiAutoZeroWrapper(double elevatorInitSetpoint, double armInitSetpoint, double distance,
			double elevatorSetpoint, double armSetpoint) {
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;

		this.distance = distance;

		this.elevatorInitSetpoint = elevatorInitSetpoint;
		this.armInitSetpoint = armInitSetpoint;
	}

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiAutoZero createState() {
		return new MultiAutoZero(this.elevatorInitSetpoint, this.armInitSetpoint, this.distance, this.elevatorSetpoint,
				this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
