# robotoy : toy robot movement simulation

### Problem Description

- The toy robot moves on a 5 * 5 square tabletop
- It takes four commands 

```
PLACE X,Y,F
MOVE
LEFT
RIGHT
REPORT
```
Where PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST. The origin (0,0) can be considered to be the SOUTH WEST most corner.

It is required that the first command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application should discard all commands in the sequence until a valid PLACE command has been executed.

Where MOVE will move the toy robot one unit forward in the direction it is currently facing.

Where LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.

Where REPORT will announce the X,Y and facing of the robot. This can be in any form, but standard output is sufficient.

A robot that is not on the table can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands.

#### Constraints
- The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot. Any move that would cause the robot to fall must be ignored.

#### Example
```
PLACE 1,2,EAST
MOVE
MOVE
LEFT
MOVE
REPORT

Output: 3,3,NORTH
```

#### extension
- It can be extended to multiple robots on the same board
- In 'multi' mode, as soon as a valid placement occurs, a new robot to the table is to be added with incrementing number identifier (e.g. 'Robot 1', 'Robot 2' etc.). Note that placement can not occur on an already occupied cell of the tabletop.
- 'multi' mode has a new command. A ROBOT <number> command will make the robot identified by active i.e. subsequent commands will affect that robot's position/direction. Any command that affects position/direction (e.g. MOVE, LEFT, RIGHT...) will affect only the active robot.

### Solution details
__Technology__

Java 11, Intellij IDEA, Maven

### How to run
- In order to run it, it is best to compile it using maven from the main project folder with the command 
```
mvn package
```
- Then copy the jar file robotoy-1.0.jar from the target folder to an appropriate working directory where you want to test. (Referring henceforth to this directory as test directory).
- create/copy a text file in the test directory named 'commands.txt' with appropriate commands. one command per line.
- run the program as 
```
java -jar robotoy-1.0.jar
```
for normal (non-multi) mode
- For multi mode, run it as 
```
java -jar robotoy-1.0.jar -m
```


### Notes
- Multi mode has been implemented
- Unit tests has been added to the project
- The solution is tested
- Invalid commands are ignored but other commands are executed
- test resource file has been added for multi test, however no test has been given. This is because this is an App, designed to run either in single mode or in multi mode, and trying to unit test both mode one after another will produce undesirable results (unless the Tabletop status is completely reset).  