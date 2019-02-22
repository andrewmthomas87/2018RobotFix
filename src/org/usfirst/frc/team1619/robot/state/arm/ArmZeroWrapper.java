package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.TalonSRXEncoderVelocitySensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class ArmZeroWrapper extends Wrapper<ArmZero> {

	private TalonSRXEncoderVelocitySensor armVelocitySensor = in.get(IO.SENSOR_ARM_VELOCITY);
	private Timer timer = new Timer();
	private boolean hasBeenZeroed;

	@Override
	protected ArmZero createState() {
		return new ArmZero(in, out);
	}

	@Override
	protected boolean isReady() {
		return !this.hasBeenZeroed;
	}

	@Override
	protected boolean isDone() {
		if (Math.abs(armVelocitySensor.get()) <= 0.5) {
			if (!this.timer.isStarted()) {
				this.timer.start(Constants.ARM_ZERO_WAIT_TIME);
			}

		} else if (timer.isStarted()) {
			timer.reset();
		}

		if (timer.isDone()) {
			this.hasBeenZeroed = true;
			return true;
		}

		return false;

	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.arm.getId();
	}

}
