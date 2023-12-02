// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private Solenoid solenoid;
  private TalonFX motor;
  /** Creates a new IntakeSubsystem. */
  private IntakeSubsystem() {
    this.solenoid = new Solenoid(PneumaticsModuleType.CTREPCM , Constants.IntakeConstants.KsolenoidID);
    this.motor = new TalonFX(Constants.IntakeConstants.KMotorID);
  }

  private static IntakeSubsystem instance;
  
  public static IntakeSubsystem getInstance(){
    if (instance==null){
      instance = new IntakeSubsystem();
    }
    return instance;
  }

  public void setSolenoid(boolean value){
    this.solenoid.set(value);
  }

  public void setMotor(double speed){
    this.motor.set(speed);
  }

  public boolean isOpen(){
    return this.solenoid.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
