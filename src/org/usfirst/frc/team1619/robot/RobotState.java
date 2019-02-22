package org.usfirst.frc.team1619.robot;

import org.usfirst.frc.team1619.robot.MultiAutoChooser.AutoTypes;
import org.usfirst.frc.team1619.robot.MultiAutoChooser.GameStartPositions;
import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.state.arm.ArmDesiredPostion;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotState {

	public static double previousheadingError;
	public static boolean hasBeenZeroed = false;
	public static boolean isCubeAcquired = true;
	public static boolean autoDone = false;
	public static boolean highGear = true;

	public static String gameData;

	public static ArmDesiredPostion armDesiredPosition = ArmDesiredPostion.NONE;

	private static SendableChooser<MultiAutoChooser.AutoTypes> autonLL = new SendableChooser<>();
	private static SendableChooser<MultiAutoChooser.AutoTypes> autonRR = new SendableChooser<>();
	private static SendableChooser<MultiAutoChooser.AutoTypes> autonLR = new SendableChooser<>();
	private static SendableChooser<MultiAutoChooser.AutoTypes> autonRL = new SendableChooser<>();
	public static SendableChooser<MultiAutoChooser.GameStartPositions> startPosition = new SendableChooser<>();

	public static MultiAutoChooser.AutoTypes selectedFieldConfigurationAuto_LL;
	public static MultiAutoChooser.AutoTypes selectedFieldConfigurationAuto_RR;
	public static MultiAutoChooser.AutoTypes selectedFieldConfigurationAuto_LR;
	public static MultiAutoChooser.AutoTypes selectedFieldConfigurationAuto_RL;
	public static MultiAutoChooser.GameStartPositions previousStartPosition;
	public static MultiAutoChooser.GameStartPositions selectedStartPosition;

	public static double elevatorSetpoint;
	public static boolean cancelZero = false;

	public static void update() {

		String newGameData = DriverStation.getInstance().getGameSpecificMessage();
		if (newGameData != null && newGameData.length() > 0) {
			gameData = newGameData.substring(0, 2);
			SmartDashboard.putString("Game Data", gameData);

		}

		NumericSensor leftVelocity = In.getInstance().get(IO.SENSOR_DRIVE_LEFT_VELOCITY);
		SmartDashboard.putNumber("Left Velocity", leftVelocity.get());

		NumericSensor rightVelocity = In.getInstance().get(IO.SENSOR_DRIVE_RIGHT_VELOCITY);
		SmartDashboard.putNumber("Right Velocity", rightVelocity.get());

		NumericSensor headingSensor = In.getInstance().get(IO.SENSOR_NAVX_HEADING);
		SmartDashboard.putNumber("Heading", headingSensor.get());

		NumericSensor leftPositionSensor = In.getInstance().get(IO.SENSOR_DRIVE_LEFT_POSITION);
		SmartDashboard.putNumber("Left Position", leftPositionSensor.get());

		NumericSensor rightPositionSensor = In.getInstance().get(IO.SENSOR_DRIVE_RIGHT_POSITION);
		SmartDashboard.putNumber("Right Position", rightPositionSensor.get());

		NumericSensor armPosition = In.getInstance().get(IO.SENSOR_ARM_POSITION);
		SmartDashboard.putNumber("Arm Position", armPosition.get());

		NumericSensor elevatorPosition = In.getInstance().get(IO.SENSOR_ELEVATOR_POSITION);
		SmartDashboard.putNumber("Elevator Position", elevatorPosition.get());

		BooleanSensor elevatorLimit = In.getInstance().get(IO.ELEVATOR_BOTTOM_SWITCH);
		// SmartDashboard.putBoolean("Elevator Limit Switch", elevatorLimit.get());

		NumericSensor driveCurrentSensor = In.getInstance().get(IO.SENSOR_DRIVE_CURRENT);
		// SmartDashboard.putNumber("DRIVE CURRENT", driveCurrentSensor.get());

		SmartDashboard.putString("Arm Desired Position", armDesiredPosition.toString());
		SmartDashboard.putBoolean("___CUBE ACQUIRED___", isCubeAcquired);
		SmartDashboard.putBoolean("hasBeenZeroed", hasBeenZeroed);

	}

	public static void initAuto() {
		startPosition.addObject("Left", GameStartPositions.LEFT);
		startPosition.addDefault("Middle", GameStartPositions.MIDDLE);
		startPosition.addObject("Right", GameStartPositions.RIGHT);

		autonLL.addObject("LL - Do Nothing", AutoTypes.DO_NOTHING);
		autonLL.addObject("LL - SCALE", AutoTypes.SCALE);
		autonLL.addDefault("LL - SWITCH", AutoTypes.SWITCH);
		autonLL.addObject("LL - SWITCH_SCALE", AutoTypes.SWITCH_SCALE);
		autonLL.addObject("LL - DRIVE STRAIGHT", AutoTypes.DRIVE_STRAIGHT);
		autonLL.addObject("LL - SNEAK_SCALE", AutoTypes.SNEAK_SCALE);
		autonLL.addObject("LL - SIDE_SWITCH", AutoTypes.SIDE_SWITCH);

		autonRR.addObject("RR - Do Nothing", AutoTypes.DO_NOTHING);
		autonRR.addObject("RR - SCALE", AutoTypes.SCALE);
		autonRR.addObject("RR - SWITCH_SCALE", AutoTypes.SWITCH_SCALE);
		autonRR.addDefault("RR - SWITCH", AutoTypes.SWITCH);
		autonRR.addObject("RR - DRIVE STRAIGHT", AutoTypes.DRIVE_STRAIGHT);
		autonRR.addObject("RR - SNEAK_SCALE", AutoTypes.SNEAK_SCALE);
		autonRR.addObject("RR - SIDE_SWITCH", AutoTypes.SIDE_SWITCH);

		autonLR.addObject("LR - Do Nothing", AutoTypes.DO_NOTHING);
		autonLR.addObject("LR - SCALE", AutoTypes.SCALE);
		autonLR.addObject("LR - SWITCH_SCALE", AutoTypes.SWITCH_SCALE);
		autonLR.addDefault("LR - SWITCH", AutoTypes.SWITCH);
		autonLR.addObject("LR - DRIVE STRAIGHT", AutoTypes.DRIVE_STRAIGHT);
		autonLR.addObject("LR - SNEAK_SCALE", AutoTypes.SNEAK_SCALE);
		autonLR.addObject("LR - SIDE_SWITCH", AutoTypes.SIDE_SWITCH);

		autonRL.addObject("RL - Do Nothing", AutoTypes.DO_NOTHING);
		autonRL.addObject("RL - SCALE", AutoTypes.SCALE);
		autonRL.addDefault("RL - SWITCH", AutoTypes.SWITCH);
		autonRL.addObject("RL - SWITCH_SCALE", AutoTypes.SWITCH_SCALE);
		autonRL.addObject("RL - DRIVE STRAIGHT", AutoTypes.DRIVE_STRAIGHT);
		autonRL.addObject("RL - SNEAK_SCALE", AutoTypes.SNEAK_SCALE);
		autonRL.addObject("RL - SIDE_SWITCH", AutoTypes.SIDE_SWITCH);

		SmartDashboard.putData("StartPosition", startPosition);
		SmartDashboard.putData("Auton_LL", autonLL);
		SmartDashboard.putData("Auton_RR", autonRR);
		SmartDashboard.putData("Auton_LR", autonLR);
		SmartDashboard.putData("Auton_RL", autonRL);

		selectedStartPosition = startPosition.getSelected();
		selectedFieldConfigurationAuto_LL = autonLL.getSelected();
		selectedFieldConfigurationAuto_RR = autonRR.getSelected();
		selectedFieldConfigurationAuto_LR = autonLR.getSelected();
		selectedFieldConfigurationAuto_RL = autonRL.getSelected();

	}

	public static void updateAuto() {

		selectedStartPosition = startPosition.getSelected();
		selectedFieldConfigurationAuto_LL = autonLL.getSelected();
		selectedFieldConfigurationAuto_RR = autonRR.getSelected();
		selectedFieldConfigurationAuto_LR = autonLR.getSelected();
		selectedFieldConfigurationAuto_RL = autonRL.getSelected();

		SmartDashboard.putString("LL ", selectedFieldConfigurationAuto_LL.toString());
		SmartDashboard.putString("RR ", selectedFieldConfigurationAuto_RR.toString());
		SmartDashboard.putString("LR ", selectedFieldConfigurationAuto_LR.toString());
		SmartDashboard.putString("RL ", selectedFieldConfigurationAuto_RL.toString());
		SmartDashboard.putString("Start Position ", selectedStartPosition.toString());

	}
}
