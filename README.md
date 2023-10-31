Speaking clock
============== 
Specification 
-------------
1. Given a 24-hour clock convert the current time into words e.g. "08:34" should be converted to "It's eight thirty four"
2. Make another controller to handle input from user in the format “11:25”
3. Return Midday and Midnight as "It's Midday" and "It's Midnight"

The time allotted to the test is 1 hour. Your solution will be judged on a number of criteria pertinent to good software development practice. Incomplete solutions are acceptable.
For the purposes of this exercise, please use Java 8 libraries.

NOTES :
==============
* Use JDK 8 or above
* Swagger implementation is preferable.
* Maven/Gradle projects only. MVN projects should build with “mvn clean install” command
* ReadMe file should contain build and deployment related information
* Use microservice architecture, exception handling and unit test cases should be present

BUILD STEPS:
==============
* Run 'mvn clean install' to make a build of the project (jar file).
* Copy the jar file into EC2 instance using the below command 
  `scp -i raga-frontend.pem web-0.0.1-SNAPSHOT.jar ubuntu@13.127.105.156:/home/ubuntu`
  replace the pem file with yours and the EC2 IP with yours.
* Connect to the EC2 instance using the below command.
  `ssh -i raga-frontend.pem  ubuntu@13.127.105.156`
  replace the pem file with yours and the EC2 IP with yours.
* Run the screen on the EC2 instance using the below command.
  `screen -r 1352523.speakingClock` 
  replace the screen name with that of your.
* Start the jar using the below command.
  `java -jar web-0.0.1-SNAPSHOT.jar`
  replace the jar name with that of yours.