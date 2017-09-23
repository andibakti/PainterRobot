//Cheng Lin
//19 Sept 17
//Arduino code to run a servo

#include <Servo.h> //import servo commands
Servo servo; //declare servo

int rotate = 90; //change rotate to alter how much servo turns

void setup() {
  servo.attach(4); //which pin the servo's orange wire will go in
  servo.write(0); //servo is at 0 degrees
  delay(2000);

}

//modify value in servo.write to control how much you want to turn
void loop() {
 servo.write(rotate); //turn servo to 90 degrees
 delay(1000);
 servo.write(0); //turn servo to 0
 delay(1000);
}
