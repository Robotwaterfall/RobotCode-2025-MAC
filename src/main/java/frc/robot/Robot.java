// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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

if (time - startTime < 3) {
    LeftMasterMotor1.set(ControlMode.PercentOutput, 0.5); //drive forward for 3 seconds at 50% speed
    LeftMasterMotor2.set(ControlMode.PercentOutput, 0.5);
    RightMasterMotor1.set(ControlMode.PercentOutput, -0.5);
    RightMasterMotor2.set(ControlMode.PercentOutput, -0.5);
  } else {
    LeftMasterMotor1.set(ControlMode.PercentOutput, 0); //stop if time is over 3 seconds
    LeftMasterMotor2.set(ControlMode.PercentOutput, 0);
    RightMasterMotor1.set(ControlMode.PercentOutput, 0);
    RightMasterMotor2.set(ControlMode.PercentOutput, 0);
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1)*0.6;
    double turn = joy1.getRawAxis(4)*0.3;

    double left = speed + turn;  //calculate left and right motor speeds
    double right = speed - turn;

    LeftMasterMotor1.set(ControlMode.PercentOutput, left); //set motor speeds
    LeftMasterMotor2.set(ControlMode.PercentOutput, left);
    RightMasterMotor1.set(ControlMode.PercentOutput, -right);
    RightMasterMotor2.set(ControlMode.PercentOutput, -right);

  
    }

  
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