package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class DriveTrajectoryWrapper extends Wrapper<DriveTrajectory> {

	private Trajectory2DDistanceSteps trajectory;
	private boolean isInverted;

	private Timer timer = new Timer();

	public DriveTrajectoryWrapper(Trajectory2DDistanceSteps trajectory, boolean isInverted) {
		this.trajectory = trajectory;
		this.isInverted = isInverted;
	}

	@Override
	protected DriveTrajectory createState() {
		return new DriveTrajectory(in, out, this.trajectory, this.isInverted);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		if (this.getState().isTrajectoryNearCompletion()) {
			if (!timer.isStarted()) {
				timer.start(Constants.AUTO_TRAJECTORY_TIMEOUT);
			}
		} else if (timer.isStarted()) {
			timer.reset();
		}

		return timer.isDone();

	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.drive.getId();
	}

}