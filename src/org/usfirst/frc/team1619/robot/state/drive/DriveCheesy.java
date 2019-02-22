//package org.usfirst.frc.team1619.robot.state.drive;
//
//import org.usfirst.frc.team1619.robot.IO;
//import org.usfirst.frc.team1619.robot.framework.IO.In;
//import org.usfirst.frc.team1619.robot.framework.IO.Out;
//import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
//import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerAxisSensor;
//import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerButtonSensor;
//import org.usfirst.frc.team1619.robot.framework.state.State;
//
//public class DriveCheesy extends State {
//
//	CheesyDrive cheesyDrive = new CheesyDrive();
//	MotorSpeed motorSpeed = new MotorSpeed(0, 0, false);
//
//	private ControllerButtonSensor rightTrigger;
//	private ControllerAxisSensor yAxis;
//	private ControllerAxisSensor xAxis;
//
//	private Motor driveLeft, driveRight;
//
//	public DriveCheesy(In in, Out out) {
//		this.yAxis = in.get(IO.DRIVER_LEFT_AXIS_Y);
//		this.xAxis = in.get(IO.DRIVER_RIGHT_AXIS_X);
//		this.rightTrigger = in.get(IO.DRIVER_RIGHT_TRIGGER);
//		this.driveLeft = out.motors.get(IO.MOTOR_DRIVE_LEFT);
//		this.driveRight = out.motors.get(IO.MOTOR_DRIVE_RIGHT);
//
//	}
//
//	@Override
//	protected void initialize() {
//
//	}
//
//	@Override
//	protected void update() {
//
//		if (rightTrigger.get()) {
//			if (this.yAxis.get() >= 0) {
//				motorSpeed = cheesyDrive.cheesyDrive(this.yAxis.get(), this.xAxis.get(), true);
//			} else {
//				motorSpeed = cheesyDrive.cheesyDrive(this.yAxis.get(), -1.0 * this.xAxis.get(), true);
//			}
//		} else {
//			if (this.yAxis.get() >= 0) {
//				motorSpeed = cheesyDrive.cheesyDrive(this.yAxis.get(), this.xAxis.get(), false);
//			} else {
//				motorSpeed = cheesyDrive.cheesyDrive(this.yAxis.get(), -1.0 * this.xAxis.get(), false);
//			}
//
//		}
//
//		this.driveRight.set(motorSpeed.rightMotor);
//		this.driveLeft.set(motorSpeed.leftMotor);
//
//	}
//
//	@Override
//	protected void dispose() {
//
//	}
//}
