package org.usfirst.frc.team1619.robot.framework.util;

import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.state.State;

public class Delay extends State {

	private long delay;
	Timer timer = new Timer();
	boolean isDelayFinished = false;

	public Delay(In in, Out out, long delay) {
		this.delay = delay;
	}

	@Override
	protected void initialize() {
		// set isDelayFinished to false
		isDelayFinished = false;
		// Start timer
		timer.start(delay);
	}

	@Override
	protected void update() {

		if (timer.isDone()) {
			// If the timer is done set isDelayFinsihed to true
			isDelayFinished = true;
		}
	}

	// Create a method so the wrapper can get the value of isDelayFinished
	public boolean getIsDelayFinished() {
		return isDelayFinished;
	}

	@Override
	protected void dispose() {

	}
}