package org.usfirst.frc.team1619.robot;

import org.usfirst.frc.team1619.robot.misc.EjectSpeedProfile;

public class Constants {

	// AUTO MODE STUFF
	public static final int AUTO_INTAKE_TIMEOUT = 2000;

	public static final double AUTO_DISTANCE_TIMEOUT_BOUND = 1.0;

	public static final int AUTO_TRAJECTORY_TIMEOUT = 250;

	public static final double ROBOT_WIDTH = 25.5 / 12.0;

	// Autons
	public static final int GAME_FIELD_CONFIGURATIONS = 2;
	// public static final int GAME_START_POSITIONS = 4;

	public static final int FIELD_CONFIGURATION_ID_CAP_ONE = 2;
	public static final int FIELD_CONFIGURATION_ID_CAP_TWO = 2;
	public static final int FIELD_CONFIGURATION_ID_CAP_THREE = 1;
	public static final int FIELD_CONFIGURATION_ID_CAP_FOUR = 1;

	public static final int TIME_IN_ACCEPTABLE_RADIUS_PID = 100;

	// Driver Controller
	public static final int DRIVER_CONTROLLER_ID = 0;
	public static final int DRIVER_LEFT_AXIS_X_ID = 0;
	public static final int DRIVER_LEFT_AXIS_Y_ID = 1;
	public static final int DRIVER_RIGHT_AXIS_X_ID = 4;
	public static final int DRIVER_RIGHT_AXIS_Y_ID = 5;

	// Operator Controller
	public static final int OPERATOR_CONTROLLER_ID = 1;
	public static final int OPERATOR_LEFT_AXIS_X_ID = 0;
	public static final int OPERATOR_LEFT_AXIS_Y_ID = 1;
	public static final int OPERATOR_RIGHT_AXIS_X_ID = 4;
	public static final int OPERATOR_RIGHT_AXIS_Y_ID = 5;

	// Collector
	public static final int MOTOR_COLLECTOR_TOP_ID = 5;
	public static final int MOTOR_COLLECTOR_BOTTOM_ID = 4;

	public static final double MOTOR_COLLECTOR_TOP_INTAKE_SPEED = 0.8;
	public static final double MOTOR_COLLECTOR_BOTTOM_INTAKE_SPEED = 1.0;

	public static final EjectSpeedProfile AUTO_EJECT_PROFILE = new EjectSpeedProfile(-1.0, -0.3);

	public static final EjectSpeedProfile TELEOP_EJECT_PROFILE = new EjectSpeedProfile(-0.5, -0.5);

	public static final EjectSpeedProfile FAST_EJECT_PROFILE = new EjectSpeedProfile(-1.0, -1.0);

	public static final EjectSpeedProfile MEDIUM_EJECT_PROFILE = new EjectSpeedProfile(-1.0, -0.8);

	public static final EjectSpeedProfile DINKY_EJECT_PROFILE = new EjectSpeedProfile(-0.25, -0.25);

	public static final double MOTOR_COLLECTOR_TOP_HOLD_SPEED = 0.2;
	public static final double MOTOR_COLLECTOR_BOTTOM_HOLD_SPEED = 0.3;

	public static final double COLLECTOR_OUTPUT_CURRENT_CUBE_ACQUIRED_DURING_INTAKE = 10.0;
	public static final int EJECT_TIME = 500;

	// Drive
	public static final int PEAK_CURRENT = 40;
	public static final int CONTINUOUS_CURRENT = 30;
	public static final int CURRENT_DURATION = 50;
	
	public static final int MOTOR_DRIVE_LEFT_ID = 1;
	public static final int MOTOR_DRIVE_LEFT_SLAVE_ID = 0;
	public static final int MOTOR_DRIVE_RIGHT_ID = 14;
	public static final int MOTOR_DRIVE_RIGHT_SLAVE_ID = 15;

	public static final int QUAD_ENCODER_CYCLES_PER_REVOLUTION = 400;
	public static final double REVOLUTION_PER_UNIT = 5 * Math.PI / 12.0;

	public static final double DRIVE_CURRENT_THRESHOLD = 30;

	// Elevator
	public static final int MOTOR_ELEVATOR_PRIMARY_ID = 12;
	public static final int MOTOR_ELEVATOR_SECONDARY_ID = 13;
	public static final int MOTOR_ELEVATOR_TERTIARY_ID = 2;

	public static final int ELEVATOR_CYCLES_PER_REVOLUTION = 1440;
	public static final double ELEVATOR_REVOLUTION_PER_UNIT = 5.380319105;

	public static final double ELEVATOR_ZERO_OUTPUT = -0.3;

	public static final int ELEVATOR_ZERO_WAIT_TIME = 250;

	public static final double ELEVATOR_TRANSITION_HEIGHT_MINIMUM = 32.50;
	public static final double ELEVATOR_ARM_TRANSITION_HEIGHT_MINIMUM = 27.50;

	public static final double ELEVATOR_STAGE_1_HEIGHT = 39.03;
	public static final double ELEVATOR_STAGE_2_HEIGHT = 71.0;

	public static final double ELEVATOR_SCALE_HIGH_POSITION_AUTO = 49.0;

	public static final double ELEVATOR_SCALE_HIGH_POSITION = 68.0;

	public static final double ELEVATOR_SCALE_LOW_POSITION = 65.18;

	public static final double ELEVATOR_SCALE_NUETRAL_POSITION = 67.55;

	public static final double ELEVATOR_SWITCH_POSITION = 26.98;
	public static final double ELEVATOR_FLOOR_POSITION = 0.0;

	public static final double ELEVATOR_EXCHANGE_POSITION = 4.6;

	public static final double ELEVATOR_SAFE_POSITION = 0.00;

	public static final double ELEVATOR_SLOW_DOWN_OUTPUT = 0.25;

	public static final double ELEVATOR_SAFTEY_CHECK = 3.00;
	public static final double ELEVATOR_ACCEPTABLE_PROXIMITY = 12.00;

	public static final double ELEVATOR_ACCEPTABLE_RANGE = 10.0;

	public static final double ELEVATOR_QUICK_SET_THRESHOLD = 35.0;

	public static final double ELEVATOR_INTAKE_THRESHOLD = 15.0;

	// ARM
	public static final int MOTOR_ARM_ID = 3;

	public static final int ARM_CYCLES_PER_REVOLUTION = 16384;
	public static final double ARM_REVOLUTION_PER_UNIT = 380.3274559;

	public static final double ARM_ZERO_OUTPUT = 0.65;
	public static final int CUBE_COLLECTED_WAIT_TIME = 150;
	public static final double ARM_FLIP_COMPENSATION = -0.35;

	public static final double MAX_ARM_POSITION_INTAKE_SIDE_BELOW_STAGE_1 = 80.1;
	public static final double MIN_ARM_POSITION_RESTRICTED_SIDE_BELOW_STAGE_1 = 168.80;

	public static final double ARM_POSITION_ELEVATOR_SAFTEY = 5.00;

	public static final double ARM_QUICK_SET_THRESHOLD = 30.0;

	public static final double ARM_INTAKE_VOLTAGE = -0.25;

	public static final int HEADING_ERROR_THRESHOLD_TIMEOUT_CABLE_GUARD = 750;
	public static final double HEADING_ERROR_THRESHOLD_CABLE_GUARD = 15.0;
	
	public static final int HEADING_ERROR_THRESHOLD_TIMEOUT = 250;
	public static final double HEADING_ERROR_THRESHOLD = 2.0;	

	public static int ARM_ZERO_WAIT_TIME = 250;

	public static double ARM_INTAKE_POSITION = -30.0;

	public static double ARM_SCALE_HIGH_FRONT = 45.18;

	public static double ARM_SCALE_HIGH_BACK_AUTO = 129.0;
	public static double ARM_SCALE_HIGH_BACK = 160.0;

	public static double ARM_SCALE_NUETRAL_FRONT = 7.10;
	public static double ARM_SCALE_NUETRAL_BACK = 176.81;

	public static double ARM_SWITCH_FRONT = -4.0;
	public static double ARM_SWITCH_BACK = 184.10;
	public static double ARM_SAFE_POSITION = 10.0;
	public static double ARM_PROTECTED_POSITION = 60.00;

	public static double ARM_EXCHANGE_HEIGHT = 0.0;

	public static double ARM_ACCEPTABLE_RANGE = 16.00;

	public static double ARM_INTAKE_THRESHOLD = 16.0;

}