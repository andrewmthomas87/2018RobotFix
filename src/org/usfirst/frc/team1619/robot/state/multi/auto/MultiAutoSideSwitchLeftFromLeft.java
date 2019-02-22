package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.framework.trajectory.TrapezoidTrajectory;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSideSwitch_BackupPointToLeftSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSideSwitch_LeftSwitchToBackupPoint;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSideSwitch_LeftToBackupPoint;
import org.usfirst.frc.team1619.robot.trajectories.Left_leftSideSwitch_BackupPointToCube;

public class MultiAutoSideSwitchLeftFromLeft extends MultiSubsystemSequencerState {

	public MultiAutoSideSwitchLeftFromLeft(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(
				new MultiDriveZeroThenIdleWrapper(
						new DriveTrajectoryWrapper(
								new Trajectory2DDistanceSteps(new Left_LeftSideSwitch_LeftToBackupPoint()), true),
						Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_SAFE_POSITION));

		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Left_LeftSideSwitch_BackupPointToLeftSwitch()), false),
				Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_SAFE_POSITION));
		
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Left_LeftSideSwitch_LeftSwitchToBackupPoint()), true),
				Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_SAFE_POSITION));
		
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Left_leftSideSwitch_BackupPointToCube()), true),
				Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_SAFE_POSITION));	}

}
