package org.usfirst.frc.team1619.robot.state.climbingAids;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.ServoMotor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class RampHold extends State {

	private ServoMotor servoMotor;
	private boolean in;

	public RampHold(In in, Out out) {
		this.servoMotor = (ServoMotor) out.motors.get(IO.MOTOR_LEFT_PLATFORM_SERVO);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {

		this.servoMotor.set(1.0);

	}

	@Override
	protected void dispose() {

	}

}