package org.usfirst.frc.team1619.robot;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerState;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.state.multi.MultiZeroWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoDriveStraightTurnLeftFromRightWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoDriveStraightTurnRightFromLeft;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoDriveStraightTurnRightFromLeftWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoDriveStraightWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoScaleLeftFromLeftWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoScaleLeftFromRightWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoScaleRightFromLeftWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoScaleRightFromRightWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSideSwitchLeftFromLeftWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSideSwitchRightFromRightWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSneakLeftScaleFromRightWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSneakRightScaleFromLeftWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSwitchLeftFromCenterWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSwitchLeftFromLeftWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSwitchLeftScaleLeftFromCenterWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSwitchLeftScaleRightFromCenterWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSwitchRightFromCenterWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSwitchRightFromLeftWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSwitchRightScaleLeftFromCenterWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoSwitchRightScaleRightFromCenterWrapper;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MultiAutoChooser extends MultiSubsystemSequencerState {

	public MultiAutoChooser(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.add(this.addSequence());
	}

	public enum GameStartPositions {
		LEFT, MIDDLE, RIGHT
	}

	public enum AutoTypes {
		DO_NOTHING, SCALE, SWITCH, DRIVE_STRAIGHT, SWITCH_SCALE, SNEAK_SCALE, SIDE_SWITCH
	}

	public Wrapper<?> addSequence() {
		if (RobotState.gameData.equals("LL")) {
			if (RobotState.selectedStartPosition == GameStartPositions.LEFT) {
				switch (RobotState.selectedFieldConfigurationAuto_LL) {
				case SCALE:
					IO.PROFILE_DRIVE_HEADING_PID = IO.driveHeadingController.getHeadingPID().addProfile(0.0, 0.0, 0.01,
							0.007, 0.0, 10.0, 1.0);
					return new MultiAutoScaleLeftFromLeftWrapper();
				case SWITCH:
					return new MultiAutoSwitchLeftFromLeftWrapper();
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightWrapper(false);
				case SIDE_SWITCH: 
					return new MultiAutoSideSwitchLeftFromLeftWrapper();
				default:
					return null;
				}
			} else if (RobotState.selectedStartPosition == GameStartPositions.MIDDLE) {
				switch (RobotState.selectedFieldConfigurationAuto_LL) {
				case SWITCH:
					return new MultiAutoSwitchLeftFromCenterWrapper();
				case SWITCH_SCALE:
					return new MultiAutoSwitchLeftScaleLeftFromCenterWrapper();
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightWrapper(true);
				default:
					return null;
				}
			} else if (RobotState.selectedStartPosition == GameStartPositions.RIGHT) {
				switch (RobotState.selectedFieldConfigurationAuto_LL) {
				case SCALE:
					return new MultiAutoScaleLeftFromRightWrapper();
				case SWITCH:
					return null;
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightTurnLeftFromRightWrapper();
				case SNEAK_SCALE: 
					return new MultiAutoSneakLeftScaleFromRightWrapper();
				default:
					return null;
				}
			}
		} else if (RobotState.gameData.equals("RR")) {
			if (RobotState.selectedStartPosition == GameStartPositions.LEFT) {
				switch (RobotState.selectedFieldConfigurationAuto_RR) {
				case SCALE:
					return new MultiAutoScaleRightFromLeftWrapper();
				case SWITCH:
					return new MultiAutoSwitchRightFromLeftWrapper();
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightTurnRightFromLeftWrapper();
				case SNEAK_SCALE:
					return new MultiAutoSneakRightScaleFromLeftWrapper();
				default:
					return null;
				}
			} else if (RobotState.selectedStartPosition == GameStartPositions.MIDDLE) {
				switch (RobotState.selectedFieldConfigurationAuto_RR) {
				case SWITCH:
					return new MultiAutoSwitchRightFromCenterWrapper();
				case SWITCH_SCALE:
					return new MultiAutoSwitchRightScaleRightFromCenterWrapper();
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightWrapper(true);
				default:
					return null;
				}
			} else if (RobotState.selectedStartPosition == GameStartPositions.RIGHT) {
				switch (RobotState.selectedFieldConfigurationAuto_RR) {
				case SCALE:
					IO.PROFILE_DRIVE_HEADING_PID = IO.driveHeadingController.getHeadingPID().addProfile(0.0, 0.0, 0.028,
							0.007, 0.0, 10.0, 1.0);
					return new MultiAutoScaleRightFromRightWrapper();
				case SWITCH:
					return null;
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightWrapper(false);
				case SIDE_SWITCH:
					return new MultiAutoSideSwitchRightFromRightWrapper();
				default:
					return null;
				}
			}
		} else if (RobotState.gameData.equals("LR")) {
			if (RobotState.selectedStartPosition == GameStartPositions.LEFT) {
				switch (RobotState.selectedFieldConfigurationAuto_LR) {
				case SCALE:
					return new MultiAutoScaleRightFromLeftWrapper();
				case SWITCH:
					return new MultiAutoSwitchLeftFromLeftWrapper();
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightTurnRightFromLeftWrapper();
				case SNEAK_SCALE: 
					return new MultiAutoSneakRightScaleFromLeftWrapper();
				case SIDE_SWITCH:
					return new MultiAutoSideSwitchLeftFromLeftWrapper();
				default:
					return null;
				}
			} else if (RobotState.selectedStartPosition == GameStartPositions.MIDDLE) {
				switch (RobotState.selectedFieldConfigurationAuto_LR) {
				case SWITCH:
					return new MultiAutoSwitchLeftFromCenterWrapper();
				case SWITCH_SCALE:
					return new MultiAutoSwitchLeftScaleRightFromCenterWrapper();
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightWrapper(true);
				default:
					return null;
				}
			} else if (RobotState.selectedStartPosition == GameStartPositions.RIGHT) {
				switch (RobotState.selectedFieldConfigurationAuto_LR) {
				case SCALE:
					IO.PROFILE_DRIVE_HEADING_PID = IO.driveHeadingController.getHeadingPID().addProfile(0.0, 0.0, 0.028,
							0.007, 0.0, 10.0, 1.0);
					return new MultiAutoScaleRightFromRightWrapper();
				case SWITCH:
					return null;
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightWrapper(false);
				default:
					return null;
				}
			}
		} else if (RobotState.gameData.equals("RL")) {
			if (RobotState.selectedStartPosition == GameStartPositions.LEFT) {
				switch (RobotState.selectedFieldConfigurationAuto_RL) {
				case SCALE:
					return new MultiAutoScaleLeftFromLeftWrapper();
				case SWITCH:
					return new MultiAutoSwitchRightFromLeftWrapper();
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightWrapper(false);
				default:
					return null;
				}
			} else if (RobotState.selectedStartPosition == GameStartPositions.MIDDLE) {
				switch (RobotState.selectedFieldConfigurationAuto_RL) {
				case SWITCH:
					return new MultiAutoSwitchRightFromCenterWrapper();
				case SWITCH_SCALE:
					return new MultiAutoSwitchRightScaleLeftFromCenterWrapper();
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightWrapper(true);
				default:
					return null;
				}
			} else if (RobotState.selectedStartPosition == GameStartPositions.RIGHT) {
				switch (RobotState.selectedFieldConfigurationAuto_RL) {
				case SCALE:
					return new MultiAutoScaleLeftFromRightWrapper();
				case SWITCH:
					return null;
				case DRIVE_STRAIGHT:
					return new MultiAutoDriveStraightTurnLeftFromRightWrapper();
				case SNEAK_SCALE:
					return new MultiAutoSneakLeftScaleFromRightWrapper();
				case SIDE_SWITCH:
					return new MultiAutoSideSwitchRightFromRightWrapper();
				default:
					return null;
				}
			}
		}
		return null;
	}

	public boolean getisStateNull() {
		return this.addSequence() == null;
	}
}
