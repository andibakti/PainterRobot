//Cheng Lin
//19 Sept 17
//Arduino code to control two brush DC motors with the Pololu DRV8835 Dual Motor Driver Kit for Raspberry Pi

//------------declare variables--------------------------
int DIR_1 = 2; //pin to control motor 1's direction (digital)
int PWM_1 = A1; //pin to control motor 1's speed (analog)

int DIR_2 = 3; //pin to control motor 2's direction (digital)
int PWM_2 = A0; //pin to control motor 2's speed (analog)

//declare int as factor for converting degrees to duration of turn (in milliseconds)
//factor will be determined through testing
int factor = 10;

//------------setup-------------------------------
void setup() {
  //set the direction pins as output
  pinMode(DIR_1, OUTPUT);
  pinMode(DIR_2, OUTPUT);

  //PWM pins do not need to be set as output to be used
}

//----------create methods to run motors-----------
void motorDelay(){
  
}
//we will construct the robot so
//LOW digital output is forward for M1
//HIGH digital output is forward for M2

//method to go forward, method accepts amount of time to travel forward
void forward (int count){
  analogWrite(PWM_1, 255); //set speed to max
  digitalWrite(DIR_1, LOW); //LOW means M1 goes forward

  //delay because Arduino does not have enough current to start both at same time
  delay(100);
  
  analogWrite(PWM_2, 255); //set speed to max
  digitalWrite(DIR_2, HIGH);

  delay(count);
  motorStop();
}
//method to go backward, method accepts amount of time to travel backward
void backward(int count) {
  
  analogWrite(PWM_1, 255);
  digitalWrite(DIR_1, HIGH); //HIGH means M1 goes backward

  //delay because Arduino does not have enough current to start both at same time
  delay(100);
  
  analogWrite(PWM_2, 255);
  digitalWrite(DIR_2, LOW); //LOW means M2 goes backward

  delay(count);
  motorStop();
}

//method to turn right, accepts a degree value for the magnitude of the turn
void rightTurn(int deg) {
  //right turns require M1 to go forward while M2 backward
  //motor 1 goes forward
  analogWrite(PWM_1, 255); //set speed to max
  digitalWrite(DIR_1, LOW); //LOW means M1 goes forward

  //delay because Arduino does not have enough current to start both at same time
  delay (100);

  //motor 2 goes backward
  analogWrite(PWM_2, 255); //set speed
  digitalWrite(DIR_2, LOW); //LOW means M2 goes backward

  delay(factor*deg);
  motorStop();
}

//method to turn left, accepts a degree value for the magnitude of the turn
void leftTurn(int deg) {
  //left turns require M2 to go forward while M1 backward
  //motor 2 goes forward
  analogWrite(PWM_2, 255); //set speed to max
  digitalWrite(DIR_2, HIGH); //LOW means M2 goes forward

  //delay because Arduino does not have enough current to start both at same time
  delay (100);

  //motor 1 goes backward
  analogWrite(PWM_1, 255); //set speed
  digitalWrite(DIR_1, HIGH); //LOW means M2 goes backward

  delay(factor*deg);
  motorStop();
}

//stop method, stops motors for 1 second
void motorStop() {
  analogWrite(PWM_1, 0);
  analogWrite(PWM_2, 0);
  delay(1000); //stop motor for one second
}

//-----------loop----------------------------------
void loop() {
  forward(1000);
  backward(1000);
  rightTurn(180);
  leftTurn(180);
  
}
