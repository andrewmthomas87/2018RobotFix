package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.state.multi.ElevatorAndArmQuickSetWrapper;
import org.usfirst.frc.team1619.robot.state.multi.MultiDriveTwoSetWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightScale_FenceCube5ToScale;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightScale_FenceCube6ToScale;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightScale_RightToScale;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightScale_ScaleToFenceCube5;
import org.usfirst.frc.team1619.robot.trajectories.Right_RightScale_ScaleToFenceCube6;

public class MultiAutoScaleRightFromRight extends MultiSubsystemSequencerState {

	public MultiAutoScaleRightFromRight(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(
				new MultiDriveZeroThenIdleWrapper(
						new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightScale_RightToScale()),
								true),
						Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION, 18.0,
						Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO, Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new MultiEjectWrapper(Constants.MEDIUM_EJECT_PROFILE, Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new ElevatorAndArmQuickSetWrapper(Constants.ELEVATOR_QUICK_SET_THRESHOLD,
				Constants.ARM_QUICK_SET_THRESHOLD, Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiDriveAndIntakeWrapper(new DriveTrajectoryWrapper(
				new Trajectory2DDistanceSteps(new Right_RightScale_ScaleToFenceCube6()), false)));

		this.add(new MultiDriveTwoSetWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightScale_FenceCube6ToScale()),
						true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION, 3.2,
				Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO, Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

//		this.add(new MultiDelayIdleWrapper(100, Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
//				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new ElevatorAndArmQuickSetWrapper(Constants.ELEVATOR_QUICK_SET_THRESHOLD,
				Constants.ARM_QUICK_SET_THRESHOLD, Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiDriveAndIntakeWrapper(new DriveTrajectoryWrapper(
				new Trajectory2DDistanceSteps(new Right_RightScale_ScaleToFenceCube5()), false)));

		this.add(new MultiDriveTwoSetWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightScale_FenceCube5ToScale()),
						true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION, 4.0,
				Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO, Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new MultiDelayIdleWrapper(200, Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(
				new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiElevatorAndArmIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

	}

}
