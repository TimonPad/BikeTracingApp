import RPi.GPIO as GPIO
from time import sleep
# Pins for Motor Driver Inputs
Motor1A = 2
Motor1B = 3
Motor1E = 4
p = 0 

def setup():
    global p
    GPIO.setmode(GPIO.BCM) # GPIO Numbering
    GPIO.setup(Motor1A,GPIO.OUT) # All pins as Outputs
    GPIO.setup(Motor1B,GPIO.OUT)
    GPIO.setup(Motor1E,GPIO.OUT)
    p = GPIO.PWM(Motor1E,100)
    p.start(0)

'''
Function to run motor forward.
Input pwm
'''

def forward(pwm)
    pass

'''
Function to run motor backward.
Input pwm
'''

def backward(pwm)
    pass

'''
Function to stop motor
'''

def stop()
    pass

'''
Function to vibrate motor -.o
Input intensity 1-10
'''
def vibrate()
    pass

def loop():
    global p
    # Going forwards 5s

    GPIO.output(Motor1A,GPIO.HIGH)
    GPIO.output(Motor1B,GPIO.LOW)
    p.ChangeDutyCycle(25)
    print("25% forward")
    GPIO.output(Motor1E,GPIO.HIGH)
    sleep(2)
    
    #Stop 5s
    GPIO.output(Motor1E,GPIO.LOW)
    sleep(5)

    # Stop
    GPIO.output(Motor1E,GPIO.LOW)

def destroy():
    global p
    p.stop()
    GPIO.cleanup()
    
if __name__ == '__main__': # Program start from here
    setup()
try:
    loop()
except:
    print ("Hi u called")
    #destroy()
finally:
    destroy()
    
