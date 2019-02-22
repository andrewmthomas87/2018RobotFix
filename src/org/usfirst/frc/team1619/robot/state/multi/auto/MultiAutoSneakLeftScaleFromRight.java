package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.state.drive.DriveRotateWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveSetVoltageWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.state.multi.ElevatorAndArmQuickSetWrapper;
import org.usfirst.frc.team1619.robot.state.multi.MultiZeroWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Left_RightScale_LeftSneakToScale;
import org.usfirst.frc.team1619.robot.trajectories.Right_LeftScale_RightSneakToScale;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MultiAutoSneakLeftScaleFromRight extends MultiSubsystemSequencerState {

	public MultiAutoSneakLeftScaleFromRight(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(new DriveSetVoltageWrapper(-0.3, 400));

		this.add(new DriveRotateWrapper(88.5, IO.PROFILE_DRIVE_PID_ROTATE, Constants.HEADING_ERROR_THRESHOLD,
				Constants.HEADING_ERROR_THRESHOLD_TIMEOUT));

		this.add(
				new MultiDriveZeroThenIdleWrapper(
						new DriveTrajectoryWrapper(
								new Trajectory2DDistanceSteps(new Right_LeftScale_RightSneakToScale()), true),
						Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiDriveWrapper(new DriveRotateWrapper(285.0, IO.PROFILE_DRIVE_PID_ROTATE_CABLE_GUARD,
				Constants.HEADING_ERROR_THRESHOLD_CABLE_GUARD, Constants.HEADING_ERROR_THRESHOLD_TIMEOUT_CABLE_GUARD),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

//		this.add(new MultiDriveWrapper(new DriveSetVoltageWrapper(-0.3, 500), Constants.ELEVATOR_SCALE_HIGH_POSITION,
//				Constants.ARM_SCALE_HIGH_BACK));

		// Lift arm up to scale
		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SCALE_HIGH_POSITION,
				Constants.ARM_SCALE_HIGH_BACK));

//		this.add(new MultiDelayIdleWrapper(250, Constants.ELEVATOR_SCALE_HIGH_POSITION, Constants.ARM_SCALE_HIGH_BACK));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SCALE_HIGH_POSITION,
				Constants.ARM_SCALE_HIGH_BACK));

		// this.add(new MultiDriveWrapper(new DriveSetVoltageWrapper(0.3, 500),
		// Constants.ELEVATOR_SCALE_HIGH_POSITION,
		// Constants.ARM_SCALE_HIGH_BACK));

		// Put arm down to floor
		this.add(new ElevatorAndArmQuickSetWrapper(Constants.ELEVATOR_QUICK_SET_THRESHOLD,
				Constants.ARM_QUICK_SET_THRESHOLD, Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

//		this.add(new MultiDriveWrapper(new DriveSetVoltageWrapper(0.3, 400), Constants.ELEVATOR_FLOOR_POSITION,
//				Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiDriveWrapper(new DriveRotateWrapper(270.0, IO.PROFILE_DRIVE_PID_ROTATE_CABLE_GUARD,
				Constants.HEADING_ERROR_THRESHOLD_CABLE_GUARD, Constants.HEADING_ERROR_THRESHOLD_TIMEOUT_CABLE_GUARD),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiElevatorAndArmIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));
	}

}
