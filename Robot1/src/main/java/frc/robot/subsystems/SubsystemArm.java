// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SubsystemArm extends SubsystemBase {
  private TalonFX ArmMotor;
  /** Creates a new SubsystemArm. */
  public SubsystemArm() {
    this.ArmMotor = new TalonFX(0);
  }

  public void setSpeed(double speed){
    this.ArmMotor.set(speed);
  }

  public Double getEncoderPosition(){
    return ArmMotor.getPosition().getValue();
  }

  private static SubsystemArm instance;
  
  public static SubsystemArm getInstance(){
    if (instance==null){
      instance = new SubsystemArm();
    }
    return instance;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
