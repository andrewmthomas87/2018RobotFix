package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.NumericSensor;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class MultiElevatorAndArmDistanceIdleWrapper extends ParallelStateWrapper<MultiElevatorAndArmIdle> {

	private double elevatorSetpoint;
	private double armSetpoint;

	private double distance;

	private NumericSensor leftPosition = in.get(IO.SENSOR_DRIVE_LEFT_POSITION);
	private NumericSensor righPosition = in.get(IO.SENSOR_DRIVE_RIGHT_POSITION);

	public MultiElevatorAndArmDistanceIdleWrapper(double elevatorSetpoint, double armSetpoint, double distance) {

		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;
		this.distance = distance;
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
		double currentDistance = Math.abs((this.leftPosition.get() + this.righPosition.get()) / 2.0);

		return currentDistance >= this.distance;
	}

}
