/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.PID;

/**
 * This Subsystem configures the Drivetrain of my robot
 */

public class Drivetrain extends SubsystemBase {

  // This Xbox Controller Object is used for Driving the Robot]
  private final Joystick DriverControl;

  private Boolean toggledOn = false;
  private Boolean toggledPress = false;

  // Doubles for Pid p and error state 
  private Double kp;
  private Double error;

  private NetworkTableEntry kPEntry, Gyro, Vision;

  // Motor Controllers for Drivetrain 
  private final Spark tankR = new Spark(Constants.kTankR);
  private final Spark tankL = new Spark(Constants.kTankL);

  // Gyro Init method for Robot
  private final Gyro gyro = new ADXRS450_Gyro(Constants.kGyroPort);

  // Creates an Drive groupe for a drivetrain
  private final DifferentialDrive drive = new DifferentialDrive(tankR, tankL);



  
  public Drivetrain(final Joystick m_driver) {

    this.DriverControl = m_driver;
    this.error = Constants.kError;
    this.kp = Constants.kProportional;

    gyro.calibrate();

    kPEntry = Shuffleboard.getTab("Test").add("PID P",0).withPosition(0, 1).withSize(Constants.kWidgetWidth, Constants.kWidgetHight)
      .withWidget(BuiltInWidgets.kTextView).getEntry();

  }


  private void OpDrive(){

    // Pipes X and Y axises of Driver Controller into the Arcade Drive Class to Control Robot Drivetrain
    drive.arcadeDrive(DriverControl.getRawAxis(Constants.kDriverX), DriverControl.getRawAxis(Constants.kDriverY));

    if (toggledOn == true){

      driveStraight();
    }
    else{
      
    }

  }

  // Function to use a button as a toggle
  public void updateToggle(){
    if(DriverControl.getRawButton(Constants.kButtonA)){

      if(!toggledPress){
          
          toggledOn = !toggledOn;
          toggledPress= true;
      }
      else{
        toggledPress= false;
    }
  }
}

  private void driveStraight() {

    
    double turn_power = kp * error;
    drive.arcadeDrive(DriverControl.getRawAxis(Constants.kDriverX), turn_power);
  
    
  }

  private void ShuffleboardUpdate(){

    kPEntry.setDouble(kp);
  

  }

  @Override
  public void periodic() {
    
    OpDrive();
    updateToggle();
    ShuffleboardUpdate();
  }
}
