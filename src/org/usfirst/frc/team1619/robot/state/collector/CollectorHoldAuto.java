package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class CollectorHoldAuto extends State {

	private Motor motorCollectorTop;
	private Motor motorCollectorBottom;

	public CollectorHoldAuto(In in, Out out) {

		this.motorCollectorTop = out.motors.get(IO.MOTOR_COLLECTOR_TOP);
		this.motorCollectorBottom = out.motors.get(IO.MOTOR_COLLECTOR_BOTTOM);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {

		if (RobotState.isCubeAcquired) {

			this.motorCollectorTop.set(Constants.MOTOR_COLLECTOR_TOP_HOLD_SPEED);
			this.motorCollectorBottom.set(Constants.MOTOR_COLLECTOR_BOTTOM_HOLD_SPEED);
		}

	}

	@Override
	protected void dispose() {

	}
}
