package org.usfirst.frc.team1619.robot.controller;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.controller.PID;

public class ElevatorPID extends PID {

	@Override
	public double get(double measuredValue) {
		double normalValue = super.get(measuredValue);
		if (this.getError(measuredValue) < -6.00) {
			double output = normalValue + Constants.ELEVATOR_SLOW_DOWN_OUTPUT;
			return output;
		}

		return normalValue;
	}

}
