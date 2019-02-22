package org.usfirst.frc.team1619.robot.state.climbingAids;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.ServoMotor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class RampDeploy extends State {

	private ServoMotor servoMotor;
	private BooleanSensor operatorDpadRight;
	private boolean in = false;

	public RampDeploy(In in, Out out) {
		this.servoMotor = (ServoMotor) out.motors.get(IO.MOTOR_LEFT_PLATFORM_SERVO);
		this.operatorDpadRight = in.get(IO.OPERATOR_BUTTON_DPAD_RIGHT);

	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {

		if (this.operatorDpadRight.getDelta() == 1) {
			this.in = true;
		}

		if (this.in) {
			this.servoMotor.set(0.0);
		} else {
			this.servoMotor.set(1.00);
		}

	}

	@Override
	protected void dispose() {

	}

}
