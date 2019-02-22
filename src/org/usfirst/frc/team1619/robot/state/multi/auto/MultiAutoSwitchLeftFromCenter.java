package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Center_LeftSwitch_CenterCubeToCenterForward;
import org.usfirst.frc.team1619.robot.trajectories.Center_LeftSwitch_CenterForwardToCenterCube;
import org.usfirst.frc.team1619.robot.trajectories.Center_LeftSwitch_CenterForwardToSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Center_LeftSwitch_CenterToSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Center_LeftSwitch_SwitchToCenterForward;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;


public class MultiAutoSwitchLeftFromCenter extends MultiSubsystemSequencerState {

	public MultiAutoSwitchLeftFromCenter(Set<Integer> subsystemIds) {
		super(subsystemIds);

// ---------------- Left Switch -------------------- //		
		
		// Drive from center wall to left switch
		this.add(new MultiDriveZeroThenIdleWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_LeftSwitch_CenterToSwitch()), false),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Drive backwards to center in line with cube pyramid cubes
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_LeftSwitch_SwitchToCenterForward()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Turn on intake and drive forward to cube
		this.add(new MultiDriveAndIntakeWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_LeftSwitch_CenterForwardToCenterCube()), false)));

		// Drive backwards
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_LeftSwitch_CenterCubeToCenterForward()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Drive forwards to left switch
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_LeftSwitch_CenterForwardToSwitch()), false),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
		
		// Drive backwards to center in line with cube pyramid cubes
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Center_LeftSwitch_SwitchToCenterForward()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
		
					
		// ---------------- Left Switch -------------------- //		
			
	}

	@Override
	protected void dispose() {
		super.dispose();
		RobotState.autoDone = true;
	}

}
