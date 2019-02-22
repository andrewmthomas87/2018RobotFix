package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.arm.ArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.arm.ArmSetWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdleWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorSetWrapper;

public class MultiElevatorAndArmSet extends ParallelState {

	public MultiElevatorAndArmSet(double elevatorSetpoint, double armSetpoint, Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addStatePair(
				new ArmSetWrapper(armSetpoint, Constants.ARM_ACCEPTABLE_RANGE, Constants.TIME_IN_ACCEPTABLE_RADIUS_PID),
				new ArmIdleWrapper(armSetpoint));
		this.addStatePair(new ElevatorSetWrapper(elevatorSetpoint, Constants.ELEVATOR_ACCEPTABLE_RANGE,
				Constants.TIME_IN_ACCEPTABLE_RADIUS_PID), new ElevatorIdleWrapper(elevatorSetpoint));
	}

}
