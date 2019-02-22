package org.usfirst.frc.team1619.robot.state.multi;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.arm.ArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdleWrapper;

public class MultiIdleProtected extends ParallelState {

	public MultiIdleProtected(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(new ArmIdleWrapper(Constants.ARM_PROTECTED_POSITION));
		this.addState(new ElevatorIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION));
	}

}
