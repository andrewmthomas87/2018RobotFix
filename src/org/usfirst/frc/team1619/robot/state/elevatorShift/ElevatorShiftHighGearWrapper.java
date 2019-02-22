package org.usfirst.frc.team1619.robot.state.elevatorShift;

import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;

public class ElevatorShiftHighGearWrapper extends Wrapper<ElevatorShiftHighGear> {

	@Override
	protected ElevatorShiftHighGear createState() {
		return new ElevatorShiftHighGear(in, out);

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
		return subsystemId == Subsystems.elevatorShift.getId();
	}

}
