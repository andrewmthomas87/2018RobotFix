package org.usfirst.frc.team1619.robot.framework.util;

import org.usfirst.frc.team1619.robot.framework.IO.In;
import org.usfirst.frc.team1619.robot.framework.IO.Out;
import org.usfirst.frc.team1619.robot.framework.state.State;

import edu.wpi.first.wpilibj.Preferences;

public class AutonDelay extends State {

	//Needed to read from the SmartDashboard
	Preferences prefs = Preferences.getInstance();
	private double delay;
	Timer timer = new Timer();
	boolean isDelayFinished = false;


	public AutonDelay(In in, Out out) {
		
	}

	@Override
	protected void initialize() {

		// Set delay to the value set in preferences on the smartDashboard
		delay = prefs.getDouble("Autonomous Delay (ms)", 0.0);
		// set isDelayFinished to false
		isDelayFinished = false;
		//Start timer
		timer.start((int)delay);
		
	}

	@Override
	protected void update() {

		if(timer.isDone()) {
			//If the timer is done set isDelayFinsihed to true
			isDelayFinished = true;
		}
	}
	
	//Create a method so the wrapper can get the value of isDelayFinished
	public boolean getIsDelayFinished() {
		return isDelayFinished;
	}
	
	@Override
	protected void dispose() {

	}
}