package org.usfirst.frc.team1619.robot.state.multi;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.state.arm.ArmSetWrapper;
import org.usfirst.frc.team1619.robot.state.arm.ArmZeroWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorZeroWrapper;

public class MultiZero extends MultiSubsystemSequencerState {

	public MultiZero(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(new ElevatorZeroWrapper());
		this.add(new ArmZeroWrapper());
		this.add(new ArmSetWrapper(Constants.ARM_PROTECTED_POSITION, Constants.ARM_ACCEPTABLE_RANGE,
				Constants.TIME_IN_ACCEPTABLE_RADIUS_PID));
	}

	@Override
	protected void dispose() {
		super.dispose();
		RobotState.hasBeenZeroed = true;
	}

}
