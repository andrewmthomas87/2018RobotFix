package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.framework.state.Wrapper;
import org.usfirst.frc.team1619.robot.state.collector.CollectorHoldWrapper;

public class MultiDriveZeroThenIdle extends ParallelState {

	public MultiDriveZeroThenIdle(Wrapper driverState, double elevatorInitSetpoint, double armInitSetpoint,
			double distance, double elevatorSetpoint, double armSetpoint, Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(driverState);
		this.addBackgroundState(new MultiAutoZeroWrapper(elevatorInitSetpoint, armInitSetpoint, distance,
				elevatorSetpoint, armSetpoint));
	}

}
