import RPi.GPIO as GPIO 
from time import sleep 

GPIO.setmode(GPIO.BCM) 

Motor1A = 2 
Motor1B = 3 
Motor1E = 4 
def setup(): # All pins as Outputs
    GPIO.setup(Motor1A,GPIO.OUT) 
    GPIO.setup(Motor1B,GPIO.OUT) 
    GPIO.setup(Motor1E,GPIO.OUT) 

#print "Stopping motor" 
def StopMotor():
    GPIO.output(Motor1E,GPIO.LOW) # to stop the motor 

#print "Motor going to Start forwards" 
def StartMotorForward():
    GPIO.output(Motor1A,GPIO.HIGH) # to run motor in clockwise direction 
    GPIO.output(Motor1B,GPIO.LOW) # put it high to rotate motor in anti-clockwise direction 
    GPIO.output(Motor1E,GPIO.HIGH) # Should be always high to start motor 

def StartMotorBackwards():
	GPIO.output(Motor1A,GPIO.LOW)
	GPIO.output(Motor1B,GPIO.HIGH)
	GPIO.output(Motor1E,GPIO.HIGH)


StartMotorForward()
sleep(5)
StartMotorBackwards()
sleep(5)
StopMotor()
sleep(5)
GPIO.cleanup() 