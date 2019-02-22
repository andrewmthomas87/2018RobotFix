package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.state.multi.intake.MultiIntakeSequenceAutoWrapper;

public class MultiDriveAndIntake extends ParallelState {

	public MultiDriveAndIntake(Wrapper driveState, Wrapper multiIntakeSequenceAutoWrapper, Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(multiIntakeSequenceAutoWrapper);
		this.addState(driveState);
	}

}
