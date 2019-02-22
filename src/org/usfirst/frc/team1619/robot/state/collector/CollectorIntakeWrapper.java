package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerButtonSensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class CollectorIntakeWrapper extends Wrapper<CollectorIntake> {

	private ControllerButtonSensor intakeOverride = in.get(IO.OPERATOR_BUTTON_START);

	@Override
	protected CollectorIntake createState() {
		return new CollectorIntake(in, out);
	}

	@Override
	protected boolean isReady() {
		return intakeOverride.get();
	}

	@Override
	protected boolean isDone() {
		return !intakeOverride.get() || RobotState.isCubeAcquired;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.collector.getId();
	}
}
