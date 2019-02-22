package org.usfirst.frc.team1619.robot;

import java.util.Set;

import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
import org.usfirst.frc.team1619.robot.state.collector.CollectorHoldAutoWrapper;
import org.usfirst.frc.team1619.robot.state.multi.auto.MultiAutoScaleRightFromLeftWrapper;

public class MultiAutoChooserCubeHold extends ParallelState {

	public MultiAutoChooserCubeHold(Set<Integer> subsystemIds) {
		super(subsystemIds);

		this.addState(new MultiAutoChooserWrapper());
		this.addBackgroundState(new CollectorHoldAutoWrapper());
	}

}
