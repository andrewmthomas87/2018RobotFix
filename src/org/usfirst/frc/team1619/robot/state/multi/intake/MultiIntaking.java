package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.arm.ArmSetVoltageWrapper;
import org.usfirst.frc.team1619.robot.state.collector.IntakeIfSequenceWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdleWrapper;
import org.usfirst.frc.team1619.robot.state.misc.CancelCheckWrapper;

public class MultiIntaking extends ParallelState {

	public MultiIntaking(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(new IntakeIfSequenceWrapper());
		this.addBackgroundState(new ElevatorIdleWrapper(Constants.ELEVATOR_FLOOR_POSITION));
		this.addBackgroundState(new ArmSetVoltageWrapper(Constants.ARM_INTAKE_VOLTAGE, -1));

		this.addBackgroundState(new CancelCheckWrapper());

	}

	@Override
	protected void dispose() {
		RobotState.cancelZero = false;
		super.dispose();
	}

}