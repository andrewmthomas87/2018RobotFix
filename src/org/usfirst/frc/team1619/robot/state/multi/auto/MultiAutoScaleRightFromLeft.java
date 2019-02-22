package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.state.multi.ElevatorAndArmQuickSetWrapper;
import org.usfirst.frc.team1619.robot.state.multi.MultiDriveTwoSetWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftScale_FenceCube1ToScale;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftScale_FenceCube2ToScale;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftScale_ScaleToFenceCube1;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftScale_ScaleToFenceCube2;
import org.usfirst.frc.team1619.robot.trajectories.Left_RightSale_FenceCube6ToScale;
import org.usfirst.frc.team1619.robot.trajectories.Left_RightSale_LeftToScale;
import org.usfirst.frc.team1619.robot.trajectories.Left_RightSale_ScaleToFenceCube5;
import org.usfirst.frc.team1619.robot.trajectories.Left_RightSale_ScaleToFenceCube6;

public class MultiAutoScaleRightFromLeft extends MultiSubsystemSequencerState {

	public MultiAutoScaleRightFromLeft(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(
				new MultiDriveZeroThenIdleWrapper(
						new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_RightSale_LeftToScale()),
								true),
						Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION, 39.0,
						Constants.ELEVATOR_SCALE_HIGH_POSITION, Constants.ARM_SCALE_HIGH_BACK));
		
		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SCALE_HIGH_POSITION,
				Constants.ARM_SCALE_HIGH_BACK));

		this.add(new MultiDelayIdleWrapper(200, Constants.ELEVATOR_SCALE_HIGH_POSITION, Constants.ARM_SCALE_HIGH_BACK));

		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SCALE_HIGH_POSITION,
				Constants.ARM_SCALE_HIGH_BACK));

		this.add(new ElevatorAndArmQuickSetWrapper(Constants.ELEVATOR_QUICK_SET_THRESHOLD,
				Constants.ARM_QUICK_SET_THRESHOLD, Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiDriveAndIntakeWrapper(new DriveTrajectoryWrapper(
				new Trajectory2DDistanceSteps(new Left_RightSale_ScaleToFenceCube6()), false)));

		this.add(new MultiDriveTwoSetWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_RightSale_FenceCube6ToScale()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION, 3.2,
				Constants.ELEVATOR_SCALE_HIGH_POSITION, Constants.ARM_SCALE_HIGH_BACK));

		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SCALE_HIGH_POSITION_AUTO,
				Constants.ARM_SCALE_HIGH_BACK_AUTO));

		this.add(new ElevatorAndArmQuickSetWrapper(Constants.ELEVATOR_QUICK_SET_THRESHOLD,
				Constants.ARM_QUICK_SET_THRESHOLD, Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

//		 this.add(new MultiDriveAndIntakeWrapper(new DriveTrajectoryWrapper(
//		 new Trajectory2DDistanceSteps(new Left_RightSale_ScaleToFenceCube5()),
//		 false)));

		this.add(
				new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiElevatorAndArmIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

	}

}
