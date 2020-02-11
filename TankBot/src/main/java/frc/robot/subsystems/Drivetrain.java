/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * This Subsystem configures the Drivetrain of my robot
 */

public class Drivetrain extends SubsystemBase {

  // This Xbox Controller Object is used for Driving the Robot
  private Joystick DriverControl;

  private Boolean toggledOn = false;
  private Boolean toggledPress = false;

  // Motor Controllers for Drivetrain 
  private Spark tankR = new Spark(Constants.kTankR);
  private Spark tankL = new Spark(Constants.kTankL);

  // Creates an Drive groupe for a drivetrain
  private DifferentialDrive drive = new DifferentialDrive(tankR, tankL);

  
  public Drivetrain(Joystick m_driver) {

    this.DriverControl = m_driver;

  }


  public void OpDrive(){

    // Pipes X and Y axises of Driver Controller into the Arcade Drive Class to Control Robot Drivetrain
    drive.arcadeDrive(DriverControl.getRawAxis(Constants.kDriverX), DriverControl.getRawAxis(Constants.kDriverY));

  

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

  @Override
  public void periodic() {

    
    OpDrive();
    updateToggle();
  }
}
