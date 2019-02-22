package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class ElevatorSetWrapper extends Wrapper<ElevatorSet> {

	private double setpoint;
	private double acceptableRadius;
	private int timeInAcceptableRange;
	private NumericSensor elevatorPositionSensor = in.get(IO.SENSOR_ELEVATOR_POSITION);
	private Timer timer = new Timer();

	public ElevatorSetWrapper(double setpoint, double acceptableRadius, int timeInAcceptableRange) {
		this.setpoint = setpoint;

		this.acceptableRadius = acceptableRadius;
		this.timeInAcceptableRange = timeInAcceptableRange;

	}

	@Override
	protected ElevatorSet createState() {
		return new ElevatorSet(in, out, this.setpoint);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {

		if (Math.abs(elevatorPositionSensor.get() - this.setpoint) <= this.acceptableRadius) {
			if (!this.timer.isStarted()) {
				this.timer.start(this.timeInAcceptableRange);
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
		return subsystemId == Subsystems.elevator.getId();
	}

}
