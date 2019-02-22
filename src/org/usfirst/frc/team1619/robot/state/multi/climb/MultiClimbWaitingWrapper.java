//package org.usfirst.frc.team1619.robot.state.multi.climb;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.usfirst.frc.team1619.robot.Subsystems;
//import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
//
//public class MultiClimbWaitingWrapper extends ParallelStateWrapper<MultiClimbWaiting> {
//
//	@Override
//	protected Set<Integer> getSubsystemIds() {
//		Set<Integer> subsystemIds = new HashSet<>();
//
//		subsystemIds.add(Subsystems.elevator.getId());
//		subsystemIds.add(Subsystems.arm.getId());
//
//		return subsystemIds;
//	}
//
//	@Override
//	protected MultiClimbWaiting createState() {
//		return new MultiClimbWaiting(this.getSubsystemIds());
//	}
//
//	@Override
//	protected boolean isReady() {
//		return true;
//	}
//
//}
