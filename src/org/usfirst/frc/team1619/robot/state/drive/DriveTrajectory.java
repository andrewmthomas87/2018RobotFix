package org.usfirst.frc.team1619.robot.state.drive;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NavXHeadingSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.TalonSRXEncoderPositionSensor;
import org.usfirst.frc.team1619.robot.framework.controller.HeadingController;
import org.usfirst.frc.team1619.robot.framework.state.State;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrajectory extends State {

	private TalonSRXEncoderPositionSensor leftPosition;
	private TalonSRXEncoderPositionSensor rightPosition;
	private NavXHeadingSensor headingSensor;

	private HeadingController controller;

	private boolean isTrajectoryComplete;
	private Trajectory2DDistanceSteps trajectory;
	private boolean isInverted;

	public DriveTrajectory(In in, Out out, Trajectory2DDistanceSteps trajectory, boolean isInverted) {

		this.controller = IO.driveHeadingController;
		this.trajectory = trajectory;
		this.isInverted = isInverted;
		this.leftPosition = in.get(IO.SENSOR_DRIVE_LEFT_POSITION);
		this.rightPosition = in.get(IO.SENSOR_DRIVE_RIGHT_POSITION);
		this.headingSensor = in.get(IO.SENSOR_NAVX_HEADING);
	}

	@Override
	protected void initialize() {

		this.controller.prepareTrajectory(this.trajectory);

		IO.driveHeadingController.setInverted(this.isInverted);
		IO.driveHeadingController.getLeftFeedforward().setProfile(IO.PROFILE_DRIVE_LEFT_FEEDFORWARD);
		IO.driveHeadingController.getRightFeedforward().setProfile(IO.PROFILE_DRIVE_RIGHT_FEEDFORWARD);
		IO.driveHeadingController.getLeftFeedforward().getPID().setProfile(IO.PROFILE_DRIVE_LEFT_PID);
		IO.driveHeadingController.getRightFeedforward().getPID().setProfile(IO.PROFILE_DRIVE_RIGHT_PID);
		IO.driveHeadingController.getHeadingPID().setProfile(IO.PROFILE_DRIVE_HEADING_PID);
	}

	@Override
	protected void update() {

		double distance = Math.max(((Math.abs(this.leftPosition.get() + this.rightPosition.get()) / 2.0)), 0.1);
		this.controller.set(Math.abs(distance));

	}

	@Override
	protected void dispose() {

		double error = (this.trajectory.finalHeading - this.trajectory.initialHeading) - this.headingSensor.get();

		this.controller.previousError += error;

		RobotState.previousheadingError += Math.abs(error);
		this.rightPosition.zero();
		this.leftPosition.zero();
	}

	public boolean isTrajectoryNearCompletion() {
		return this.trajectory.isDone(-1);
	}
}