package org.usfirst.frc.team1619.robot.state.elevatorShift;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.ServoMotor;
import org.usfirst.frc.team1619.robot.framework.state.State;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class ElevatorShiftSet extends State {

	private double position;
	private int timeOn;
	private int timeOff;
	private int timeInit;

	private Timer timer = new Timer();

	private boolean pulse = true;

	private ServoMotor motor;

	public ElevatorShiftSet(In in, Out out, double position, int timeOn, int timeOff, int timeInit) {
		this.position = position;
		this.timeOff = timeOff;
		this.timeOn = timeOn;
		this.timeInit = timeInit;

		this.motor = (ServoMotor) out.motors.get(IO.MOTOR_SHIFT_SERVO);
	}

	@Override
	protected void initialize() {
		timer.start(this.timeInit);

	}

	@Override
	protected void update() {
		if (timer.isDone()) {
			this.pulse = !this.pulse;
			if (this.pulse) {
				this.timer.start(this.timeOn);
			} else {
				this.timer.start(this.timeOff);
			}
		}

		if (this.pulse) {
			this.motor.set(this.position);
		} else {
			this.motor.disable();
		}

	}

	@Override
	protected void dispose() {

	}

}
