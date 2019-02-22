package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.state.State;
import org.usfirst.frc.team1619.robot.misc.EjectSpeedProfile;

public class CollectorEject extends State {

	private Motor motorCollectorTop;
	private Motor motorCollectorBottom;

	private double topEjectSpeed;
	private double bottomEjectSpeed;

	public CollectorEject(In in, Out out, EjectSpeedProfile ejectSpeedProfile) {

		this.motorCollectorTop = out.motors.get(IO.MOTOR_COLLECTOR_TOP);
		this.motorCollectorBottom = out.motors.get(IO.MOTOR_COLLECTOR_BOTTOM);

		this.topEjectSpeed = ejectSpeedProfile.getTopEjectSpeed();
		this.bottomEjectSpeed = ejectSpeedProfile.getBottomEjectSpeed();
	}

	@Override
	protected void initialize() {
		RobotState.isCubeAcquired = false;
	}

	@Override
	protected void update() {

		this.motorCollectorTop.set(topEjectSpeed);
		this.motorCollectorBottom.set(bottomEjectSpeed);
	}

	@Override
	protected void dispose() {
		RobotState.isCubeAcquired = false;
	}
}
