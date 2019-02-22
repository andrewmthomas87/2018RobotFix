package org.usfirst.frc.team1619.robot.framework.IO.sensor;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OutputCurrentComparisonSensor extends BooleanSensor {

	private TalonSRX motor;
	private double comparisonValue;

	public OutputCurrentComparisonSensor(TalonSRX motor, double comparisonValue) {
		this.motor = motor;
		this.comparisonValue = comparisonValue;
	}

	@Override
	protected boolean getValue() {
		// SmartDashboard.putNumber("current", this.motor.getOutputCurrent());
		return this.motor.getOutputCurrent() > this.comparisonValue;
	}

}