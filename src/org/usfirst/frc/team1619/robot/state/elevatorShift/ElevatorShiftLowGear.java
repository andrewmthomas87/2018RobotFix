package org.usfirst.frc.team1619.robot.state.elevatorShift;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerButtonSensor;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class ElevatorShiftLowGear extends Wrapper<ElevatorShiftSet> {

	private ControllerButtonSensor shift = in.get(IO.OPERATOR_BUTTON_DPAD_LEFT);
	private ControllerButtonSensor overrideButton = in.get(IO.DRIVER_BUTTON_BACK);
	private Timer timer = new Timer();

	@Override
	protected ElevatorShiftSet createState() {
		// return new ElevatorShiftSet(in, out, 0.4, 50, 300, 300); //practice bot
		return new ElevatorShiftSet(in, out, 0.8, 50, 300, 300); // competition bot
	}

	@Override
	protected boolean isReady() {

		if (!this.timer.isStarted()) {
			this.timer.start(105000);
		}

		return (this.overrideButton.get() || this.timer.isDone()) && this.shift.getDelta() == 1 && !RobotState.highGear;

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
