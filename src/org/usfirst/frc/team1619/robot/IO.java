package org.usfirst.frc.team1619.robot;

import java.util.Arrays;
import java.util.HashSet;

import org.usfirst.frc.team1619.robot.controller.ArmPID;
import org.usfirst.frc.team1619.robot.controller.ElevatorPID;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.Motor;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.MotorProxy;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.PositionControlledTalonSRXMotor;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.ServoMotor;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.TalonSRXMotor;
import org.usfirst.frc.team1619.robot.framework.IO.actuator.motor.TalonSRXMotorGroup;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerAxisSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerButtonSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.ControllerButtonSensor.ControllerButton;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.DigitalInputSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NavXHeadingSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.OutputCurrentComparisonSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.OutputCurrentSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.TalonSRXEncoderPositionSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.TalonSRXEncoderVelocitySensor;
import org.usfirst.frc.team1619.robot.framework.controller.HeadingController;
import org.usfirst.frc.team1619.robot.framework.controller.Feedforward;
import org.usfirst.frc.team1619.robot.framework.controller.PID;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IO {

	// Drive
	public static int MOTOR_DRIVE_LEFT, MOTOR_DRIVE_RIGHT;
	public static int MOTOR_PROXY_DRIVE_LEFT_DEFAULT;
	public static int MOTOR_PROXY_DRIVE_RIGHT_DEFAULT;

	public static int SENSOR_DRIVE_LEFT_POSITION;
	public static int SENSOR_DRIVE_RIGHT_POSITION;

	public static int PROFILE_DRIVE_PID_ROTATE_CABLE_GUARD;
	public static int PROFILE_DRIVE_PID_ROTATE;

	public static int SENSOR_DRIVE_LEFT_VELOCITY;
	public static int SENSOR_DRIVE_RIGHT_VELOCITY;

	public static int SENSOR_DRIVE_CURRENT;

	public static PID currentLimiter = new PID();
	public static int PROFILE_DRIVE_CURRENT_LIMTING;

	public static PID driveRotatePID = new PID();

	// PID

	public static HeadingController driveHeadingController = new HeadingController(new Feedforward(new PID()),
			new Feedforward(new PID()), new PID(true));

	public static int PROFILE_DRIVE_LEFT_FEEDFORWARD;
	public static int PROFILE_DRIVE_RIGHT_FEEDFORWARD;

	public static int PROFILE_DRIVE_HEADING_PID;
	public static int PROFILE_DRIVE_LEFT_PID;

	public static int PROFILE_DRIVE_RIGHT_PID;

	// Collector
	public static int MOTOR_COLLECTOR_TOP;
	public static int MOTOR_COLLECTOR_BOTTOM;

	// Elevator
	public static int MOTOR_ELEVATOR;
	public static int MOTOR_PROXY_ELEVATOR_DEFAULT;
	public static int SENSOR_ELEVATOR_POSITION;
	public static int SENSOR_ELEVATOR_VELOCITY;
	public static int MOTOR_PROXY_ELEVATOR_POSITION_CONTROLLED;
	public static int ELEVATOR_BOTTOM_SWITCH;

	// PID
	public static PID elevatorPID = new ElevatorPID();
	public static int PROFILE_ELEVATOR_POSITION_STAGE_1;
	public static int PROFILE_ELEVATOR_POSITION_STAGE_2;

	public static int PROFILE_ELEVATOR_POSITION_STAGE_1_WITH_CUBE;
	public static int PROFILE_ELEVATOR_POSITION_STAGE_2_WITH_CUBE;

	// ARM
	public static int MOTOR_ARM;
	public static int MOTOR_PROXY_ARM_DEFAULT;

	public static int SENSOR_ARM_POSITION;
	public static int SENSOR_ARM_VELOCITY;

	public static int MOTOR_PROXY_ARM_POSITION_CONTROLLED;

	// PID
	public static PID armPID = new ArmPID();
	public static int PROFILE_ARM_POSITION_CUBE;
	public static int PROFILE_ARM_POSITION_NO_CUBE;

	// Xbox Controller
	public static int DRIVER_LEFT_AXIS_Y, DRIVER_LEFT_AXIS_X, DRIVER_RIGHT_AXIS_Y, DRIVER_RIGHT_AXIS_X;

	public static int DRIVER_BUTTON_B, DRIVER_RIGHT_TRIGGER, DRIVER_LEFT_TRIGGER, DRIVER_BUTTON_BACK, DRIVER_BUTTON_A,
			DRIVER_BUTTON_START;

	public static int OPERATOR_LEFT_Y_AXIS, OPERATOR_LEFT_X_AXIS, OPERATOR_RIGHT_Y_AXIS, OPERATOR_RIGHT_X_AXIS;
	public static int OPERATOR_LEFT_TRIGGER, OPERATOR_RIGHT_TRIGGER, OPERATOR_BUTTON_A, OPERATOR_BUTTON_DPAD_LEFT,
			OPERATOR_BUTTON_DPAD_RIGHT, OPERATOR_BUTTON_DPAD_UP, OPERATOR_BUTTON_DPAD_DOWN, OPERATOR_BUTTON_B,
			OPERATOR_BUTTON_X, OPERATOR_BUTTON_Y, OPERATOR_BUTTON_LEFT_BUMPER, OPERATOR_BUTTON_RIGHT_BUMPER,
			OPERATOR_BUTTON_START, OPERATOR_BUTTON_BACK;

	// CUBE SENSOR
	public static int CUBE_SENSOR;

	public static int ELEVATOR_CURRENT_SENSOR;

	public static int SENSOR_NAVX_HEADING;

	// SERVOS
	public static int MOTOR_SHIFT_SERVO;
	public static int MOTOR_LEFT_PLATFORM_SERVO;

	public static void initialize() {
		// In and Out
		Out out = Out.getInstance();
		In in = In.getInstance();

		NavXHeadingSensor headingSensor = new NavXHeadingSensor();
		SENSOR_NAVX_HEADING = in.register(headingSensor);

		// Driver Controller
		DRIVER_RIGHT_AXIS_X = in.register(
				new ControllerAxisSensor(Constants.DRIVER_CONTROLLER_ID, Constants.DRIVER_RIGHT_AXIS_X_ID, true, 0.13));
		DRIVER_LEFT_AXIS_Y = in.register(
				new ControllerAxisSensor(Constants.DRIVER_CONTROLLER_ID, Constants.DRIVER_LEFT_AXIS_Y_ID, true, 0.13));

		DRIVER_BUTTON_A = in.register(new ControllerButtonSensor(Constants.DRIVER_CONTROLLER_ID, ControllerButton.A));

		DRIVER_BUTTON_B = in.register(new ControllerButtonSensor(Constants.DRIVER_CONTROLLER_ID, ControllerButton.B));

		DRIVER_RIGHT_TRIGGER = in
				.register(new ControllerButtonSensor(Constants.DRIVER_CONTROLLER_ID, ControllerButton.RIGHT_TRIGGER));

		DRIVER_LEFT_TRIGGER = in
				.register(new ControllerButtonSensor(Constants.DRIVER_CONTROLLER_ID, ControllerButton.LEFT_TRIGGER));

		DRIVER_BUTTON_BACK = in
				.register(new ControllerButtonSensor(Constants.DRIVER_CONTROLLER_ID, ControllerButton.BACK));
		DRIVER_BUTTON_START = in
				.register(new ControllerButtonSensor(Constants.DRIVER_CONTROLLER_ID, ControllerButton.START));

		// Operator Controller
		OPERATOR_BUTTON_DPAD_LEFT = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.D_PAD_LEFT));
		OPERATOR_BUTTON_DPAD_RIGHT = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.D_PAD_RIGHT));
		OPERATOR_BUTTON_DPAD_UP = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.D_PAD_UP));
		OPERATOR_BUTTON_DPAD_DOWN = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.D_PAD_DOWN));
		OPERATOR_BUTTON_A = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.A));
		OPERATOR_BUTTON_B = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.B));
		OPERATOR_BUTTON_X = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.X));
		OPERATOR_BUTTON_Y = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.Y));
		OPERATOR_BUTTON_START = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.START));
		OPERATOR_BUTTON_BACK = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.BACK));

		// Operator Controller

		OPERATOR_LEFT_TRIGGER = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.LEFT_TRIGGER));
		OPERATOR_RIGHT_TRIGGER = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.RIGHT_TRIGGER));
		OPERATOR_LEFT_Y_AXIS = in.register(new ControllerAxisSensor(Constants.OPERATOR_CONTROLLER_ID,
				Constants.OPERATOR_LEFT_AXIS_Y_ID, true, 0.13));
		OPERATOR_RIGHT_Y_AXIS = in.register(new ControllerAxisSensor(Constants.OPERATOR_CONTROLLER_ID,
				Constants.OPERATOR_RIGHT_AXIS_Y_ID, false, 0.13));
		OPERATOR_BUTTON_RIGHT_BUMPER = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.RIGHT_BUMPER));
		OPERATOR_BUTTON_LEFT_BUMPER = in
				.register(new ControllerButtonSensor(Constants.OPERATOR_CONTROLLER_ID, ControllerButton.LEFT_BUMPER));

		// DRIVE
		// Base Drive Motors
		TalonSRX motorDriveLeft = new TalonSRX(Constants.MOTOR_DRIVE_LEFT_ID);
		TalonSRX motorDriveLeftSlave = new TalonSRX(Constants.MOTOR_DRIVE_LEFT_SLAVE_ID);
		TalonSRX motorDriveRight = new TalonSRX(Constants.MOTOR_DRIVE_RIGHT_ID);
		TalonSRX motorDriveRightSlave = new TalonSRX(Constants.MOTOR_DRIVE_RIGHT_SLAVE_ID);

		PROFILE_DRIVE_CURRENT_LIMTING = currentLimiter.addProfile(0.0, 0.0, 0.0, 0.012, 0.0, 0.0, -1.0 / 0.012, -1,
				1.0);

		// Current Sensor
		SENSOR_DRIVE_CURRENT = in.register(
				new OutputCurrentSensor(motorDriveLeft, motorDriveLeftSlave, motorDriveRight, motorDriveRightSlave));

		// Drive Proxy
		MotorProxy driveLeftProxy = new MotorProxy();
		MotorProxy driveRightProxy = new MotorProxy();
		MOTOR_DRIVE_LEFT = out.motors.register(driveLeftProxy);
		MOTOR_DRIVE_RIGHT = out.motors.register(driveRightProxy);

		// Grouping Drivetrain motors
		TalonSRXMotorGroup driveLeftCANTalonMotorGroup = new TalonSRXMotorGroup(motorDriveLeft, NeutralMode.Brake,
				false, motorDriveLeftSlave);
		TalonSRXMotorGroup driveRightCANTalonMotorGroup = new TalonSRXMotorGroup(motorDriveRight, NeutralMode.Brake,
				true, motorDriveRightSlave);

		// Default Config
		MOTOR_PROXY_DRIVE_LEFT_DEFAULT = driveLeftProxy.addMotor(driveLeftCANTalonMotorGroup);
		MOTOR_PROXY_DRIVE_RIGHT_DEFAULT = driveRightProxy.addMotor(driveRightCANTalonMotorGroup);
		driveLeftProxy.setMotor(MOTOR_PROXY_DRIVE_LEFT_DEFAULT);
		driveRightProxy.setMotor(MOTOR_PROXY_DRIVE_RIGHT_DEFAULT);

		// Drive Position Sensors
		TalonSRXEncoderPositionSensor driveLeftPositionSensor = new TalonSRXEncoderPositionSensor(motorDriveLeft, false,
				FeedbackDevice.QuadEncoder, Constants.QUAD_ENCODER_CYCLES_PER_REVOLUTION, Constants.REVOLUTION_PER_UNIT,
				"feet");
		TalonSRXEncoderPositionSensor driveRightPositionSensor = new TalonSRXEncoderPositionSensor(motorDriveRight,
				true, FeedbackDevice.QuadEncoder, Constants.QUAD_ENCODER_CYCLES_PER_REVOLUTION,
				Constants.REVOLUTION_PER_UNIT, "feet");

		SENSOR_DRIVE_LEFT_POSITION = in.register(driveLeftPositionSensor);
		SENSOR_DRIVE_RIGHT_POSITION = in.register(driveRightPositionSensor);

		// Drive Velocity Sensors
		NumericSensor driveLeftVelocitySensor = new TalonSRXEncoderVelocitySensor(motorDriveLeft, false,
				FeedbackDevice.QuadEncoder, Constants.QUAD_ENCODER_CYCLES_PER_REVOLUTION, Constants.REVOLUTION_PER_UNIT,
				"feet per second");
		NumericSensor driveRightVelocitySensor = new TalonSRXEncoderVelocitySensor(motorDriveRight, true,
				FeedbackDevice.QuadEncoder, Constants.QUAD_ENCODER_CYCLES_PER_REVOLUTION, Constants.REVOLUTION_PER_UNIT,
				"feet per second");

		SENSOR_DRIVE_LEFT_VELOCITY = in.register(driveLeftVelocitySensor);
		SENSOR_DRIVE_RIGHT_VELOCITY = in.register(driveRightVelocitySensor);

		// Drive PID Profiles

		driveHeadingController.setMotors(driveLeftCANTalonMotorGroup, driveRightCANTalonMotorGroup);
		driveHeadingController.setSenors(driveLeftPositionSensor, driveRightPositionSensor, driveLeftVelocitySensor,
				driveRightVelocitySensor, headingSensor);

		PROFILE_DRIVE_LEFT_FEEDFORWARD = driveHeadingController.getLeftFeedforward().addProfile(0.075, 0.025, 0.018);
		PROFILE_DRIVE_RIGHT_FEEDFORWARD = driveHeadingController.getRightFeedforward().addProfile(0.075, 0.025, 0.018);

		PROFILE_DRIVE_LEFT_PID = driveHeadingController.getLeftFeedforward().getPID().addProfile(0.0, 0.0, 0.175, 1.12,
				0.0, 1.0 / 0.07, 1.0, 1.0);
		PROFILE_DRIVE_RIGHT_PID = driveHeadingController.getRightFeedforward().getPID().addProfile(0.0, 0.0, 0.175,
				1.12, 0.0, 1.0 / 0.07, 1.0, 1.0);

		PROFILE_DRIVE_HEADING_PID = driveHeadingController.getHeadingPID().addProfile(0.0, 0.0, 0.014, 0.007, 0.0, 10.0,
				1.0);

		// TODO: Tune these values more
		PROFILE_DRIVE_PID_ROTATE_CABLE_GUARD = driveRotatePID.addProfile(0.0, 0.0, 0.164, 0.014, 0.0, 1.0);
		PROFILE_DRIVE_PID_ROTATE = driveRotatePID.addProfile(0.0, 0.0, 0.07, 0.04, 0.035, 10.0, 10, 1.0);

		// Collector
		TalonSRX motorCollectorTop = new TalonSRX(Constants.MOTOR_COLLECTOR_TOP_ID);
		MOTOR_COLLECTOR_TOP = out.motors.register(new TalonSRXMotor(motorCollectorTop, NeutralMode.Brake, true));
		TalonSRX motorCollectorBottom = new TalonSRX(Constants.MOTOR_COLLECTOR_BOTTOM_ID);
		MOTOR_COLLECTOR_BOTTOM = out.motors.register(new TalonSRXMotor(motorCollectorBottom, NeutralMode.Brake, false));

		// Elevator
		// Base Elevator Motors
		TalonSRX motorElevator1 = new TalonSRX(Constants.MOTOR_ELEVATOR_PRIMARY_ID);
		TalonSRX motorElevator2 = new TalonSRX(Constants.MOTOR_ELEVATOR_SECONDARY_ID);
		TalonSRX motorElevator3 = new TalonSRX(Constants.MOTOR_ELEVATOR_TERTIARY_ID);

		// Elevator Proxy
		MotorProxy elevatorProxy = new MotorProxy();
		MOTOR_ELEVATOR = out.motors.register(elevatorProxy);

		// Grouping Elevator
		TalonSRXMotorGroup elevatorMotorGroup = new TalonSRXMotorGroup(motorElevator1, NeutralMode.Brake, false,
				new HashSet<>(Arrays.asList(motorElevator2, motorElevator3)));
		MOTOR_PROXY_ELEVATOR_DEFAULT = elevatorProxy.addMotor(elevatorMotorGroup);
		elevatorProxy.setMotor(MOTOR_PROXY_ELEVATOR_DEFAULT);

		// Position Sensor
		NumericSensor elevatorPositionSensor = new TalonSRXEncoderPositionSensor(motorElevator1, false,
				FeedbackDevice.QuadEncoder, Constants.ELEVATOR_CYCLES_PER_REVOLUTION,
				Constants.ELEVATOR_REVOLUTION_PER_UNIT, "inches");
		SENSOR_ELEVATOR_POSITION = in.register(elevatorPositionSensor);

		// Velocity Sensor
		NumericSensor elevatorVelocitySensor = new TalonSRXEncoderVelocitySensor(motorElevator1, false,
				FeedbackDevice.QuadEncoder, Constants.ELEVATOR_CYCLES_PER_REVOLUTION,
				Constants.ELEVATOR_REVOLUTION_PER_UNIT, "inches per second");
		SENSOR_ELEVATOR_VELOCITY = in.register(elevatorVelocitySensor);

		// Position Controlled
		MOTOR_PROXY_ELEVATOR_POSITION_CONTROLLED = elevatorProxy
				.addMotor(new PositionControlledTalonSRXMotor(elevatorMotorGroup, elevatorPID, elevatorPositionSensor));

		PROFILE_ELEVATOR_POSITION_STAGE_1 = elevatorPID.addProfile(0.075, 0.0, 0.07, 0.0, 0.0, 1.0);
		PROFILE_ELEVATOR_POSITION_STAGE_2 = elevatorPID.addProfile(0.075, 0.0, 0.08, 0.0, 0.0, 1.0);

		PROFILE_ELEVATOR_POSITION_STAGE_1_WITH_CUBE = elevatorPID.addProfile(0.075, 0.0, 0.08, 0.0, 0.0, 1.0);
		PROFILE_ELEVATOR_POSITION_STAGE_2_WITH_CUBE = elevatorPID.addProfile(0.075, 0.0, 0.09, 0.0, 0.0, 1.0);

		ELEVATOR_BOTTOM_SWITCH = in.register(new DigitalInputSensor(0, false));

		// Arm
		// Arm Motor
		TalonSRX motorArmSRX = new TalonSRX(Constants.MOTOR_ARM_ID);
		Motor motorArm = new TalonSRXMotor(motorArmSRX, NeutralMode.Brake, true);

		// Arm Proxy
		MotorProxy armProxy = new MotorProxy();
		MOTOR_ARM = out.motors.register(armProxy);
		// Default Config
		MOTOR_PROXY_ARM_DEFAULT = armProxy.addMotor(motorArm);
		armProxy.setMotor(MOTOR_PROXY_ARM_DEFAULT);

		// Position Sensor
		NumericSensor armPositionSensor = new TalonSRXEncoderPositionSensor(motorArmSRX, true,
				FeedbackDevice.QuadEncoder, Constants.ARM_CYCLES_PER_REVOLUTION, Constants.ARM_REVOLUTION_PER_UNIT,
				"feet");
		SENSOR_ARM_POSITION = in.register(armPositionSensor);

		// Velocity Sensor
		NumericSensor armVelocitySensor = new TalonSRXEncoderVelocitySensor(motorArmSRX, false,
				FeedbackDevice.QuadEncoder, Constants.ARM_CYCLES_PER_REVOLUTION, Constants.ARM_REVOLUTION_PER_UNIT,
				"feet per second");
		SENSOR_ARM_VELOCITY = in.register(armVelocitySensor);

		MOTOR_PROXY_ARM_POSITION_CONTROLLED = armProxy
				.addMotor(new PositionControlledTalonSRXMotor(motorArm, armPID, armPositionSensor));

		PROFILE_ARM_POSITION_CUBE = armPID.addProfile(0.1, 0.0, 0.035, 0.0, 0.3, 0.75);
		PROFILE_ARM_POSITION_NO_CUBE = armPID.addProfile(0.0, 0.0, 0.025, 0.0, 0.3, 0.75);

		ELEVATOR_CURRENT_SENSOR = in.register(new OutputCurrentComparisonSensor(motorElevator1, 1));

		// CUBE SENSORS
		CUBE_SENSOR = in.register(new OutputCurrentComparisonSensor(motorCollectorTop,
				Constants.COLLECTOR_OUTPUT_CURRENT_CUBE_ACQUIRED_DURING_INTAKE));

		// SERVOS
		MOTOR_SHIFT_SERVO = out.motors.register(new ServoMotor(8));
		MOTOR_LEFT_PLATFORM_SERVO = out.motors.register(new ServoMotor(7));

	}
}