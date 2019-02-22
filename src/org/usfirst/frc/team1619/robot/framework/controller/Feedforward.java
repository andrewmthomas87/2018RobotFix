package org.usfirst.frc.team1619.robot.framework.controller;

import java.util.ArrayList;
import java.util.List;

public class Feedforward {

	private class Profile {
		public double kv, ka, kd;

		public Profile(double kv, double ka, double kd) {
			this.kv = kv;
			this.ka = ka;
			this.kd = kd;
		}
	}

	private long previousTime = -1;
	private double integratedError = 0.0;
	private double pidValue;
	private List<Profile> profiles = new ArrayList<>();

	private Profile profile = null;
	private PID pid;

	public Feedforward(PID pid) {
		this.pid = pid;
	}

	public int addProfile(double kv, double ka, double kd) {
		this.profiles.add(new Profile(kv, ka, kd));
		return this.profiles.size() - 1;
	}

	public void setProfile(int key) {
		this.profile = this.profiles.get(key);
		this.integratedError = 0;
	}

	public double get(double velocity, double acceleration, double actualVelocity, boolean isInverted) {
		this.pid.set(velocity);
		double error = velocity - actualVelocity;
		double time = 0.0;
		if (this.previousTime != -1) {
			time = (System.currentTimeMillis() - this.previousTime) / 1000.0;
			this.integratedError += error * time;
		}

		this.previousTime = System.currentTimeMillis();

		this.pidValue = this.pid.get(actualVelocity);
		double output = 0.0;

		if (!isInverted) {
			if (acceleration < 0) {
				output = this.profile.kv * velocity + this.profile.kd * acceleration;

			} else {
				output = this.profile.kv * velocity + this.profile.ka * acceleration;
			}
		} else {
			if (acceleration < 0) {
				output = this.profile.kv * velocity - this.profile.kd * acceleration;

			} else {
				output = this.profile.kv * velocity - this.profile.ka * acceleration;
			}
		}

		if (output > 1.0) {
			output = 1.0;
		} else if (output < -1.0) {
			output = -1.0;
		}

		return output + this.pidValue;
	}

	public double getKa(double acceleration, boolean isInverted) {

		return (isInverted ? -1.0 : 1.0) * this.profile.ka * acceleration;
	}

	public PID getPID() {
		return this.pid;
	}

	public double getPidValue() {
		return this.pidValue;
	}

}
