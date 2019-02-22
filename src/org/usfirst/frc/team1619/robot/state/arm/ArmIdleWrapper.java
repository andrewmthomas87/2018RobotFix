package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class ArmIdleWrapper extends Wrapper<ArmIdle> {

	protected double setpoint;

	public ArmIdleWrapper(double setpoint) {
		this.setpoint = setpoint;
	}

	@Override
	protected ArmIdle createState() {
		return new ArmIdle(in, out, setpoint);
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
	
	public double getSetpoint() {
		return setpoint;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.arm.getId();
	}

}
