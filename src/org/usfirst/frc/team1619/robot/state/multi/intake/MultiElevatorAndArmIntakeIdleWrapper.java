package org.usfirst.frc.team1619.robot.state.multi.intake;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiElevatorAndArmIdle;

public class MultiElevatorAndArmIntakeIdleWrapper extends ParallelStateWrapper<MultiElevatorAndArmIdle> {

	private double elevatorSetpoint;
	private double armSetpoint;

	private BooleanSensor fullIntake = in.get(IO.OPERATOR_LEFT_TRIGGER);

	public MultiElevatorAndArmIntakeIdleWrapper(double elevatorSetpoint, double armSetpoint) {
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;
	}

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiElevatorAndArmIdle createState() {
		return new MultiElevatorAndArmIdle(this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		return this.fullIntake.getDelta() == 1;
	}

}