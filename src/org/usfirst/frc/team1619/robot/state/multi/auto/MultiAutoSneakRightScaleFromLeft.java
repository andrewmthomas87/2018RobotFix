package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Left_RightScale_LeftSneakToScale;

public class MultiAutoSneakRightScaleFromLeft extends MultiSubsystemSequencerState{

	public MultiAutoSneakRightScaleFromLeft(Set<Integer> subsystemIds) {
		super(subsystemIds);
		
		this.add(new MultiDriveZeroThenIdleWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_RightScale_LeftSneakToScale()), true),
				Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_SAFE_POSITION));
	}
}
