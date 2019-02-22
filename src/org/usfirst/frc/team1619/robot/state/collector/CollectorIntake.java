package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class CollectorIntake extends State {

	private Motor motorCollectorTop;
	private Motor motorCollectorBottom;
	private BooleanSensor cubeSensor;

	private Timer timer = new Timer();

	public CollectorIntake(In in, Out out) {

		this.motorCollectorTop = out.motors.get(IO.MOTOR_COLLECTOR_TOP);
		this.motorCollectorBottom = out.motors.get(IO.MOTOR_COLLECTOR_BOTTOM);
		this.cubeSensor = in.get(IO.CUBE_SENSOR);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {
		if (this.cubeSensor.get()) {
			if (!this.timer.isStarted()) {
				this.timer.start(Constants.CUBE_COLLECTED_WAIT_TIME);
			}

		} else if (timer.isStarted()) {
			timer.reset();
		}
		
		if (timer.isDone()) {
			RobotState.isCubeAcquired = true;
		}
		
		this.motorCollectorTop.set(Constants.MOTOR_COLLECTOR_TOP_INTAKE_SPEED);
		this.motorCollectorBottom.set(Constants.MOTOR_COLLECTOR_BOTTOM_INTAKE_SPEED);
	}

	@Override
	protected void dispose() {

	}
}
