package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSwitch_BackupPointToLeftSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSwitch_FenceCube1BackUp;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSwitch_FenceCube1BackUpToLeftSwitch;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSwitch_FenceCube1LineUpToFenceCube1;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSwitch_LeftSwitchToFenceCube1LineUp;
import org.usfirst.frc.team1619.robot.trajectories.Left_LeftSwitch_LeftToBackupPoint;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;


public class MultiAutoSwitchLeftFromLeft extends MultiSubsystemSequencerState {

	public MultiAutoSwitchLeftFromLeft(Set<Integer> subsystemIds) {
		super(subsystemIds);

// ---------------- Left Switch Starting backwards from Left Side-------------------- //		
		
		// Drive straight backwards from left wall to line up to turn into the left switch
		this.add(new MultiDriveZeroThenIdleWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_LeftSwitch_LeftToBackupPoint()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Drive into the left switch
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_LeftSwitch_BackupPointToLeftSwitch()), false),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
		
		// Drive backwards to line up on Fence Cube #1
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_LeftSwitch_LeftSwitchToFenceCube1LineUp()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Turn on intake and drive forward to fence cube #1
		this.add(new MultiDriveAndIntakeWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_LeftSwitch_FenceCube1LineUpToFenceCube1()), false)));

		// Back up 
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_LeftSwitch_FenceCube1BackUp()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Put Cube in Left Switch 
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Left_LeftSwitch_FenceCube1BackUpToLeftSwitch()), false),
				Constants.ELEVATOR_SWITCH_POSITION, Constants.ARM_SWITCH_FRONT));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SWITCH_POSITION, Constants.ARM_SWITCH_FRONT));
		
		// Drive to fence cube #2
					
// ---------------- Left Switch Starting from Left Side-------------------- //		
			
	}

	@Override
	protected void dispose() {
		super.dispose();
		RobotState.autoDone = true;
	}

}
