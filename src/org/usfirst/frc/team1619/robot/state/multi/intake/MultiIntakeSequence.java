package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.util.DelayWrapper;
import org.usfirst.frc.team1619.robot.state.multi.ElevatorAndArmQuickSetWrapper;
import org.usfirst.frc.team1619.robot.state.multi.ElevatorAndArmToProtectedWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiElevatorAndArmSetWrapper;

public class MultiIntakeSequence extends MultiSubsystemSequencerState {

	public MultiIntakeSequence(Set<Integer> subsystemIds) {
		super(subsystemIds);
		this.add(new ElevatorAndArmQuickSetWrapper(Constants.ELEVATOR_INTAKE_THRESHOLD, Constants.ARM_INTAKE_THRESHOLD,
				Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_SAFE_POSITION));

		this.add(new DelayWrapper(100));

		this.add(new MultiIntakingWrapper());
		this.add(new ElevatorAndArmToProtectedWrapper());
	}

	@Override
	protected void dispose() {

		super.dispose();
	}

}