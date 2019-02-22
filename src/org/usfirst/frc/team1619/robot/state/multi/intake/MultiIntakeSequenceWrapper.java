package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.state.MultiSubsystemSequencerStateWrapper;

public class MultiIntakeSequenceWrapper extends MultiSubsystemSequencerStateWrapper<MultiIntakeSequence> {

	private BooleanSensor intakeButton = in.get(IO.OPERATOR_LEFT_TRIGGER);

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());
		subsystemsIds.add(Subsystems.collector.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiIntakeSequence createState() {
		return new MultiIntakeSequence(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return this.intakeButton.get();
	}

}
