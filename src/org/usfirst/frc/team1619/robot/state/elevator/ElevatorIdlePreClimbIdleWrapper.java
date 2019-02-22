package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;

public class ElevatorIdlePreClimbIdleWrapper extends ElevatorIdleWrapper {
	
	private BooleanSensor toggleManual = in.get(IO.OPERATOR_BUTTON_DPAD_DOWN);

	public ElevatorIdlePreClimbIdleWrapper(double setpoint) {
		super(setpoint);
	}
	
	@Override
	protected boolean isDone() {
		return this.toggleManual.get();
	}

}
