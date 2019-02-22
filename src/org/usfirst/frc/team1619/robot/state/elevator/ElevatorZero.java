package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.TalonSRXEncoderPositionSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class ElevatorZero extends State{
	
	private Motor elevator;
	
	private TalonSRXEncoderPositionSensor elevatorPositionSensor;
	
	public ElevatorZero(In in, Out out) {
		this.elevator = out.motors.get(IO.MOTOR_ELEVATOR);
		this.elevatorPositionSensor = in.get(IO.SENSOR_ELEVATOR_POSITION);
		
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void update() {
		this.elevator.set(Constants.ELEVATOR_ZERO_OUTPUT);
	}

	@Override
	protected void dispose() {
		this.elevatorPositionSensor.zero();
		
	}

}
