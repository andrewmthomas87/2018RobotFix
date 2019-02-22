package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.state.elevatorShift.ElevatorShiftLowGear;

public class MultiDriveZeroThenIdleWrapper extends ParallelStateWrapper<MultiDriveZeroThenIdle> {

	private Wrapper driverState;

	private double elevatorSetpoint;
	private double armSetpoint;

	private double distance;

	private double elevatorInitSetpoint;
	private double armInitSetpoint;

	public MultiDriveZeroThenIdleWrapper(Wrapper driverState, double elevatorInitSetpoint, double armInitSetpoint,
			double distance, double elevatorSetpoint, double armSetpoint) {
		this.driverState = driverState;
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;

		this.distance = distance;

		this.armInitSetpoint = armInitSetpoint;
		this.elevatorInitSetpoint = elevatorInitSetpoint;
	}

	public MultiDriveZeroThenIdleWrapper(Wrapper driverState, double elevatorSetpoint, double armSetpoint) {
		this.driverState = driverState;
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;

		this.distance = 0;

		this.armInitSetpoint = armSetpoint;
		this.elevatorInitSetpoint = elevatorSetpoint;
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
	protected MultiDriveZeroThenIdle createState() {
		return new MultiDriveZeroThenIdle(this.driverState, this.elevatorInitSetpoint, this.armInitSetpoint,
				this.distance, this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}
}
