package org.usfirst.frc.team1619.robot.state.multi.climb;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.IO;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.IO.sensor.BooleanSensor;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.framework.util.Timer;

public class MultiClimbWrapper extends ParallelStateWrapper<MultiClimb> {

	Timer timer = new Timer();
	private BooleanSensor climbButton = in.get(IO.OPERATOR_BUTTON_DPAD_DOWN);
	private BooleanSensor overrideButton = in.get(IO.DRIVER_BUTTON_BACK);

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemIds = new HashSet<>();

		subsystemIds.add(Subsystems.elevator.getId());
		subsystemIds.add(Subsystems.arm.getId());
		subsystemIds.add(Subsystems.climbingAids.getId());

		return subsystemIds;
	}

	@Override
	protected MultiClimb createState() {
		return new MultiClimb(this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		if (!this.timer.isStarted()) {
			this.timer.start(105000);
		}

		return (this.timer.isDone() || this.overrideButton.get()) && (this.climbButton.get());
	}

}
