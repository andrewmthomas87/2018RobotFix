package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.arm.ArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.arm.ArmSetWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdleWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorSetWrapper;
import org.usfirst.frc.team1619.robot.state.misc.CancelCheckWrapper;

public class ElevatorAndArmToIntake extends ParallelState {

	public ElevatorAndArmToIntake(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addStatePair(
				new ElevatorSetWrapper(Constants.ELEVATOR_FLOOR_POSITION, Constants.ELEVATOR_ACCEPTABLE_RANGE,
						Constants.TIME_IN_ACCEPTABLE_RADIUS_PID),
				new ElevatorIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION));

		this.addStatePair(new ArmSetWrapper(Constants.ARM_INTAKE_POSITION, Constants.ARM_ACCEPTABLE_RANGE,
				Constants.TIME_IN_ACCEPTABLE_RADIUS_PID), new ArmIdleWrapper(Constants.ARM_INTAKE_POSITION));
		
		this.addBackgroundState(new CancelCheckWrapper());
	}

	@Override
	public boolean isDone() {
		return super.isDone();
	}

}
