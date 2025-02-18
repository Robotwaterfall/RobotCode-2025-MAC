// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   private TalonSRX LeftMasterMotor1 = new TalonSRX( 18);
   private TalonSRX LeftMasterMotor2 = new TalonSRX(3);
   private TalonSRX RightMasterMotor1 = new TalonSRX(4);
   private TalonSRX RightMasterMotor2 = new TalonSRX(1);
   private SparkMax Testmotor = new SparkMax(13, MotorType.kBrushless);

  private Joystick joy1 = new Joystick(0);

  private double startTime;


  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    if (time - startTime < 3) { // drive forward for 3 seconds
    LeftMasterMotor1.set(ControlMode.PercentOutput, 0.6);
    LeftMasterMotor2.set(ControlMode.PercentOutput, 0.6);
    RightMasterMotor1.set(ControlMode.PercentOutput, -0.6);
    RightMasterMotor2.set(ControlMode.PercentOutput, -0.6);
    } else {
      LeftMasterMotor1.set(ControlMode.PercentOutput, 0);
      LeftMasterMotor2.set(ControlMode.PercentOutput, 0);
      RightMasterMotor1.set(ControlMode.PercentOutput, 0);
      RightMasterMotor2.set(ControlMode.PercentOutput, 0);
    }
    
    if (time - startTime > 3 && time - startTime < 5){ // turn right for 2 seconds at 60% speed
    LeftMasterMotor1.set(ControlMode.PercentOutput, 0.6);
    LeftMasterMotor2.set(ControlMode.PercentOutput, 0.6);
    RightMasterMotor1.set(ControlMode.PercentOutput, 0);
    RightMasterMotor2.set(ControlMode.PercentOutput, 0);
    } else {
      LeftMasterMotor1.set(ControlMode.PercentOutput, 0);
      LeftMasterMotor2.set(ControlMode.PercentOutput, 0);
      RightMasterMotor1.set(ControlMode.PercentOutput, 0);
      RightMasterMotor2.set(ControlMode.PercentOutput, 0);
    }
    if (time - startTime > 5 && time -startTime < 10) { // drive backwards for 5 seconds at 60% speed
      LeftMasterMotor1.set(ControlMode.PercentOutput, -0.6);
      LeftMasterMotor2.set(ControlMode.PercentOutput, -0.6);
      RightMasterMotor1.set(ControlMode.PercentOutput, 0.6);
      RightMasterMotor2.set(ControlMode.PercentOutput, 0.6);
    } else {
      LeftMasterMotor1.set(ControlMode.PercentOutput, 0);
      LeftMasterMotor2.set(ControlMode.PercentOutput, 0);
      RightMasterMotor1.set(ControlMode.PercentOutput, 0);
      RightMasterMotor2.set(ControlMode.PercentOutput, 0);
    }
    if (time - startTime > 10 && time - startTime < 12) { // turn testmotor for 2 seconds right at 50% speed
      Testmotor.set(0.5);
    } else {
      Testmotor.set(0);
    }

}
 

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1) * 0.6;
    double turn = joy1.getRawAxis(0) * 0.3;

    double left = speed + turn;  // calculate left and right motor speeds
    double right = speed - turn;

    LeftMasterMotor1.set(ControlMode.PercentOutput, left);
    LeftMasterMotor2.set(ControlMode.PercentOutput, left);
    RightMasterMotor1.set(ControlMode.PercentOutput, -right);
    RightMasterMotor2.set(ControlMode.PercentOutput, -right); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
