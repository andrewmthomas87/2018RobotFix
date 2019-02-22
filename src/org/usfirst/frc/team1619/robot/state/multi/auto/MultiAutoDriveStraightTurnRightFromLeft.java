package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Left_DriveCenter_LeftToCenter;
import org.usfirst.frc.team1619.robot.trajectories.Right_LeftScale_RightToScale;

public class MultiAutoDriveStraightTurnRightFromLeft extends MultiSubsystemSequencerState {

	public MultiAutoDriveStraightTurnRightFromLeft(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(new MultiDriveZeroThenIdleWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_DriveCenter_LeftToCenter()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
		
		this.add(new MultiElevatorAndArmIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
	}

}
