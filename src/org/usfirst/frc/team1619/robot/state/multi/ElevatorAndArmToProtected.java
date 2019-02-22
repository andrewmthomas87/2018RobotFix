package org.usfirst.frc.team1619.robot.state.multi;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.arm.ArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.arm.ArmSetWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdleWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorSetWrapper;
import org.usfirst.frc.team1619.robot.state.misc.CancelCheckWrapper;

public class ElevatorAndArmToProtected extends ParallelState {

	public ElevatorAndArmToProtected(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addStatePair(
				new ElevatorSetWrapper(Constants.ELEVATOR_FLOOR_POSITION, Constants.ELEVATOR_ACCEPTABLE_RANGE,
						Constants.TIME_IN_ACCEPTABLE_RADIUS_PID),
				new ElevatorIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION));
		this.addStatePair(new ArmSetWrapper(Constants.ARM_PROTECTED_POSITION, Constants.ARM_ACCEPTABLE_RANGE,
				Constants.TIME_IN_ACCEPTABLE_RADIUS_PID), new ArmIdleWrapper(Constants.ARM_PROTECTED_POSITION));

		this.addBackgroundState(new CancelCheckWrapper());

	}
	
	@Override
	protected void dispose() {
		RobotState.cancelZero = false;
		super.dispose();
	}

}
