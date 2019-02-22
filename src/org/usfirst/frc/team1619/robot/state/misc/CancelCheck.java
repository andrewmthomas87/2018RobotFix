package org.usfirst.frc.team1619.robot.state.misc;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class CancelCheck extends State {

	private BooleanSensor cancelButton;

	public CancelCheck(In in, Out out) {
		this.cancelButton = in.get(IO.OPERATOR_BUTTON_RIGHT_BUMPER);
		
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void update() {
		if (this.cancelButton.get()) {
			RobotState.cancelZero = true;
		}

	}

	@Override
	protected void dispose() {

	}

}
