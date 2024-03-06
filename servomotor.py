import RPi.GPIO as GPIO
import time

servoPIN = 17
GPIO.setmode(GPIO.BCM)
GPIO.setup(servoPIN, GPIO.OUT)
pwm = GPIO.PWM(servoPIN, 50) # GPIO 17 for PWM with 50Hz
pwm.start(0) # Initialization

def SetAngle(angle):
    duty = (angle / 18 + 2)
    GPIO.output(servoPIN, True)
    pwm.ChangeDutyCycle(duty)
    time.sleep(1)
    GPIO.output (servoPIN, False)
    pwm.ChangeDutyCycle(0)


SetAngle(90);
SetAngle(45);
SetAngle(0);
pwm.stop()
GPIO.cleanup()
