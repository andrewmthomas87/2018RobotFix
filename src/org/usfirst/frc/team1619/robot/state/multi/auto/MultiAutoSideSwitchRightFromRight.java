package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.framework.trajectory.TrapezoidTrajectory;
import org.usfirst.frc.team1619.robot.state.drive.DriveRotateWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveSetVoltageAfterPauseWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveSetVoltageWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.state.multi.MultiDriveTwoSetWrapper;
import org.usfirst.frc.team1619.robot.state.multi.MultiElevatorAndArmTwoSetWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSideSwitch_BackupPointToLeftSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSideSwitch_LeftSwitchToBackupPoint;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSideSwitch_LeftToBackupPoint;
import org.usfirst.frc.team1619.robot.trajectories.Left_leftSideSwitch_BackupPointToCube;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightSideSwitch_BackupPointToCube;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightSideSwitch_BackupPointToRightSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightSideSwitch_CubeToBackupPoint;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightSideSwitch_RightSwitchToBackupPoint;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightSideSwitch_RightToBackupPoint;

public class MultiAutoSideSwitchRightFromRight extends MultiSubsystemSequencerState {

	public MultiAutoSideSwitchRightFromRight(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(new MultiDriveZeroThenIdleWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Right_RightSideSwitch_RightToBackupPoint()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Right_RightSideSwitch_BackupPointToRightSwitch()), false),
				Constants.ELEVATOR_SWITCH_POSITION, Constants.ARM_SWITCH_FRONT));

		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SWITCH_POSITION,
				Constants.ARM_SWITCH_FRONT));

		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Right_RightSideSwitch_RightSwitchToBackupPoint()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// this.add(new MultiDelayIdleWrapper(800, Constants.ELEVATOR_FLOOR_POSITION,
		// Constants.ARM_SAFE_POSITION));

		this.add(new MultiDriveAndIntakeWrapper(new DriveSetVoltageAfterPauseWrapper(800, 0.3, 2000)));

		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SWITCH_POSITION, Constants.ARM_SWITCH_FRONT));

		this.add(new MultiDriveWrapper(new DriveSetVoltageWrapper(0.3, 500), Constants.ELEVATOR_SWITCH_POSITION,
				Constants.ARM_SWITCH_FRONT));

		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SWITCH_POSITION,
				Constants.ARM_SWITCH_FRONT));

		this.add(new MultiDriveTwoSetWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightSideSwitch_CubeToBackupPoint()),
						true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION, 1,
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_SAFE_POSITION));

		this.add(new MultiDriveAndIntakeWrapper(new DriveTrajectoryWrapper(
				new Trajectory2DDistanceSteps(new Right_RightSideSwitch_BackupPointToCube()), false)));

		this.add(new MultiDriveWrapper(
				new DriveRotateWrapper(300, IO.PROFILE_DRIVE_PID_ROTATE, Constants.HEADING_ERROR_THRESHOLD,
						Constants.HEADING_ERROR_THRESHOLD_TIMEOUT),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiElevatorAndArmIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));
	}

}
