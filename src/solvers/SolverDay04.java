package solvers;

import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;

public class SolverDay04 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 4 : ";
    }

    @Override
    public String solvePuzzleA() {
        long result = 0;
        try (BufferedReader lines = Utils.getBufferInput("4-A")) {
            for (String line; (line = lines.readLine()) != null; ) {
                if (isOneElfUseless(line)) result++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + result;
    }

    private boolean isOneElfUseless(String line) {
        String[] firstStrings = line.split(",")[0].split("-");
        String[] secondStrings = line.split(",")[1].split("-");

        long[] elfA = {Long.parseLong(firstStrings[0]), Long.parseLong(firstStrings[1])};
        long[] elfB = {Long.parseLong(secondStrings[0]), Long.parseLong(secondStrings[1])};

        return (elfA[0] <= elfB[0] && elfA[1] >= elfB[1]) || (elfB[0] <= elfA[0] && elfB[1] >= elfA[1]);

    }

    @Override
    public String solvePuzzleB() {
        long result = 0;
        try (BufferedReader lines = Utils.getBufferInput("4-A")) {
            for (String line; (line = lines.readLine()) != null; ) {
                if (isOneElfPartlyUseless(line)) result++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + result;
    }

    private boolean isOneElfPartlyUseless(String line) {
        String[] firstStrings = line.split(",")[0].split("-");
        String[] secondStrings = line.split(",")[1].split("-");

        long[] elfA = {Long.parseLong(firstStrings[0]), Long.parseLong(firstStrings[1])};
        long[] elfB = {Long.parseLong(secondStrings[0]), Long.parseLong(secondStrings[1])};

        return (elfA[0] <= elfB[0] && elfA[1] >= elfB[0]) || (elfB[0] <= elfA[0] && elfB[1] >= elfA[0]);

    }
}
