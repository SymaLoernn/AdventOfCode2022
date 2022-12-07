package solvers;

import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;

public class SolverDay03 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 3 :";
    }

    @Override
    public String solvePuzzleA() {
        long score = 0;
        try (BufferedReader lines = Utils.getBufferInput("3-A")) {
            for (String line; (line = lines.readLine()) != null; ) {
                score += scoreLineA(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + score;
    }

    private int scoreLineA(String line) {
        int result = 0;
        // First, split in half
        int halfLength = line.length()/2;
        char[] halfA = line.substring(0, halfLength).toCharArray();
        String halfB = line.substring(halfLength);

        // Then, search for any common char
        for (int i = 0; i < halfA.length; i++) {
            if (halfB.indexOf(halfA[i]) != -1) {
                return scoreChar(halfA[i]);
            }
        }
        // Failsafe
        System.out.println("fail ...");
        System.out.println(line);
        System.out.println(halfA);
        System.out.println(halfB);
        return Integer.MIN_VALUE;
    }

    private int scoreChar(char c) {
        if (c < 95) {
            return c - 64 + 26;
        } else {
            return c - 96;
        }
    }

    @Override
    public String solvePuzzleB() {
        long score = 0;
        try (BufferedReader lines = Utils.getBufferInput("3-A")) {
            for (String lineA; (lineA = lines.readLine()) != null; ) {
                String lineB = lines.readLine();
                String lineC = lines.readLine();
                score += scoreGroup(lineA, lineB, lineC);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + score;
    }

    public int scoreGroup(String lineA, String lineB, String lineC) {
        char[] charsA = lineA.toCharArray();

        for (int i = 0; i < charsA.length; i++) {
            if ((lineB.indexOf(charsA[i]) != -1) && (lineC.indexOf(charsA[i]) != -1)) {
                return scoreChar(charsA[i]);
            }
        }
        //Failsafe
        return Integer.MIN_VALUE;
    }


}
