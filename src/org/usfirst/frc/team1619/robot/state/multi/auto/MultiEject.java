package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.misc.EjectSpeedProfile;
import org.usfirst.frc.team1619.robot.state.arm.ArmIdleWrapper;
import org.usfirst.frc.team1619.robot.state.collector.CollectorEjectAutoWrapper;
import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdleWrapper;

public class MultiEject extends ParallelState {
	public MultiEject(EjectSpeedProfile ejectSpeedProfile, double elevatorSetpoint, double armSetpoint,
			Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(new CollectorEjectAutoWrapper(ejectSpeedProfile));
		this.addBackgroundState(new ElevatorIdleWrapper(elevatorSetpoint));
		this.addBackgroundState(new ArmIdleWrapper(armSetpoint));

	}
}
