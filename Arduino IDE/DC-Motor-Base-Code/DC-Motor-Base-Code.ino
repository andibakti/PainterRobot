//Cheng Lin
//19 Sept 17
//Arduino code to control two brush DC motors with the Pololu DRV8835 Dual Motor Driver Kit for Raspberry Pi

//------------declare variables--------------------------
int DIR_1 = 1; //pin to control motor 1's direction (digital)
int PWM_1 = A1; //pin to control motor 1's speed (analog)

int DIR_2 = 2; //pin to control motor 2's direction (digital)
int PWM_2 = A2; //pin to control motor 2's speed (analog)

//declare int to control motor speed
//(value will be the pulse width of the PWM, 0-255)
int speed = 255;

//integer for delay so motors don't start at the same time
//(Arduino doesn't have enough current to start both at same time)
int count = 0;

//------------setup-------------------------------
void setup() {
  //set the direction pins as output
  pinMode(DIR_1, OUTPUT);
  pinMode(DIR_2, OUTPUT);

  //PWM pins do not need to be set as output to be used
}

/*/----------create methods to run motors-----------
//we will construct the robot so a LOW digital output is forward
//and a HIGH digital output is backward

void M1_forward (){
  digitalWrite(DIR_1, LOW);
  analogWrite(PWM_1, 255);
}*/


//-----------loop----------------------------------
void loop() {
  analogWrite(PWM_1, 255);

  if (count == 0) {
    delay(100);
    count++;
  }
  analogWrite(PWM_2, 255);
}
