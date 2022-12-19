package org.demo.validations;

import org.demo.App;
import org.demo.core.*;
import org.demo.utils.FileUtil;

import java.util.List;
import java.util.Optional;

import static org.demo.core.Board.LOWER_BOUND;
import static org.demo.core.Board.UPPER_BOUND;

public class Validate {

    public static Optional<Command> getCommand(String line) {
        if (line.startsWith("PLACE")) {
            String place = line.substring(5).trim();
            String[] args = place.split(",");
            if (args.length < 3)
                return Optional.empty();
            else {
                try {
                    int x = Integer.parseInt(args[0]);
                    int y = Integer.parseInt(args[1]);
                    String possibleFace = args[2].trim();
                    Face face = null; //had to initialise for compilation
                    for (Face e : Face.values()) {
                        if (possibleFace.equals(e.name())){
                            face = e;
                            break;
                        }
                    }
                    if (x >= LOWER_BOUND &&
                            x <= UPPER_BOUND &&
                            y >= LOWER_BOUND &&
                            y <= UPPER_BOUND &&
                            face != null)
                        return Command.getInstance(Move.PLACE, x, y, face);
                    else
                        return Optional.empty();
                } catch (NumberFormatException ex) {
                    //invalid integer, ignore
                    return Optional.empty();
                }
            }
        }
        else if(line.startsWith("ROBOT")) {
            if (!App.isMulti())
                return Optional.empty();
            String possibleId = line.substring(5).trim();
            if (possibleId.length() > 0) {
                try {
                    int id = Integer.parseInt(possibleId);
                    return Command.getInstance(Move.ROBOT, id, 0, null);
                } catch (NumberFormatException ex) {
                    //invalid integer, ignore
                    return Optional.empty();
                }
            } else
                return Optional.empty();
        }
        else if(line.startsWith("MOVE"))
            return Command.getInstance(Move.MOVE, 0, 0, null);
        else if(line.startsWith("LEFT"))
            return Command.getInstance(Move.LEFT, 0, 0, null);
        else if(line.startsWith("RIGHT"))
            return Command.getInstance(Move.RIGHT, 0, 0, null);
        else if(line.startsWith("REPORT"))
            return Command.getInstance(Move.REPORT, 0, 0, null);
        else
            return Optional.empty();
    }

    public static List<String> getLines(String filePath) {
        Optional<List<String>> linesOpt = FileUtil.readLines(filePath);
        if (linesOpt.isPresent())
            return linesOpt.get();
        else {
            System.out.println("Could not read from file. Exiting ...");
            System.exit(1);
            return null; //this is only to succeed compilation
        }
    }

}
