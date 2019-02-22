package org.usfirst.frc.team1619.robot.framework.controller;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NavXHeadingSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.TalonSRXEncoderPositionSensor;
import org.usfirst.frc.team1619.robot.framework.trajectory.Trajectory2DDistanceSteps;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HeadingController {

	private Trajectory2DDistanceSteps trajectory;

	private Feedforward rightFeedforward;
	private Feedforward leftFeedforward;
	private PID headingPID;

	private Motor left;
	private Motor right;

	private NumericSensor velocityLeft;
	private NumericSensor velocityRight;
	public NavXHeadingSensor headingSensor;

	private TalonSRXEncoderPositionSensor leftPosition;
	private TalonSRXEncoderPositionSensor rightPosition;

	public double previousError = 0.0;

	private boolean isInverted;

	public HeadingController(Feedforward leftFeedforward, Feedforward rightFeedforward, PID headingPID) {
		this.leftFeedforward = leftFeedforward;
		this.rightFeedforward = rightFeedforward;
		this.headingPID = headingPID;
	}

	// These two methods must be called before controller is used
	public void setMotors(Motor left, Motor right) {
		this.left = left;
		this.right = right;
	}

	// These two methods must be called before controller is used
	public void setSenors(TalonSRXEncoderPositionSensor leftPosition, TalonSRXEncoderPositionSensor rightPosition,
			NumericSensor velocityLeft, NumericSensor velocityRight, NavXHeadingSensor headingSensor) {

		this.leftPosition = leftPosition;
		this.rightPosition = rightPosition;

		this.velocityLeft = velocityLeft;
		this.velocityRight = velocityRight;
		this.headingSensor = headingSensor;
	}

	public void setInverted(boolean isInverted) {
		this.isInverted = isInverted;
	}

	public void prepareTrajectory(Trajectory2DDistanceSteps trajectory) {
		this.headingSensor.zero();
		this.leftPosition.zero();
		this.rightPosition.zero();
		this.trajectory = trajectory;
	}

	public void set(double distance) {
		double heading = this.trajectory.heading(distance) + this.previousError;

		while (heading < 0) {
			heading += 360.0;
		}

		double velocity = this.trajectory.velocity(distance);
		double previousVelocity = this.trajectory.velocity(distance - this.trajectory.resolution);
		double curvature = this.trajectory.curvature(distance);

		double acceleration = 0.0;
		acceleration = (Math.pow(velocity, 2) - Math.pow(previousVelocity, 2)) / (2 * (this.trajectory.resolution));

		if (this.isInverted) {
			velocity = -velocity;
		}

		double measuredHeading = this.headingSensor.get();
		double measuredLeftVelocity = this.velocityLeft.get();
		double measuredRightVelocity = this.velocityRight.get();

		this.headingPID.set(0);
		double headingError = Math.abs(heading - measuredHeading);

		if (headingError > 180) {
			headingError = 360.0 - headingError;
		}

		double leftAdjustment = 0.0;
		double rightAdjustment = 0.0;
		double headingPIDValue = 0.0;

		if (((measuredHeading < heading) && ((heading - measuredHeading) < 180))
				|| ((measuredHeading > heading) && ((measuredHeading - heading) > 180))) {
			headingPIDValue = this.headingPID.get(headingError);
		} else {
			headingPIDValue = this.headingPID.get(-headingError);
			headingError = -headingError;
		}

		rightAdjustment += -(velocity + 0.01) * headingPIDValue;
		leftAdjustment += (velocity + 0.01) * headingPIDValue;

		double leftVelocity = 0.0;
		double rightVelocity = 0.0;

		if (Math.abs(curvature) < 0.005) {
			leftVelocity = rightVelocity = velocity;
		} else {
			double leftRatio = 1 - ((Constants.ROBOT_WIDTH * curvature) / 2.0);
			double rightRatio = 1 + ((Constants.ROBOT_WIDTH * curvature) / 2.0);

			if (!this.isInverted) {
				leftVelocity = velocity * leftRatio;
				rightVelocity = velocity * rightRatio;
			} else {
				// right ratio assigned to left velocity for invert
				leftVelocity = velocity * rightRatio;
				rightVelocity = velocity * leftRatio;
			}
		}

		double leftOutput = 0.0;
		double rightOutput = 0.0;

		if (!this.isInverted) {
			leftOutput = this.leftFeedforward.get(leftVelocity + leftAdjustment, acceleration, measuredLeftVelocity,
					this.isInverted);

			rightOutput = this.rightFeedforward.get(rightVelocity + rightAdjustment, acceleration,
					measuredRightVelocity, this.isInverted);
		} else {
			leftOutput = this.leftFeedforward.get(leftVelocity - leftAdjustment, acceleration, measuredLeftVelocity,
					this.isInverted);

			rightOutput = this.rightFeedforward.get(rightVelocity - rightAdjustment, acceleration,
					measuredRightVelocity, this.isInverted);
		}

		double leftVelocityError = leftVelocity - measuredLeftVelocity;
		double rightVelocityError = rightVelocity - measuredRightVelocity;

		// SmartDashboard.putNumber("Target Heading", heading);
		// SmartDashboard.putNumber("Target Center Velocity", velocity);
		// SmartDashboard.putNumber("Target Left Velocity", leftVelocity);
		// SmartDashboard.putNumber("Target Right Velocity", rightVelocity);
		// SmartDashboard.putNumber("Target Acceleration", acceleration);
		// SmartDashboard.putNumber("Curvature", curvature);
		// SmartDashboard.putNumber("Heading Error", -headingError);
		// SmartDashboard.putNumber("Heading Unscaled PID Correction", headingPIDValue);
		// SmartDashboard.putNumber("Heading PID Correction",
		// Math.abs(rightAdjustment));
		// SmartDashboard.putNumber("Left Output", leftOutput);
		// SmartDashboard.putNumber("Right Output", rightOutput);
		// SmartDashboard.putNumber("Heading P Contribution", this.headingPID.p);
		// SmartDashboard.putNumber("Heading I Contribution", this.headingPID.i);
		// SmartDashboard.putNumber("Heading D Contribution", this.headingPID.d);
		// SmartDashboard.putNumber("Left P Contribution",
		// this.leftFeedforward.getPID().p);
		// SmartDashboard.putNumber("Left I Contribution",
		// this.leftFeedforward.getPID().i);
		// SmartDashboard.putNumber("Left D Contribution",
		// this.leftFeedforward.getPID().d);
		// SmartDashboard.putNumber("Left PID Value",
		// this.leftFeedforward.getPidValue());
		// SmartDashboard.putNumber("Right Velocity Error", rightVelocityError);
		// SmartDashboard.putNumber("Right Velocity Error Ratio", rightVelocityError /
		// (rightVelocity + rightAdjustment));
		// SmartDashboard.putNumber("Left Velocity Error", leftVelocityError);
		// SmartDashboard.putNumber("Left Velocity Error Ratio", leftVelocityError /
		// (leftVelocity + leftAdjustment));

		this.left.set(leftOutput);
		this.right.set(rightOutput);

	}

	public NavXHeadingSensor getHeadingSensor() {
		return headingSensor;
	}

	public PID getHeadingPID() {
		return this.headingPID;
	}

	public Feedforward getLeftFeedforward() {
		return leftFeedforward;
	}

	public Feedforward getRightFeedforward() {
		return rightFeedforward;
	}

}
