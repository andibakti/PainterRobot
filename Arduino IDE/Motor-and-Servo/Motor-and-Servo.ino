//Cheng Lin
//19 Sept 17
//Arduino code to control two brush DC motors with the Pololu DRV8835 Dual Motor Driver Kit for Raspberry Pi

#include <Servo.h> //import servo commands
Servo servo; //declare servo

//------------declare variables--------------------------
int DIR_1 = 2; //pin to control motor 1's direction (digital)
int PWM_1 = A1; //pin to control motor 1's speed (analog)

int DIR_2 = 3; //pin to control motor 2's direction (digital)
int PWM_2 = A0; //pin to control motor 2's speed (analog)

//declare int as factor for converting degrees to duration of turn (in milliseconds)
//factor will be determined through testing
int factor = 10;

int rotate = 40; //change rotate to alter how much servo turns

//------------setup-------------------------------
void setup() {
  //set the direction pins as output
  pinMode(DIR_1, OUTPUT);
  pinMode(DIR_2, OUTPUT);

  //PWM pins do not need to be set as output to be used

  servo.attach(4); //which pin the servo's orange wire will go in
  servo.write(40); //servo is at 0 degrees
}

//----------create methods to run motors-----------
void motorDelay(){
  
}
//we will construct the robot so
//HIGH digital output is forward for M1
//LOW digital output is forward for M2

//method to go forward, method accepts amount of time to travel forward
void forward (int count){
  digitalWrite(DIR_1, HIGH); //HIGH means M1 goes forward
  digitalWrite(DIR_2, LOW); //LOW means M2 goes forward

  //delay because Arduino does not have enough current to start both at same time
  delay(100);

  analogWrite(PWM_1, 255); //set speed to max
  analogWrite(PWM_2, 255);

  delay(count);
  motorStop();
}
//method to go backward, method accepts amount of time to travel backward
void backward(int count) {

  digitalWrite(DIR_1, LOW); //LOW means M1 goes backward
  digitalWrite(DIR_2, HIGH); //HIGH means M2 goes backward
  
  //delay because Arduino does not have enough current to start both at same time
  delay(100);

  analogWrite(PWM_1, 255); //set speed
  analogWrite(PWM_2, 255);

  delay(count);
  motorStop();
}

//method to turn right, accepts a degree value for the magnitude of the turn
void rightTurn(int deg) {
  //left turns require M2 to go forward while M1 backward
  digitalWrite(DIR_1, LOW); //HIGH means M1 goes backward
  digitalWrite(DIR_2, LOW); //HIGH means M2 goes forward

  delay (100);

  analogWrite(PWM_1, 255); //set speed
  analogWrite(PWM_2, 255); //set speed to max

  delay(factor*(360-deg));
  motorStop();
}

//method to turn left, accepts a degree value for the magnitude of the turn
void leftTurn(int deg) {
  //left turns require M2 to go forward while M1 backward
  digitalWrite(DIR_1, LOW); //HIGH means M1 goes backward
  digitalWrite(DIR_2, LOW); //HIGH means M2 goes forward

  delay (100);

  analogWrite(PWM_2, 255); //set speed to max
  delay(100);
  analogWrite(PWM_1, 255); //set speed

  delay(factor*deg);
  motorStop();
}

//stop method, stops motors for 1 second
void motorStop() {
  analogWrite(PWM_1, 0);
  analogWrite(PWM_2, 0);
  delay(1000); //stop motor for one second
}

//turn servo so it drops the pen down
void penDown(){
 servo.write(0); //turn servo to specified degree
 delay(1000);
}

//turn servo arm so it lifts the pen up
void penUp() {
 servo.write(rotate); //turn servo back to original
 delay(1000);
}

//-----------loop----------------------------------
void loop() {
  //penDown();
  //forward(1000);
  //rightTurn(180);
  //leftTurn(180);
  //backward(1000);
  //penUp();
}
