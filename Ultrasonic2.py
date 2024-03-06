# https://thepihut.com/blogs/raspberry-pi-tutorials/hc-sr04-ultrasonic-range-sensor-on-the-raspberry-pi
import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
TRIG = 23
ECHO = 24

print ("Distance Measurement In Progress")

GPIO.setup(TRIG,GPIO.OUT)
GPIO.setup(ECHO,GPIO.IN)

GPIO.output(TRIG, False)
print ("Waiting For Sensor To Settle")
time.sleep(2)

GPIO.output(TRIG, True)

time.sleep(0.00001) #10uS pulse to trigger the module

GPIO.output(TRIG, False)

while GPIO.input(ECHO)==0: #gets start time
  pulse_start = time.time()
while GPIO.input(ECHO)==1: #gets end time    
  pulse_end = time.time()

pulse_duration = pulse_end - pulse_start
distance = pulse_duration * (17150*2)
distance = round(distance, 2)
print ("Distance:",distance,"cm")

GPIO.cleanup()