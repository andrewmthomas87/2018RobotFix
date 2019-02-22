package org.usfirst.frc.team1619.robot.state.multi;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class MultiDriveTwoSetWrapper extends ParallelStateWrapper<MultiDriveTwoSet> {

	private Wrapper driverState;
	private double elevatorSetpoint;
	private double armSetpoint;

	private double elevatorInitSetpoint;
	private double armInitSetpoint;

	private double distance;

	public MultiDriveTwoSetWrapper(Wrapper driverState, double elevatorInitSetpoint, double armInitSetpoint,
			double distance, double elevatorSetpoint, double armSetpoint) {
		this.driverState = driverState;
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;

		this.elevatorInitSetpoint = elevatorInitSetpoint;
		this.armInitSetpoint = armInitSetpoint;

		this.distance = distance;

		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;
	}

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemIds = new HashSet<>();

		subsystemIds.add(Subsystems.drive.getId());
		subsystemIds.add(Subsystems.elevator.getId());
		subsystemIds.add(Subsystems.arm.getId());

		return subsystemIds;
	}

	@Override
	protected MultiDriveTwoSet createState() {
		return new MultiDriveTwoSet(this.driverState, this.elevatorInitSetpoint, this.armInitSetpoint, this.distance,
				this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
