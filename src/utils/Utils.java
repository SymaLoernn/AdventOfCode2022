package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Utils {

    // Stocker les inputs dans : inputFolder/puzzleName/inputFileName
    // Dans mon cas pour le premier jour : C:\dev\AoC\input\1-A\input.txt

    private static final String inputFolder = "C:\\dev\\AoC\\input";
    private static final String inputFileName = "input.txt";

    public static Stream<String> getStreamInput(String puzzleName)
            throws IOException {
        return Files.lines(Paths.get(inputFolder, puzzleName, inputFileName));
    }

    public static BufferedReader getBufferInput(String puzzleName)
            throws IOException {
        return Files.newBufferedReader(Paths.get(inputFolder, puzzleName, inputFileName));
    }
}
