package org.usfirst.frc.team1619.robot.state.multi.climb;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.arm.ArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.climbingAids.RampDeployWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorManualWrapper;

public class MultiClimb extends ParallelState {

	@Override
	protected void initialize() {
		RobotState.highGear = false;
		super.initialize();
	}

	public MultiClimb(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(new ElevatorManualWrapper());
		this.addBackgroundState(new ArmIdleWrapper(Constants.ARM_PROTECTED_POSITION));
		this.addBackgroundState(new RampDeployWrapper());
	}

}
