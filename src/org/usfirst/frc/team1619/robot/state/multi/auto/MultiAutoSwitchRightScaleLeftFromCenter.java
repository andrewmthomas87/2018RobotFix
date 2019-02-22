package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.state.drive.DriveRotateWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveSetVoltageWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.state.multi.ElevatorAndArmQuickSetWrapper;
import org.usfirst.frc.team1619.robot.state.multi.MultiDriveTwoSetWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Center_LeftScale_CenterCubeToScale_RL_Side;
import org.usfirst.frc.team1619.robot.trajectories.Center_RightSwitch_CenterForwardToCenterCube;
import org.usfirst.frc.team1619.robot.trajectories.Center_RightSwitch_CenterToSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Center_RightSwitch_SwitchToCenterForward;

public class MultiAutoSwitchRightScaleLeftFromCenter extends MultiSubsystemSequencerState {

	public MultiAutoSwitchRightScaleLeftFromCenter(Set<Integer> subsystemIds) {
		super(subsystemIds);

		// ---------------- Right Switch then to Left Scale-------------------- //

		// Drive from center wall to left switch (Use LeftSwitch trajectories since the
		// first 3 steps are the same)
		this.add(
				new MultiDriveZeroThenIdleWrapper(
						new DriveTrajectoryWrapper(
								new Trajectory2DDistanceSteps(new Center_RightSwitch_CenterToSwitch()), false),
						Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SWITCH_POSITION, Constants.ARM_SWITCH_FRONT));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SWITCH_POSITION,
				Constants.ARM_SWITCH_FRONT));

		// Drive backwards to center in line with cube pyramid cubes (Use LeftSwitch
		// trajectories since the first 3 steps are the same)
		this.add(new MultiDriveTwoSetWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Center_RightSwitch_SwitchToCenterForward()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION, 3.0,
				Constants.ELEVATOR_SAFE_POSITION, Constants.ARM_SAFE_POSITION));

		// Turn on intake and drive forward to cube (Use LeftSwitch trajectories since
		// the first 3 steps are the same)
		this.add(new MultiDriveAndIntakeWrapper(new DriveTrajectoryWrapper(
				new Trajectory2DDistanceSteps(new Center_RightSwitch_CenterForwardToCenterCube()), false)));

		// Drive to left scale, lined up to drop cube
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Center_LeftScale_CenterCubeToScale_RL_Side()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		this.add(new MultiDriveWrapper(new DriveRotateWrapper(295.0, IO.PROFILE_DRIVE_PID_ROTATE_CABLE_GUARD,
				Constants.HEADING_ERROR_THRESHOLD_CABLE_GUARD, Constants.HEADING_ERROR_THRESHOLD_TIMEOUT_CABLE_GUARD),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// this.add(new MultiDriveWrapper(new DriveSetVoltageWrapper(-0.3, 500),
		// Constants.ELEVATOR_SCALE_HIGH_POSITION,
		// Constants.ARM_SCALE_HIGH_BACK));

		// Lift arm up to scale
		this.add(new MultiElevatorAndArmSetWrapper(Constants.ELEVATOR_SCALE_HIGH_POSITION,
				Constants.ARM_SCALE_HIGH_BACK));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.ELEVATOR_SCALE_HIGH_POSITION, Constants.ARM_SCALE_HIGH_BACK));

		this.add(new MultiDriveWrapper(new DriveSetVoltageWrapper(0.3, 500), Constants.ELEVATOR_SCALE_HIGH_POSITION,
				Constants.ARM_SCALE_HIGH_BACK));

		// Put arm down to floor
		this.add(new ElevatorAndArmQuickSetWrapper(Constants.ELEVATOR_QUICK_SET_THRESHOLD,
				Constants.ARM_QUICK_SET_THRESHOLD, Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

		// Put arm down to floor
		this.add(new MultiElevatorAndArmIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

		// ---------------- Left Switch then to Left Scale-------------------- //

	}

	@Override
	protected void dispose() {
		super.dispose();
		RobotState.autoDone = true;
	}

}
