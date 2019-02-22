package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;


public class MultiAutoSwitchRightFromRight extends MultiSubsystemSequencerState {

	public MultiAutoSwitchRightFromRight(Set<Integer> subsystemIds) {
		super(subsystemIds);

// ---------------- Right Switch Starting backwards from Right Side-------------------- //		

//		// Drive backwards from right wall into the end of right switch
//		this.add(new MultiDriveZeroThenIdleWrapper(
//				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightSwitch_RightToRightSwitch()), true),
//				Constants.ELEVATOR_SWITCH_POSITION, Constants.ARM_SWITCH_BACK));
//
//		// Eject cube
//		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_SWITCH_POSITION, Constants.ARM_SWITCH_BACK));
//		
//		// Drive forward to position to line up on Fence Cube #6
//		this.add(new MultiDriveWrapper(
//				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightSwitch_RightSwitchToFenceCube6LineUp()), false),
//				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
//
//		// Turn on intake and drive forward to fence cube #6
//		this.add(new MultiDriveAndIntakeWrapper(
//				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightSwitch_FenceCube6LineUpToFenceCube6()), false)));
//
//		// Back up 
//		this.add(new MultiDriveWrapper(
//				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightSwitch_FenceCube6BackUp()), true),
//				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
//
//		// Put Cube in Right Switch 
//		this.add(new MultiDriveWrapper(
//				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(new Right_RightSwitch_FenceCube6BackUpToRightSwitch()), true),
//				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
//
//		// Eject cube
//		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));
//		
//		// Drive to fence cube #5
					
// ---------------- Left Switch Starting from Left Side-------------------- //		
			
	}

	@Override
	protected void dispose() {
		super.dispose();
		RobotState.autoDone = true;
	}

}
