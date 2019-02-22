package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerAxisSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ElevatorManual extends State {

	private BooleanSensor elevatorLimitSwitch;
	private ControllerAxisSensor operatorLeftYAxis;
	private Motor elevatorMotor;

	public ElevatorManual(In in, Out out) {
		this.operatorLeftYAxis = in.get(IO.OPERATOR_LEFT_Y_AXIS);
		this.elevatorMotor = out.motors.get(IO.MOTOR_ELEVATOR);
		this.elevatorLimitSwitch = in.get(IO.ELEVATOR_BOTTOM_SWITCH);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {

		if (!(this.elevatorLimitSwitch.get() && this.operatorLeftYAxis.get() < 0) && RobotState.highGear) {
			this.elevatorMotor.set(this.operatorLeftYAxis.get());
		}

		if (!(this.elevatorLimitSwitch.get() && this.operatorLeftYAxis.get() > 0) && !RobotState.highGear) {
			this.elevatorMotor.set(this.operatorLeftYAxis.get());
		}

	}

	@Override
	protected void dispose() {

	}

}
