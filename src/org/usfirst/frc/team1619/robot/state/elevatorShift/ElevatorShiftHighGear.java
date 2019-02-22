package org.usfirst.frc.team1619.robot.state.elevatorShift;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.ServoMotor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ElevatorShiftHighGear extends State {

	private ServoMotor motor;

	public ElevatorShiftHighGear(In in, Out out) {
		this.motor = (ServoMotor) out.motors.get(IO.MOTOR_SHIFT_SERVO);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {
		this.motor.set(0.35); // comp bot
		// this.motor.set(0.0); //practice bot.

	}

	@Override
	protected void dispose() {

	}

}
