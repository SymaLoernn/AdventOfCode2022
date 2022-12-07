package solvers;

import utils.StringUtils;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class SolverDay01 implements Solver {

    @Override
    public String getIntroString() {
        return "Day 1 :";
    }

    @Override
    public String solvePuzzleA() {
        long max = -1;
        long current = 0;
        try (BufferedReader lines = Utils.getBufferInput("1-A")) {
            for (String line; (line = lines.readLine()) != null; ) {
                // Si vide, on change d'elfe
                if (StringUtils.isEmpty(line)) {
                    if (current > max) max = current;
                    current = 0;
                } else {
                    current += Long.parseLong(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + max;
    }

    @Override
    public String solvePuzzleB() {
        long[] maxes = {-1, -1, -1};
        long current = 0;
        try (BufferedReader lines = Utils.getBufferInput("1-A")) {
            for (String line; (line = lines.readLine()) != null; ) {
                // Si vide, on change d'elfe
                if (StringUtils.isEmpty(line)) {
                    updateMaxes(maxes, current);
                    current = 0;
                } else {
                    current += Long.parseLong(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + Arrays.stream(maxes).sum();
    }

    public void updateMaxes(long[] maxes, long calories) {
        if (calories > maxes[2]) {
            maxes[0] = maxes[1];
            maxes[1] = maxes[2];
            maxes[2] = calories;
        } else if (calories > maxes[1]) {
            maxes[0] = maxes[1];
            maxes[1] = calories;
        } else if (calories > maxes[0]) {
            maxes[0] = calories;
        }
    }
}
