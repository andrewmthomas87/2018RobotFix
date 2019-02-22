package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.state.multi.intake.MultiIntakeSequenceAutoWrapper;

public class MultiDriveAndIntakeWrapper extends ParallelStateWrapper<MultiDriveAndIntake> {

	private Wrapper driverState;
	private Wrapper multiIntakeSequenceAutoWrapper = new MultiIntakeSequenceAutoWrapper();

	public MultiDriveAndIntakeWrapper(Wrapper driverState) {
		this.driverState = driverState;
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
	protected MultiDriveAndIntake createState() {
		return new MultiDriveAndIntake(this.driverState, this.multiIntakeSequenceAutoWrapper, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	protected boolean isDone() {
		return this.driverState.isDoneState() || this.multiIntakeSequenceAutoWrapper.isDoneState();
	};
}