package org.usfirst.frc.team1619.robot.framework.trajectory;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Trajectory2DResolution extends Trajectory {

	private double[] distanceValues;
	private double[] velocityValues;
	private double[] headingValues;
	private double[] curvatureValues;
	private double timeValueLast;

	private double resolution;
	private int index = 0;

	public Trajectory2DResolution(TrajectoryData data) {
		this.initialHeading = data.initialHeading;
		this.finalHeading = data.finalHeading;
		this.resolution = data.resolution;

		this.timeValueLast = data.timeValues[data.timeValues.length - 1];
		this.velocityValues = data.velocityValues;
		this.distanceValues = data.distanceValues;
		this.headingValues = data.headingValues;
		this.curvatureValues = data.curvatureValues;

	}

	@Override
	public double distance(double distance) {
		this.updateIndex(distance);

		if (index < this.distanceValues.length - 1) {
			return this.distanceValues[this.index];
		}

		return this.distanceValues[this.headingValues.length - 1];
	}

	@Override
	public double velocity(double distance) {
		this.updateIndex(distance);

		if (this.index < this.velocityValues.length - 1) {
			return this.velocityValues[index];
		}

		return this.velocityValues[this.headingValues.length - 1];

	}

	@Override
	public double acceleration(double distance) {
		return 0;
	}

	@Override
	public double heading(double distance) {
		this.updateIndex(distance);
		SmartDashboard.putNumber("indexDistance", this.index);
		double heading = 0.0;

		if (this.index < headingValues.length - 1) {
			heading = this.headingValues[this.index];
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

	@Override
	public double curvature(double distance) {
		this.updateIndex(distance);
		if (this.index < curvatureValues.length - 1) {
			return this.curvatureValues[index];

		}

		return this.curvatureValues[this.headingValues.length - 1];
	}

	private void updateIndex(double distance) {
		for (int i = this.index; i < distanceValues.length; i++) {
			if (distanceValues[i] > distance) {
				if (i > 0) {
					this.index = i - 1;
				}

				break;
			} else if (i == distanceValues.length - 1) {
				this.index = i;
			}
		}

	}

	public double getResolution() {
		return this.resolution;
	}

	public double getIndexedLength() {
		return this.distanceValues.length - 1;
	}

	public boolean isDone(double _) {

		double distanceDelta = distanceValues[distanceValues.length - 1] - distanceValues[this.index];
		return (distanceDelta <= Constants.AUTO_DISTANCE_TIMEOUT_BOUND); // || (this.timer.isDone());
	}

}
