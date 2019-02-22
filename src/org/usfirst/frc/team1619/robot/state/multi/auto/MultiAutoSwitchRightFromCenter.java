package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Center_RightSwitch_CenterCubeToCenterForward;
import org.usfirst.frc.team1619.robot.trajectories.Center_RightSwitch_CenterForwardToCenterCube;
import org.usfirst.frc.team1619.robot.trajectories.Center_RightSwitch_CenterForwardToSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Center_RightSwitch_CenterToSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Center_RightSwitch_SwitchToCenterForward;

public class MultiAutoSwitchRightFromCenter extends MultiSubsystemSequencerState {

	public MultiAutoSwitchRightFromCenter(Set<Integer> subsystemIds) {
		super(subsystemIds);
		
		// ---------------- Right Switch -------------------- //		
		
		// Drive from center wall to right switch
		this.add(new MultiDriveZeroThenIdleWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_RightSwitch_CenterToSwitch()), false),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Drive backwards to center in line with cube pyramid cubes
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_RightSwitch_SwitchToCenterForward()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Turn on intake and drive forward to cube
		this.add(new MultiDriveAndIntakeWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_RightSwitch_CenterForwardToCenterCube()), false)));

		// Drive backwards
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_RightSwitch_CenterCubeToCenterForward()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Drive forwards to right switch
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_RightSwitch_CenterForwardToSwitch()), false),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
		
		// Drive backwards to center in line with cube pyramid cubes
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_RightSwitch_SwitchToCenterForward()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
		
		// ---------------- Right Switch -------------------- //		

	}

	@Override
	protected void dispose() {
		super.dispose();
		RobotState.autoDone = true;
	}

}
