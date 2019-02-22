package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.arm.ArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdleWrapper;

public class MultiElevatorAndArmIdle extends ParallelState {
	public MultiElevatorAndArmIdle(double elevatorSetpoint, double armSetpoint, Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(new ArmIdleWrapper(armSetpoint));
		this.addState(new ElevatorIdleWrapper(elevatorSetpoint));
	}
}
