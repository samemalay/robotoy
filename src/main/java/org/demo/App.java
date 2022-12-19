package org.demo;

import org.demo.core.Command;
import org.demo.service.Processor;
import org.demo.validations.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.demo.service.Processor.process;

public class App
{
    private static final String filePath = "commands.txt";
    private static boolean multi;
    private static String endReport; //useful for unit test

    public static void main(String[] args)
    {
        if (args.length > 0 && "-m".equals(args[0]))
            multi = true;
        run(filePath);
    }

    public static void run(String filePath) {
        List<String> lines = Validate.getLines(filePath);
        List<Command> commands = new ArrayList<>();
        for (String line : lines) {
            //any wrong command quietly ignored
            Optional<Command> commandOpt = Validate.getCommand(line);
            if (commandOpt.isPresent()) {
                commands.add(commandOpt.get());
            }
        }

        if (commands.size() > 0)
            process(commands);
    }

    public static boolean isMulti() {
        return multi;
    }

    public static void setMulti(boolean multi) { //for unit test purposes
        App.multi = multi;
    }
    public static String getEndReport() {
        return endReport;
    }

    public static void setEndReport(String endReport) {
        App.endReport = endReport;
    }
}
