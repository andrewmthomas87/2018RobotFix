package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;

public class ArmIdlePreClimbWrapper extends ArmIdleWrapper {
	
	private BooleanSensor toggleManual = in.get(IO.OPERATOR_BUTTON_DPAD_DOWN);

	public ArmIdlePreClimbWrapper(double setpoint) {
		super(setpoint);
	}

	@Override
	protected boolean isDone() {
		return this.toggleManual.get();
	}

}
