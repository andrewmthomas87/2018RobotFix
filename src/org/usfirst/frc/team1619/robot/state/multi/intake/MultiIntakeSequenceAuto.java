package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.util.DelayWrapper;
import org.usfirst.frc.team1619.robot.state.multi.ElevatorAndArmQuickSetWrapper;

public class MultiIntakeSequenceAuto extends MultiSubsystemSequencerState {

	public MultiIntakeSequenceAuto(Set<Integer> subsystemIds) {
		super(subsystemIds);
		this.add(new ElevatorAndArmQuickSetWrapper(Constants.ELEVATOR_INTAKE_THRESHOLD, Constants.ARM_INTAKE_THRESHOLD,
				Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_SAFE_POSITION));

		this.add(new DelayWrapper(150));

		this.add(new MultiAutoIntakingWrapper());

	}

	@Override
	protected void dispose() {

		super.dispose();
	}

}