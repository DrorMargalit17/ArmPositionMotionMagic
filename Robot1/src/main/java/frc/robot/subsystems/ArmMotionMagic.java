
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmMotionMagicConstants;

public class ArmMotionMagic extends SubsystemBase {
  /** Creates a new ArmMotionMagic. */
  private TalonFX motor; 

  private final MotionMagicVoltage motionMagic = new MotionMagicVoltage(0);

  private ArmMotionMagic() {
    this.motor = new TalonFX(ArmMotionMagicConstants.KMotorID);

    TalonFXConfiguration configs = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    mm.MotionMagicCruiseVelocity = ArmMotionMagicConstants.MotionMagicCruiseVelocity;
    mm.MotionMagicAcceleration = ArmMotionMagicConstants.Acceleration;
    mm.MotionMagicJerk = ArmMotionMagicConstants.jerk;
    configs.MotionMagic = mm;

    configs.Slot0.kP = ArmMotionMagicConstants.kP;
    configs.Slot0.kD = ArmMotionMagicConstants.kD;
    configs.Slot0.kS = ArmMotionMagicConstants.kS;
    configs.Slot0.kV = ArmMotionMagicConstants.kV;
    
    configs.Voltage.PeakForwardVoltage = ArmMotionMagicConstants.PeakForwardVoltage;
    configs.Voltage.PeakReverseVoltage = ArmMotionMagicConstants.PeakRevarsedVoltage;

    StatusCode status = StatusCode.StatusCodeNotInitialized;
    for (int i = 0; i<5; ++i){
      status = motor.getConfigurator().apply(configs);
      if(status.isOK())
        break;
    }
    if (!status.isOK()){
      System.out.println("Could not apply configs, error code: " + status.toString());
    }

    configs.Feedback.SensorToMechanismRatio = ArmMotionMagicConstants.SensorToMechanismRatio;

  }

  public void putArmInPosMM(double position){
    motor.setControl(motionMagic.withPosition(position));
  }

  private static ArmMotionMagic instance;
  
  public static ArmMotionMagic getInstance(){
    if (instance==null){
      instance = new ArmMotionMagic();
    }
    return instance;
  }

  public void setSpeed(double speed){
    this.motor.set(speed);
  }

  public Double getEncoderPosition(){
    return motor.getPosition().getValue();
  }

  @Override     
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
