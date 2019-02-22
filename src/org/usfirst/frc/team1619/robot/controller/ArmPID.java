package org.usfirst.frc.team1619.robot.controller;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.framework.controller.PID;

public class ArmPID extends PID {

	@Override
	public double get(double measuredValue) {

		double normalValue = super.get(measuredValue);
		if (measuredValue > Constants.MIN_ARM_POSITION_RESTRICTED_SIDE_BELOW_STAGE_1
				&& this.getError(measuredValue) > 6.00) {

			double output = normalValue + Constants.ARM_FLIP_COMPENSATION;
			return output;
		}

		return normalValue;
	}

}
