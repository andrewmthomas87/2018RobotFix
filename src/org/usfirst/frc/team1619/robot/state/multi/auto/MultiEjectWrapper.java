package org.usfirst.frc.team1619.robot.state.multi.auto;

import java.util.HashSet;
import java.util.Set;

import org.usfirst.frc.team1619.robot.Constants;
import org.usfirst.frc.team1619.robot.Subsystems;
import org.usfirst.frc.team1619.robot.framework.state.ParallelStateWrapper;
import org.usfirst.frc.team1619.robot.misc.EjectSpeedProfile;

public class MultiEjectWrapper extends ParallelStateWrapper<MultiEject> {

	private double elevatorSetpoint;
	private double armSetpoint;
	private EjectSpeedProfile ejectSpeedProfile;

	public MultiEjectWrapper(EjectSpeedProfile ejectSpeedProfile, double elevatorSetpoint, double armSetpoint) {
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;
		this.ejectSpeedProfile = ejectSpeedProfile;
	}

	public MultiEjectWrapper(double elevatorSetpoint, double armSetpoint) {
		this.elevatorSetpoint = elevatorSetpoint;
		this.armSetpoint = armSetpoint;
		this.ejectSpeedProfile = Constants.TELEOP_EJECT_PROFILE;
	}

	@Override
	protected Set<Integer> getSubsystemIds() {
		Set<Integer> subsystemIds = new HashSet<>();

		subsystemIds.add(Subsystems.drive.getId());
		subsystemIds.add(Subsystems.elevator.getId());
		subsystemIds.add(Subsystems.arm.getId());

		return subsystemIds;
	}

	@Override
	protected MultiEject createState() {
		return new MultiEject(this.ejectSpeedProfile, this.elevatorSetpoint, this.armSetpoint, this.getSubsystemIds());
	}

	@Override
	protected boolean isReady() {
		return true;
	}
}
