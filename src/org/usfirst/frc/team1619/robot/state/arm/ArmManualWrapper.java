package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class ArmManualWrapper extends Wrapper<ArmManual> {

	private BooleanSensor manualToggle = in.get(IO.OPERATOR_BUTTON_BACK);

	@Override
	protected ArmManual createState() {
		return new ArmManual(in, out);
	}

	@Override
	protected boolean isReady() {
		return this.manualToggle.getDelta() == 1;
	}

	@Override
	protected boolean isDone() {
		return this.manualToggle.getDelta() == 1;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.arm.getId();
	}

}
