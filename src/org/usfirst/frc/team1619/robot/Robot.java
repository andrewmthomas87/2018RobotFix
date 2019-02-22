package org.usfirst.frc.team1619.robot;

import org.usfirst.frc.team1619.robot.framework.FrameData;
import org.usfirst.frc.team1619.robot.framework.RobotBase;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.MotorProxy;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.TalonSRXMotorGroup;
import org.usfirst.frc.team1619.robot.state.arm.ArmManualWrapper;
import org.usfirst.frc.team1619.robot.state.arm.ArmTeleopControlWrapper;
import org.usfirst.frc.team1619.robot.state.climbingAids.RampHoldWrapper;
import org.usfirst.frc.team1619.robot.state.collector.CollectorEjectWrapper;
import org.usfirst.frc.team1619.robot.state.collector.CollectorHoldWrapper;
import org.usfirst.frc.team1619.robot.state.collector.CollectorIntakeWrapper;
import org.usfirst.frc.team1619.robot.state.drive.DriveManualWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorManualWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorTeleopControlWrapper;
import org.usfirst.frc.team1619.robot.state.elevatorShift.ElevatorShiftHighGearWrapper;
import org.usfirst.frc.team1619.robot.state.elevatorShift.ElevatorShiftLowGear;
import org.usfirst.frc.team1619.robot.state.multi.MultiTeleopZeroWrapper;
import org.usfirst.frc.team1619.robot.state.multi.climb.MultiClimbWrapper;
import org.usfirst.frc.team1619.robot.state.multi.intake.MultiIntakeSequenceWrapper;

public class Robot extends RobotBase {

	private static final In in = In.getInstance();
	private static final Out out = Out.getInstance();
	private static final FrameData frameData = FrameData.getInstance();
	@Override
	public void threadAutonomousInit() {
		super.threadAutonomousInit();
		RobotState.previousheadingError = 0.0;
		
		((TalonSRXMotorGroup) ((MotorProxy) out.motors.get(IO.MOTOR_DRIVE_LEFT)).getMotor(IO.MOTOR_PROXY_DRIVE_LEFT_DEFAULT)).enableCurrentLimiting(false);;
		((TalonSRXMotorGroup) ((MotorProxy) out.motors.get(IO.MOTOR_DRIVE_RIGHT)).getMotor(IO.MOTOR_PROXY_DRIVE_RIGHT_DEFAULT)).enableCurrentLimiting(false);;
		
		MultiAutoChooserCubeHoldWrapper MultiAutoChooser = new MultiAutoChooserCubeHoldWrapper();
		Subsystems.arm.addWrapper(MultiAutoChooser);
		Subsystems.collector.addWrapper(MultiAutoChooser);
		Subsystems.drive.addWrapper(MultiAutoChooser);
		Subsystems.elevator.addWrapper(MultiAutoChooser);

		Subsystems.elevatorShift.addWrapper(new ElevatorShiftHighGearWrapper());
		Subsystems.climbingAids.addWrapper(new RampHoldWrapper());
	}

	@Override
	public void threadTeleopInit() {
		super.threadTeleopInit();
		
//		((TalonSRXMotorGroup) ((MotorProxy) out.motors.get(IO.MOTOR_DRIVE_LEFT)).getMotor(IO.MOTOR_PROXY_DRIVE_LEFT_DEFAULT)).enableCurrentLimiting(true);;
//		((TalonSRXMotorGroup) ((MotorProxy) out.motors.get(IO.MOTOR_DRIVE_RIGHT)).getMotor(IO.MOTOR_PROXY_DRIVE_RIGHT_DEFAULT)).enableCurrentLimiting(true);;

		MultiClimbWrapper multiClimbWrapper = new MultiClimbWrapper();
		MultiTeleopZeroWrapper multiTeleopZeroWrapper = new MultiTeleopZeroWrapper();
		MultiIntakeSequenceWrapper multiIntakeSequenceWrapper = new MultiIntakeSequenceWrapper();

		Subsystems.drive.addWrapper(new DriveManualWrapper());

		Subsystems.elevator.addWrapper(multiClimbWrapper);
		Subsystems.elevator.addWrapper(new ElevatorManualWrapper());
		Subsystems.elevator.addWrapper(multiTeleopZeroWrapper);
		Subsystems.elevator.addWrapper(multiIntakeSequenceWrapper);
		Subsystems.elevator.addWrapper(new ElevatorTeleopControlWrapper());

		Subsystems.elevatorShift.addWrapper(new ElevatorShiftLowGear());
		Subsystems.elevatorShift.addWrapper(new ElevatorShiftHighGearWrapper());

		Subsystems.arm.addWrapper(multiClimbWrapper);
		Subsystems.arm.addWrapper(new ArmManualWrapper());
		Subsystems.arm.addWrapper(multiTeleopZeroWrapper);
		Subsystems.arm.addWrapper(multiIntakeSequenceWrapper);
		Subsystems.arm.addWrapper(new ArmTeleopControlWrapper());

		Subsystems.collector.addWrapper(multiIntakeSequenceWrapper);
		Subsystems.collector.addWrapper(new CollectorEjectWrapper());
		Subsystems.collector.addWrapper(new CollectorIntakeWrapper());
		Subsystems.collector.addWrapper(new CollectorHoldWrapper());

		Subsystems.climbingAids.addWrapper(multiClimbWrapper);

		Subsystems.climbingAids.addWrapper(new RampHoldWrapper());

	}

}