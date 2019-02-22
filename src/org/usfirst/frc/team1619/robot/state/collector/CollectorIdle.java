package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class CollectorIdle extends State {

	private Motor motorCollectorTop;
	private Motor motorCollectorBottom;

	public CollectorIdle(In in, Out out) {
		
		this.motorCollectorTop = out.motors.get(IO.MOTOR_COLLECTOR_TOP);
		this.motorCollectorBottom = out.motors.get(IO.MOTOR_COLLECTOR_BOTTOM);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {

		this.motorCollectorTop.set(0);
		this.motorCollectorBottom.set(0);
	}

	@Override
	protected void dispose() {

	}
}
