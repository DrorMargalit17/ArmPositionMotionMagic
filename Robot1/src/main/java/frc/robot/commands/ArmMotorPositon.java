// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmMotionMagicConstants;
import frc.robot.subsystems.ArmMotionMagic;

public class ArmMotorPositon extends CommandBase {

  private final ArmMotionMagic arm = ArmMotionMagic.getInstance();
  Timer timer;
  double position;

  /** Creates a new ArmMotorPositon. */
  public ArmMotorPositon(Double position) {
    this.position = position;
    this.addRequirements(arm);
    this.timer.start();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.arm.putArmInPosMM(position);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.setSpeed(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(arm.getEncoderPosition() - this.position) > ArmMotionMagicConstants.Limit){
          return (this.timer.hasElapsed(ArmMotionMagicConstants.timeLimit));
    }
    else{
      this.timer.restart();
      return false;
    }
  }
}
