package solvers;

import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;

public class SolverDay06 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 6 :";
    }

    @Override
    public String solvePuzzleA() {
        char[] buffer = {'a', 'a', 'a'};
        int i = 0;
        try (BufferedReader lines = Utils.getBufferInput("6")) {
            char[] line = lines.readLine().toCharArray();
            for (i = 0; i < line.length; i++) {
                if (checkAndUpdateMarker(buffer, line[i], i) != -1) break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + (i+1);
    }

    private int checkAndUpdateMarker(char[] buffer, char c, int i) {
        if (i > 2 && buffer[0] != buffer[1] && buffer[0] != buffer[2] && buffer[0] != c && buffer[1] != buffer[2] && buffer[1] != c && buffer[2] != c) {
            return i;
        } else {
            buffer[i%3] = c;
            return -1;
        }
    }

    private boolean checkAndUpdateComplicatedMarker(char[] buffer, char c, int i) {
        buffer[i%14] = c;
        return i >= 13 && allDifferent(buffer);
    }

    private boolean allDifferent(char[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            for (int j = i + 1 ; j < buffer.length; j++) {
                if (buffer[i] == buffer[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String solvePuzzleB() {
        char[] buffer = new char[14];
        int i = 0;
        try (BufferedReader lines = Utils.getBufferInput("6")) {
            char[] line = lines.readLine().toCharArray();
            for (i = 0; i < line.length; i++) {
                if (checkAndUpdateComplicatedMarker(buffer, line[i], i)) break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + (i+1);
    }
}
