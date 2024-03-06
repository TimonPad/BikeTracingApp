import sys
import time
import RPi.GPIO as GPIO

# define variables
coil_A_1_pin = 17 # IN1
coil_A_2_pin = 18 # IN2
coil_B_1_pin = 27 # IN3
coil_B_2_pin = 22 # IN4
 
# Use BCM GPIO references
GPIO.setmode(GPIO.BCM)
GPIO.setup( coil_A_1_pin, GPIO.OUT )
GPIO.setup( coil_A_2_pin, GPIO.OUT )
GPIO.setup( coil_B_1_pin, GPIO.OUT )
GPIO.setup( coil_B_2_pin, GPIO.OUT )
GPIO.setwarnings(False)
#Step sequence
StepCount = 8
#delay 
delay = 0.001
#360 degrees
steps = 4096

# defining stepper motor sequence (found in documentation http://www.4tronix.co.uk/arduino/Stepper-Motors.php)
Seq =            [[1,0,0,1],
                 [1,0,0,0],
                 [1,1,0,0],
                 [0,1,0,0],
                 [0,1,1,0],
                 [0,0,1,0],
                 [0,0,1,1],
                 [0,0,0,1]]
 
# initializing
GPIO.output( coil_A_1_pin, GPIO.LOW )
GPIO.output( coil_A_2_pin, GPIO.LOW )
GPIO.output( coil_B_1_pin, GPIO.LOW )
GPIO.output( coil_B_2_pin, GPIO.LOW )
 
def setStep(w1, w2, w3, w4):
    GPIO.output(coil_A_1_pin, w1)
    GPIO.output(coil_A_2_pin, w2)
    GPIO.output(coil_B_1_pin, w3)
    GPIO.output(coil_B_2_pin, w4)
    
def backwards(delay, steps):    
    for i in range(steps):
        for j in reversed(range(StepCount)):
            
            setStep(Seq[j][0], Seq[j][1], Seq[j][2], Seq[j][3])
            time.sleep(delay)   
    
def countRunTime(timeElapsed: int):
    StartTime = int(round(time.time()))
    RunTime = timeElapsed
    StopTime = int(round(time.time()))
    
    
    for i in range(steps):
        for j in reversed(range(StepCount)):
            setStep(Seq[j][0], Seq[j][1], Seq[j][2], Seq[j][3])
            time.sleep(delay)
            
            if (StartTime < (StopTime + RunTime)):
                print("Stepper run ", StartTime)
                StartTime = int(round(time.time()))
            else :
                print("Stepper stop change check pin to low")
                return

countRunTime(5)
