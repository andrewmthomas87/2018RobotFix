//package org.usfirst.frc.team1619.robot.state.multi.climb;
//
//import java.util.Set;
//
//import org.usfirst.frc.team1619.robot.Constants;
//import org.usfirst.frc.team1619.robot.framework.state.ParallelState;
//import org.usfirst.frc.team1619.robot.state.arm.ArmIdlePreClimbWrapper;
//import org.usfirst.frc.team1619.robot.state.elevator.ElevatorIdlePreClimbIdleWrapper;
//
//public class MultiClimbWaiting extends ParallelState{
//
//	public MultiClimbWaiting(Set<Integer> subsystemIds) {
//		super(subsystemIds);
//
//		this.addState(new ElevatorIdlePreClimbIdleWrapper(Constants.ELEVATOR_STAGE_2_HEIGHT));
//		this.addState(new ArmIdlePreClimbWrapper(Constants.ARM_PROTECTED_POSITION));
//	}
//
//}
