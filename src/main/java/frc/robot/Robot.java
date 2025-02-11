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
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;


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

  private TalonSRX LeftMasterMotor1 = new TalonSRX(18);
  private VictorSP LeftMasterMotor2 = new VictorSP(19);
  private VictorSP RightMasterMotor1 = new VictorSP(2);
  private TalonSRX RightMasterMotor2 = new TalonSRX(1);
  private SparkMax PIDmotor = new SparkMax(13, MotorType.kBrushless);

  private Encoder encorder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
  private final double kdriveTick2Feet = 1.0 / 128 * 6 * Math.PI / 12;
  private Joystick joy1 = new Joystick(0);




  @Override
  public void autonomousInit() {
    encorder.reset();
    
  }
  final double Kp = 0.05;

  double setpoint = 0;

  @Override
  public void autonomousPeriodic() {
    // get joystick position
    if (joy1.getRawButton(1)){
      setpoint = 10;
    } else if (joy1.getRawButton(2)) {
      setpoint = 0;
    }

    // sensor position
    double sensorPosition = encorder.get() * kdriveTick2Feet;

    //calculations
    double error = setpoint - sensorPosition;
      double outputspeed = Kp * error;

    //output to motor
    PIDmotor.set(outputspeed);
    }

    @Override
    public void robotPeriodic() {
      SmartDashboard.putNumber("encoder value", encorder.get() * kdriveTick2Feet);
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
    LeftMasterMotor2.set(left);
    RightMasterMotor1.set(-right);
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
