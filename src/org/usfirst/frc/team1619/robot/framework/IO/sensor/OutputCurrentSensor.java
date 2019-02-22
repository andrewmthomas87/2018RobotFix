package org.usfirst.frc.team1619.robot.framework.IO.sensor;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class OutputCurrentSensor extends NumericSensor {

	private TalonSRX motor1;
	private TalonSRX motor2;
	private TalonSRX motor3;
	private TalonSRX motor4;

	public OutputCurrentSensor(TalonSRX motor1, TalonSRX motor2, TalonSRX motor3, TalonSRX motor4) {
		super(false);
		this.motor1 = motor1;
		this.motor2 = motor2;
		this.motor3 = motor3;
		this.motor4 = motor4;

	}

	@Override
	protected double getValue() {
		return (this.motor1.getOutputCurrent() + this.motor2.getOutputCurrent() + this.motor3.getOutputCurrent()
				+ this.motor4.getOutputCurrent()) / 4.0;
	}
}
