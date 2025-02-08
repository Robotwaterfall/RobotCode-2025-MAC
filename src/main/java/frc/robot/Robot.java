// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

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

  private VictorSP LeftMasterMotor1 = new VictorSP(0);
  private VictorSP LeftMasterMotor2 = new VictorSP(1);
  private VictorSP RightMasterMotor1 = new VictorSP(2);
  private VictorSP RightMasterMotor2 = new VictorSP(3);

  private Joystick joy1 = new Joystick(0);


  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1)*0.6;
    double turn = joy1.getRawAxis(4)*0.3;

    double left = speed + turn;
    double right = speed - turn;

    LeftMasterMotor1.set(left);
    LeftMasterMotor2.set(left);
    RightMasterMotor1.set(-right);
    RightMasterMotor2.set(-right);
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
