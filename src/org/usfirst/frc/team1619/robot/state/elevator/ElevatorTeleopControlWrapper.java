package org.usfirst.frc.team1619.robot.state.elevator;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class ElevatorTeleopControlWrapper extends Wrapper<ElevatorTeleopControl>{

	@Override
	protected ElevatorTeleopControl createState() {
		return new ElevatorTeleopControl(in, out);
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
		return subsystemId == Subsystems.elevator.getId();
	}

}
