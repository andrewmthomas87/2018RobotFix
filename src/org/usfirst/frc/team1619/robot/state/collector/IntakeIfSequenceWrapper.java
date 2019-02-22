package org.usfirst.frc.team1619.robot.state.collector;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerButtonSensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class IntakeIfSequenceWrapper extends Wrapper<IntakeIfSequence> {

	private ControllerButtonSensor triggerLeft = in.get(IO.OPERATOR_LEFT_TRIGGER);

	@Override
	protected IntakeIfSequence createState() {
		return new IntakeIfSequence(in, out);
	}

	@Override
	protected boolean isReady() {
		return triggerLeft.get();
	}

	@Override
	protected boolean isDone() {
		return RobotState.isCubeAcquired;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.collector.getId();
	}
}
