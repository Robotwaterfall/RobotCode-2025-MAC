// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

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

  private SparkMax PIDtestmotor = new SparkMax(13, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);

  

  private Joystick Joy1 = new Joystick(0);

  private Encoder encoder = new Encoder(0,1 , false , Encoder.EncodingType.k4X);
  private final double kDriveTick2Feet = 1.0 / 128 * 6 * Math.PI / 12;




  @Override
  public void autonomousInit() {
    encoder.reset();
  }

  final double kP = 0.05;

  double setpoint = 0;

  @Override
  public void autonomousPeriodic() {
    // get joystick command setting the setpoint
    if (Joy1.getRawButton(1)) {
      setpoint = 10;
    } else if (Joy1.getRawButton(2)) {
      setpoint = 0;
    }

    // get sensor position
    double sensorPosition = encoder.get() * kDriveTick2Feet;

    // calculations
    double error = setpoint - sensorPosition;

    double outputSpeed = kP * error;

    // set motor speed
    PIDtestmotor.set(outputSpeed);

  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("encoder value", encoder.get() * kDriveTick2Feet);
  }
 

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

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
