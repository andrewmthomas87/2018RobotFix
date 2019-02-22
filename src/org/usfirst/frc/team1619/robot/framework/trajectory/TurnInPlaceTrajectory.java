package org.usfirst.frc.team1619.robot.framework.trajectory;

public class TurnInPlaceTrajectory extends Trajectory {

	private TrapezoidTrajectory trapezoid;
	private boolean right;

	public TurnInPlaceTrajectory(double degrees, double acceleration, double deceleration, double coastingVelocity) {
		if (degrees < 0) {
			this.right = true;
		}

		double radians = Math.toRadians(degrees);
		double distance = 3.00 * Math.abs(radians);
		this.trapezoid = new TrapezoidTrajectory(distance, acceleration, deceleration, coastingVelocity);
	}

	@Override
	public double distance(double time) {
		return 0;
	}

	@Override
	public double velocity(double time) {
		return 0;
	}

	@Override
	public double acceleration(double time) {
		return 0;
	}

	@Override
	public double heading(double time) {
		double distance = this.trapezoid.distance(time);
		double heading = 0.0;

		if (this.right) {
			heading = 360.0 - (distance / (2 * Math.PI * 3.00) * 360.0) % 360;
		} else {
			heading = distance / (2 * Math.PI * 3.00) * 360.0;
		}

		return heading;
	}

	@Override
	public double curvature(double time) {
		return 0.0;
	}

	@Override
	public boolean isDone(double time) {
		return this.trapezoid.isDone(time);
	}

}
