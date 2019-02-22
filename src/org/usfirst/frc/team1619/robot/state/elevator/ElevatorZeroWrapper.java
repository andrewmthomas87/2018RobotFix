package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.TalonSRXEncoderVelocitySensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class ElevatorZeroWrapper extends Wrapper<ElevatorZero> {

	private TalonSRXEncoderVelocitySensor elevatorVelocitySensor = in.get(IO.SENSOR_ELEVATOR_VELOCITY);
	private BooleanSensor elevatorBottom = in.get(IO.ELEVATOR_BOTTOM_SWITCH);
	private Timer timer = new Timer();
	private boolean hasBeenZeroed;

	@Override
	protected ElevatorZero createState() {
		return new ElevatorZero(in, out);
	}

	@Override
	protected boolean isReady() {
		return !this.hasBeenZeroed;
	}

	@Override
	protected boolean isDone() {
		if (this.elevatorBottom.get()) {
			if (!this.timer.isStarted()) {
				this.timer.start(Constants.ELEVATOR_ZERO_WAIT_TIME);
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
		return subsystemId == Subsystems.elevator.getId();
	}

}
