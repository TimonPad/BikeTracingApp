import RPi.GPIO as GPIO
import time


def PowderServoAngleOn():
    servoPIN = 21
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(servoPIN, GPIO.OUT)
    pwm = GPIO.PWM(servoPIN, 50) # GPIO 17 for PWM with 50Hz
    pwm.start(0) # Initialization
    
    angle = 90
    duty = (angle / 18 + 2)
    GPIO.output(servoPIN, True)
    pwm.ChangeDutyCycle(duty)
    time.sleep(1)
    GPIO.output (servoPIN, False)
    pwm.ChangeDutyCycle(0)
    
    pwm.stop()
    GPIO.cleanup()

def PowderServoAngleOff():
    servoPIN = 21
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(servoPIN, GPIO.OUT)
    pwm = GPIO.PWM(servoPIN, 50) # GPIO 17 for PWM with 50Hz
    pwm.start(0) # Initialization
    
    angle = 0
    duty = (angle / 18 + 2)
    GPIO.output(servoPIN, True)
    pwm.ChangeDutyCycle(duty)
    time.sleep(1)
    GPIO.output (servoPIN, False)
    pwm.ChangeDutyCycle(0)
    
    pwm.stop()
    GPIO.cleanup()

def WaterServoAngleOn():
    servoPIN = 21
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(servoPIN, GPIO.OUT)
    pwm = GPIO.PWM(servoPIN, 50) # GPIO 17 for PWM with 50Hz
    pwm.start(0) # Initialization
    
    angle = 45
    duty = (angle / 18 + 2)
    GPIO.output(servoPIN, True)
    pwm.ChangeDutyCycle(duty)
    time.sleep(1)
    GPIO.output (servoPIN, False)
    pwm.ChangeDutyCycle(0)
    
    pwm.stop()
    GPIO.cleanup()
    
def WaterServoAngleOff():
    servoPIN = 21
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(servoPIN, GPIO.OUT)
    pwm = GPIO.PWM(servoPIN, 50) # GPIO 17 for PWM with 50Hz
    pwm.start(0) # Initialization
    
    angle = 0
    duty = (angle / 18 + 2)
    GPIO.output(servoPIN, True)
    pwm.ChangeDutyCycle(duty)
    time.sleep(1)
    GPIO.output (servoPIN, False)
    pwm.ChangeDutyCycle(0)
    
    pwm.stop()
    GPIO.cleanup()
    
    
PowderServoAngleOn()
time.sleep(2)
PowderServoAngleOff()

