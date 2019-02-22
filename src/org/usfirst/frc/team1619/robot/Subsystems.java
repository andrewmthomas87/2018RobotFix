package org.usfirst.frc.team1619.robot;

import org.usfirst.frc.team1619.robot.framework.state.Subsystem;

public class Subsystems {

	public static Subsystem drive;
	public static Subsystem collector;
	public static Subsystem elevator;
	public static Subsystem arm;
	public static Subsystem elevatorShift;
	public static Subsystem climbingAids;

	public static void initialize() {
		drive = new Subsystem();
		collector = new Subsystem();
		elevator = new Subsystem();
		arm = new Subsystem();
		elevatorShift = new Subsystem();
		climbingAids = new Subsystem();

	}
}
