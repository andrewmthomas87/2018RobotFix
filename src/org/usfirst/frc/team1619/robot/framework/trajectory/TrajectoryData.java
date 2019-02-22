package org.usfirst.frc.team1619.robot.framework.trajectory;

public abstract class TrajectoryData {

	public double resolution;
	public double initialHeading, finalHeading;
	public double[] timeValues;
	public double[] velocityValues;
	public double[] headingValues;
	public double[] curvatureValues;
	public double[] distanceValues;

	public TrajectoryData(double resolution, double initialHeading, double finalHeading, double[] timeValues,
			double[] distanceValues, double[] velocityValues, double[] headingValues, double[] curvatureValues) {

		this.timeValues = timeValues;
		this.velocityValues = velocityValues;
		this.distanceValues = distanceValues;
		this.headingValues = headingValues;
		this.curvatureValues = curvatureValues;

		this.resolution = resolution;
		this.initialHeading = initialHeading;
		this.finalHeading = finalHeading;

	}

}