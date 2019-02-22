package org.usfirst.frc.team1619.robot.state.multi;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiElevatorAndArmSet;

public class ElevatorAndArmQuickSetWrapper extends ParallelStateWrapper<MultiElevatorAndArmSet> {

	private double elevatorSetpoint;
	private double armSetpoint;

	private NumericSensor elevatorPositionSensor = in.get(IO.SENSOR_ELEVATOR_POSITION);
	private NumericSensor armPositionSensor = in.get(IO.SENSOR_ARM_POSITION);

	private double elevatorThreshold;
	private double armThreshold;

	public ElevatorAndArmQuickSetWrapper(double elevatorThreshold, double armThreshold, double elevatorSetpoint,
			double armSetpoint) {
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;

		this.elevatorThreshold = elevatorThreshold;
		this.armThreshold = armThreshold;

	}

	@Override
	protected Set getSubsystemIds() {
		Set<Integer> subsystemsIds = new HashSet<>();

		subsystemsIds.add(Subsystems.elevator.getId());
		subsystemsIds.add(Subsystems.arm.getId());

		return subsystemsIds;
	}

	@Override
	protected MultiElevatorAndArmSet createState() {
		return new MultiElevatorAndArmSet(this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}

	@Override
	protected boolean isDone() {
		return (Math.abs(this.elevatorPositionSensor.get() - this.elevatorSetpoint) < this.elevatorThreshold)
				&& (Math.abs(this.armPositionSensor.get() - this.armSetpoint) < this.armThreshold);

	}

}