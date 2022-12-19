package org.demo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    public static Optional<List<String>> readLines (String filePath) {
        try (
            Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return Optional.of(lines.collect(Collectors.toList()));
        } catch (IOException ex) {
            System.out.println("Unable to read from file "
                    + filePath + " : Check it's existence in the path and permission.");
            return Optional.empty();
        }
    }

}
