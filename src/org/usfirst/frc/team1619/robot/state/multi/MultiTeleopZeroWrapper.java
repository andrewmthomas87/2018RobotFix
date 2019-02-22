package org.usfirst.frc.team1619.robot.state.multi;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.RobotState;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;

public class MultiTeleopZeroWrapper extends MultiSubsystemSequencerStateWrapper<MultiTeleopZero> {

	private BooleanSensor overrideButton = in.get(IO.OPERATOR_BUTTON_BACK);

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiTeleopZero createState() {
		return new MultiTeleopZero(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return !RobotState.hasBeenZeroed;
	}

}