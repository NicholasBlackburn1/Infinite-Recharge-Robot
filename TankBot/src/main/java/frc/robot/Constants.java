/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    // DriveTrain Motors
    public static int kTankR = 0;
    public static int kTankL = 1;

    // Gyro Port 
    public static Port kGyroPort = Port.kOnboardCS0;

    // Turn Pid 
    public static double kError = 0;
    public static double kProportional = 0;

    // Controller Axises 
    public static int kDriverY = 0;
    public static int kDriverX = 5;

    // Controller Buttons
    public static int kButtonA = 1;
    public static int kButtonB = 2;
    public static int kButtonX = 3;
    public static int kButtonY = 4;
    

    // Shuffleboard Widget Pos Units Blocks
    public static int kWidgetHight = 1;
    public static int kWidgetWidth = 2;

    // Vision Processor Cam Modes
    public static int kVisionRGB = 1;
    public static int kVisionIR = 2;
    public static int kVisionDepth = 3;
}
