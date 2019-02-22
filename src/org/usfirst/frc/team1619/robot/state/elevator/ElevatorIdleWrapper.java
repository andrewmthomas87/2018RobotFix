package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class ElevatorIdleWrapper extends Wrapper<ElevatorIdle> {

	protected double setpoint;

	public ElevatorIdleWrapper(double setpoint) {
		this.setpoint = setpoint;
	}

	@Override
	protected ElevatorIdle createState() {
		return new ElevatorIdle(in, out, setpoint);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		return false;
	}

	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.elevator.getId();
	}

}
