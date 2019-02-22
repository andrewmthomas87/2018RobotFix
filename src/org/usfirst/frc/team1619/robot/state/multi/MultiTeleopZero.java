package org.usfirst.frc.team1619.robot.state.multi;

import java.util.Set;
import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiElevatorAndArmSetWrapper;

public class MultiTeleopZero extends MultiSubsystemSequencerState {
	public MultiTeleopZero(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(new MultiZeroWrapper());
		this.add(
				new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
	}
}