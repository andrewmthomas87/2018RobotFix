package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerAxisSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ArmManual extends State {

	private ControllerAxisSensor operatorRightYAxis;
	private Motor armMotor;

	public ArmManual(In in, Out out) {
		this.operatorRightYAxis = in.get(IO.OPERATOR_RIGHT_Y_AXIS);
		this.armMotor = out.motors.get(IO.MOTOR_ARM);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {
		this.armMotor.set(this.operatorRightYAxis.get());
	}

	@Override
	protected void dispose() {

	}

}
