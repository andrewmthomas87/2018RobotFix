package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class ArmSetVoltageWrapper extends Wrapper<ArmSetVoltage> {

	private double voltage;
	private int time;
	private Timer timer = new Timer();

	public ArmSetVoltageWrapper(double voltage, int time) {
		this.voltage = voltage;
		this.time = time;
	}

	@Override
	protected ArmSetVoltage createState() {
		return new ArmSetVoltage(this.voltage, in, out);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		if (time == -1) {
			return false;
		} else {
			if (!this.timer.isStarted()) {
				this.timer.start(this.time);
			}

			return timer.isDone();
		}

	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.arm.getId();
	}

}