package org.usfirst.frc.team1619.robot.state.elevatorShift;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.ServoMotor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ServoIdle extends State {

	private ServoMotor servo;

	public ServoIdle(In in, Out out) {
		this.servo = in.get(IO.MOTOR_SHIFT_SERVO);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {
		this.servo.set(-10);

	}

	@Override
	protected void dispose() {
		
	}

}
