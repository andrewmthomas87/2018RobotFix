package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.framework.util.DelayWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.trajectories.Left_RightSwitch_LeftToRightSwitchLineUp;
import org.usfirst.frc.team1619.robot.trajectories.Left_RightSwitch_RightSwitchLineUpToRightSwitch;

public class MultiAutoSwitchRightFromLeft extends MultiSubsystemSequencerState {

	public MultiAutoSwitchRightFromLeft(Set<Integer> subsystemIds) {
		super(subsystemIds);

		// ---------------- Right Switch Starting backwards from Left
		// Side-------------------- //

		this.add(new DelayWrapper(2000));

		// Drive backwards from left wall to line up with right switch
		this.add(new MultiDriveZeroThenIdleWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Left_RightSwitch_LeftToRightSwitchLineUp()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Drive forward to Right Switch
		this.add(new MultiDriveWrapper(
				new DriveTrajectoryWrapper(
						new Trajectory2DDistanceSteps(new Left_RightSwitch_RightSwitchLineUpToRightSwitch()), false),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

		// Eject cube
		this.add(new MultiEjectWrapper(Constants.FAST_EJECT_PROFILE, Constants.ELEVATOR_FLOOR_POSITION,
				Constants.ARM_PROTECTED_POSITION));

		// ---------------- Left Switch Starting from Right Side-------------------- //

	}

	@Override
	protected void dispose() {
		super.dispose();
		RobotState.autoDone = true;
	}

}
