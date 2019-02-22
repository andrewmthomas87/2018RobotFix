package org.usfirst.frc.team1619.robot.state.arm;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class ArmTeleopControlWrapper extends Wrapper<ArmTeleopControl> {

	@Override
	protected ArmTeleopControl createState() {
		return new ArmTeleopControl(in, out);
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		return false;
	}

	@Override
	public boolean isSubsystemValid(int subsystemId) {
		return subsystemId == Subsystems.arm.getId();
	}

}
