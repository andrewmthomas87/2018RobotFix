package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.state.drive.DriveTrajectoryWrapper;
import org.usfirst.frc.team1619.robot.trajectories.DRIVE_STRAIGHT_CENTER;
import org.usfirst.frc.team1619.robot.trajectories.DRIVE_STRAIGHT_SIDES;

public class MultiAutoDriveStraight extends MultiSubsystemSequencerState {

	public MultiAutoDriveStraight(Set<Integer> subsystemIds, boolean isCenter) {
		super(subsystemIds);

		this.add(new MultiDriveZeroThenIdleWrapper(
				new DriveTrajectoryWrapper(new Trajectory2DDistanceSteps(
						isCenter ? new DRIVE_STRAIGHT_CENTER() : new DRIVE_STRAIGHT_SIDES()), true),
				Constants.ELEVATOR_FLOOR_POSITION, Constants.ARM_PROTECTED_POSITION));

	}

	@Override
	protected void dispose() {
		super.dispose();
		RobotState.autoDone = true;
	}

}
