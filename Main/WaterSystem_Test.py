#imports
import RPi.GPIO as GPIO
import time


#GPIO CONSTANTS
RELAY_1 = 17
SENSOR_FLOW = 16
TRNSTR_1 = 24

#SETTING UP PINS FOR GPIO
GPIO.setmode(GPIO.BCM)
GPIO.setup(RELAY_1, GPIO.OUT)
GPIO.setup(SENSOR_FLOW, GPIO.IN)
GPIO.setup(TRNSTR_1, GPIO.OUT)

GPIO.output(RELAY_1, GPIO.HIGH) #relay off

#GLOBAL VARIABLES
water_flow = 0
usr_pulses = 0
ready = 1 # 1 == Waiting, 2 == Measuring water, 3 == Heating

#DEFINE FUNCTIONS
def relay_control(state: str):
    if state == "OFF": GPIO.output(RELAY_1, GPIO.HIGH)
    if state == "ON": GPIO.output(RELAY_1, GPIO.LOW)

def trnstr_control(state: str):
    if state == "OFF": GPIO.output(TRNSTR_1, GPIO.LOW)
    if state == "ON": GPIO.output(TRNSTR_1, GPIO.HIGH)

def sleep_ms (ms: int):
    time.sleep(ms/1000)

def interrupt_handler(channel):
    global water_flow
    global usr_pulses
    global ready
    water_flow += 1
    if (water_flow > usr_pulses):
        ready = 3
        #Turn solenoid valve off and heating on
        trnstr_control("OFF")
        relay_control("ON")
        #deactivate interupt
        GPIO.remove_event_detect(SENSOR_FLOW)
        
    print("water flow = ", water_flow)
    
def count_pulses(cups: int):
    print(cups, " of water is ", (450/1000)*220*cups, "ticks")
    return (450/1000)*220*cups

def measure_water(cup: int):
    global water_flow
    global usr_pulses
    water_flow = 0
    usr_pulses = count_pulses(cup)
    ready = 2
    GPIO.add_event_detect(SENSOR_FLOW, GPIO.RISING, callback=interrupt_handler)
    trnstr_control("ON")
    while (ready == 2):
        sleep_ms(1)

if __name__ == "__main__":
    try:
        while True:
            if(ready == 1):
                cup=int(input("How many cups? "))
                measure_water(cup)
            elif(ready == 3):
                print("Heating")
                time.sleep(5)
                ready = 1
                relay_control("OFF")
            else:
                sleep_ms(500)
    except Exception as e:
        print(e)
    finally:
        print("Cleaning connections and pins")
        GPIO.cleanup()