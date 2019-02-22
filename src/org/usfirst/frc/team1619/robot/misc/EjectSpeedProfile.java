package org.usfirst.frc.team1619.robot.misc;

public class EjectSpeedProfile {

	private double topEjectSpeed;
	private double bottomEjectSpeed;

	public EjectSpeedProfile(double topEjectSpeed, double bottomEjectSpeed) {
		this.topEjectSpeed = topEjectSpeed;
		this.bottomEjectSpeed = bottomEjectSpeed;
	}

	public double getTopEjectSpeed() {
		return this.topEjectSpeed;
	}

	public double getBottomEjectSpeed() {
		return this.bottomEjectSpeed;
	}

}
