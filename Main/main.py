import ServoMotor
import StepperMotor
import Ultrasonic

 
cupsOrdered = 0
coffeeStrength = 3 # scale 0 - 5 (default = 3)

#main
"""
1. get current values?
2. move powder servo (90 degrees)
3. screw on for certain amount -> then stop
4. move powder servo back (0 degrees)
5. switch water pump (pumps water in smaller reservoir)
6. move water servo (90 degrees)
7. turn relay on (heating coil on for 15 min?)
8. move water servo (0 degrees)

9. Process complete?
"""


# main function
def main():
    print("main")
    #1 GET VALUES ULTRASONIC
    
    #2 CHECK ANGLE VALUE AFTER INSTALATION OF SERVO ON FRAME
    print("servo powder in place")
    ServoMotor.PowderServoAngleOn()
    
    #3 SCREW TIMER WITH INPUTS OF ORDERED AND STRENGTH (STEPPER)
    print("Stepper runs for 5 seconds")
    StepperMotor.countRunTime(5)
    
    #4 CHECK ANGLE VALUE AFTER INSTALATION OF SERVO ON FRAME
    print("servo powder moves away")
    ServoMotor.PowderServoAngleOff()
    
    #5 USE INPUT OF CUPS AS WATER AFTER TIMING FUNCTION IS DONE
    
    #6 CHANGE SERVO PIN FOR WATER AND CHECK ANGLE VALUE AFTER INSTALATION OF SERVO ON FRAME
    print("Servo water in place")
    ServoMotor.WaterServoAngleOn()
    
    
    #7 TURN RELAY ON FOR 15 MIN
    
    
    #8 CHANGE SERVO PIN FOR WATER AND CHECK ANGLE VALUE AFTER INSTALATION OF SERVO ON FRAME
    print("Servo water moves away")
    ServoMotor.WaterServoAngleOff()




if __name__ == "__main__":
    main()