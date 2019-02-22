package org.usfirst.frc.team1619.robot.framework.trajectory;

import org.usfirst.frc.team1619.robot.Constants;

public class Trajectory2DDistanceSteps extends Trajectory {

	private double[] distanceValues;
	private double[] velocityValues;
	private double[] headingValues;
	private double[] curvatureValues;

	public double resolution;
	private int index = 0;

	public Trajectory2DDistanceSteps(TrajectoryData data) {
		this.initialHeading = data.initialHeading;
		this.finalHeading = data.finalHeading;
		this.resolution = data.resolution;

		this.velocityValues = data.velocityValues;
		this.distanceValues = data.distanceValues;
		this.headingValues = data.headingValues;
		this.curvatureValues = data.curvatureValues;
	}

	@Override
	public double distance(double distance) {
		this.updateIndex(distance);

		if (this.index < distanceValues.length - 1) {

			double offset = distance / this.resolution % (this.resolution);
			return this.distanceValues[index]
					+ (this.distanceValues[this.index + 1] - this.distanceValues[this.index]) * offset;
		}

		return 0.0;
	}

	@Override
	public double velocity(double distance) {
		this.updateIndex(distance);

		if (this.index < velocityValues.length - 1) {

			double offset = distance / this.resolution % (this.resolution);
			return this.velocityValues[this.index]
					+ (this.velocityValues[this.index + 1] - this.velocityValues[this.index]) * offset;
		}

		return 0.0;
	}

	@Override
	public double acceleration(double distance) {
		return 0;
	}

	@Override
	public double heading(double distance) {
		this.updateIndex(distance);

		double heading = -1;

		if (this.index < headingValues.length - 1) {

			double offset = distance / this.resolution % (this.resolution);
			heading = this.headingValues[this.index]
					+ (this.headingValues[this.index + 1] - this.headingValues[this.index]) * offset;

		} else {
			heading = this.headingValues[this.headingValues.length - 1];
		}

		if (heading < 0.0) {
			heading += 360.0;
		}

		if (heading >= this.initialHeading) {
			heading = heading - initialHeading;
		} else {
			heading = 360.0 - (this.initialHeading - heading);
		}

		return heading;
	}

	private void updateIndex(double distance) {
		this.index = (int) (distance / this.resolution);

	}

	@Override
	public double curvature(double distance) {
		this.updateIndex(distance);

		if (this.index < curvatureValues.length - 1) {

			double offset = distance / this.resolution % (this.resolution);
			return this.curvatureValues[this.index]
					+ (this.curvatureValues[this.index + 1] - this.curvatureValues[this.index]) * offset;
		}

		return 0.0;
	}

	@Override
	public boolean isDone(double distnace) {

		int index = this.index;
		if (this.index > distanceValues.length - 2) {
			index = distanceValues.length - 2;
		}

		double distanceDelta = distanceValues[distanceValues.length - 2] - distanceValues[index];
		return (distanceDelta <= Constants.AUTO_DISTANCE_TIMEOUT_BOUND);

	}

}
