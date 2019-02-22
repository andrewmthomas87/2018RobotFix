package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class MultiDriveWrapper extends ParallelStateWrapper<MultiDrive> {

	private Wrapper driverState;
	private double elevatorSetpoint;
	private double armSetpoint;

	public MultiDriveWrapper(Wrapper driverState, double elevatorSetpoint, double armSetpoint) {
		this.driverState = driverState;
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
	protected MultiDrive createState() {
		return new MultiDrive(this.driverState, this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

}
