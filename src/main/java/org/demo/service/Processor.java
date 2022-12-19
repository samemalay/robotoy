package org.demo.service;

import org.demo.core.Command;
import org.demo.core.Robot;

import java.util.List;
import java.util.Optional;

public class Processor {

    private static Robot activeRobot = null;

    public static void process(List<Command> commands) {
        for (Command command : commands)
            process(command);
    }

    private static void process(Command command) {
        Optional<Robot> robotOpt;
        switch(command.getMove()) {
            case PLACE:
                robotOpt = Robot.getInstance(command.getPlacement());
                if (robotOpt.isPresent())
                    activeRobot = robotOpt.get();
                break;
            case MOVE:
                if (activeRobot != null)
                    activeRobot.getPlacement().onMove();
                break;
            case LEFT:
                if (activeRobot != null)
                    activeRobot.getPlacement().onLeft();
                break;
            case RIGHT:
                if (activeRobot != null)
                    activeRobot.getPlacement().onRight();
                break;
            case REPORT:
                if (activeRobot != null)
                    activeRobot.report();
                break;
            case ROBOT:
                robotOpt = Robot.getActivatedInstance(command.getId());
                if (robotOpt.isPresent())
                    activeRobot = robotOpt.get();
                break;
            default:
                break;
        }
    }
}
