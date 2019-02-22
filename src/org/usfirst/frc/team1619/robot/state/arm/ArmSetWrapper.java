package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class ArmSetWrapper extends Wrapper<ArmSet> {

	private double setpoint;
	private int timeInAcceptableRadius;
	private double acceptableRadius;

	private Timer timer = new Timer();
	private NumericSensor armPositionSensor = in.get(IO.SENSOR_ARM_POSITION);

	public ArmSetWrapper(double setpoint, double acceptableRadius, int timeInAcceptableRadius) {
		this.setpoint = setpoint;
		this.acceptableRadius = acceptableRadius;
		this.timeInAcceptableRadius = timeInAcceptableRadius;

	}

	@Override
	protected ArmSet createState() {
		return new ArmSet(in, out, setpoint);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		if (Math.abs(armPositionSensor.get() - this.setpoint) <= this.acceptableRadius) {
			if (!this.timer.isStarted()) {
				this.timer.start(this.timeInAcceptableRadius);
			}

		} else if (timer.isStarted()) {
			timer.reset();
		}

		return timer.isDone();

	}

	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}

	public double getSetpoint() {
		return this.setpoint;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return true;
	}

}
