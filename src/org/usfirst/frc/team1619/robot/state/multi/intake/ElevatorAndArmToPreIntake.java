package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.arm.ArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.arm.ArmSetWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdleWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorSetWrapper;
import org.usfirst.frc.team1619.robot.state.misc.CancelCheckWrapper;

public class ElevatorAndArmToPreIntake extends ParallelState {

	public ElevatorAndArmToPreIntake(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addStatePair(
				new ElevatorSetWrapper(Constants.ELEVATOR_SAFE_POSITION, Constants.ELEVATOR_ACCEPTABLE_RANGE,
						10),
				new ElevatorIdleWrapper(Constants.ELEVATOR_SAFE_POSITION));
		this.addStatePair(new ArmSetWrapper(Constants.ARM_SAFE_POSITION, Constants.ARM_ACCEPTABLE_RANGE,
				10), new ArmIdleWrapper(Constants.ARM_SAFE_POSITION));
		this.addBackgroundState(new CancelCheckWrapper());

	}

}
