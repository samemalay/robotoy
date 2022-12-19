package org.demo.core;

import org.demo.App;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.demo.core.Board.LOWER_BOUND;
import static org.demo.core.Board.UPPER_BOUND;

public class Robot {

    private int id;
    private Placement placement;

    private static Map<Integer, Robot> robots = new HashMap<>();
    private static int activeRobot;

    public static Optional<Robot> getActivatedInstance(int id) {
        if (activate(id))
            return Optional.of(robots.get(activeRobot));
        else
            return Optional.empty();
    }

    public static Optional<Robot> getInstance(Placement placement) {
            if (!App.isMulti()) {
                    Robot robot = null;
                    if (robots.size() > 0) {
                        robot = robots.get(1);
                        Placement oldPlacement = robot.placement;
                        Board.release(oldPlacement.getX(), oldPlacement.getY());
                        if (Board.occupy(placement.getX(), placement.getY())) {
                            robot.placement = placement;
                            return Optional.of(robot);
                        }
                        else {
                            Board.occupy(oldPlacement.getX(), oldPlacement.getY());
                            return Optional.empty();
                            // active robot is still the same - out of board placement possibly; ignored
                        }
                    } else {
                        //first initiation
                        if (Board.occupy(placement.getX(), placement.getY())) {
                            robot = new Robot(placement);
                            activeRobot = 1;
                            robots.put(activeRobot, robot);
                            return Optional.of(robot);
                        }
                        else
                            return Optional.empty();
                    }
            }
            else {
                //check board status also assign id and add to map
                if (Board.occupy(placement.getX(), placement.getY())) {
                    int id = robots.size() + 1;
                    Robot robot = new Robot(placement);
                    robot.setId(id);
                    robots.put(id, robot);
                    activeRobot = id;
                    return Optional.of(robot);
                }
                else
                    return Optional.empty();
            }
    }

    private Robot(Placement placement) {
        this.placement = placement;
    }

    private void setId(int id) {
        this.id = id;
    }

    public static boolean activate(int id) {
        if (id > 0 && id <= robots.size()) {
            activeRobot = id;
            return true;
        } else {
            return false;
        }
    }

    public static int getActiveRobot() {
        return activeRobot;
    }

    public Placement getPlacement() {
        return placement;
    }

    public String formattedReport() {
        if (!App.isMulti()) {
            return "Output: " + this.placement.getX() + "," + this.placement.getY()
                    + "," + this.placement.getFace().name();
        }
        else {
            Robot rob = robots.get(activeRobot);
            return "Output: number of robots : " + robots.size() +
                    ", active robot - id : " + activeRobot + ", status : " +
                    rob.placement.getX() + "," + rob.placement.getY() +
                    "," + rob.placement.getFace().name();
        }
    }

    public void report() {
        String result = formattedReport();
        App.setEndReport(result);
        System.out.println(result);
    }
}
