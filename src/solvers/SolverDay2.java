package solvers;

import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;

public class SolverDay2 implements Solver {

    final int SCORE_X_ROCK = 1;
    final int SCORE_Y_PAPER = 2;
    final int SCORE_Z_SCISSORS = 3;

    final int SCORE_LOSE = 0;
    final int SCORE_DRAW = 3;
    final int SCORE_WIN = 6;

    @Override
    public String getIntroString() {
        return "Day 2 :";
    }

    @Override
    public String solvePuzzleA() {
        int score = 0;
        try (BufferedReader lines = Utils.getBufferInput("2-A")) {
            for (String line; (line = lines.readLine()) != null; ) {
                String[] chars = line.split(" ");
                score += scoreRound(chars[0].toCharArray()[0], chars[1].toCharArray()[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + score;
    }

    @Override
    public String solvePuzzleB() {
        int score = 0;
        try (BufferedReader lines = Utils.getBufferInput("2-A")) {
            for (String line; (line = lines.readLine()) != null; ) {
                String[] chars = line.split(" ");
                score += realScoreRound(chars[0].toCharArray()[0], chars[1].toCharArray()[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + score;
    }


    private int scoreRound(char opponent, char me) {
        int result = 0;
        switch (me) {
            case 'X' -> result += SCORE_X_ROCK;
            case 'Y' -> result += SCORE_Y_PAPER;
            case 'Z' -> result += SCORE_Z_SCISSORS;
            default -> result +=0;
        }

        switch (opponent) {
            case 'A' -> result += (me == 'X' ? SCORE_DRAW : (me == 'Y' ? SCORE_WIN : SCORE_LOSE));
            case 'B' -> result += (me == 'X' ? SCORE_LOSE : (me == 'Y' ? SCORE_DRAW : SCORE_WIN));
            case 'C' -> result += (me == 'X' ? SCORE_WIN : (me == 'Y' ? SCORE_LOSE : SCORE_DRAW));
        }

        return result;
    }

    private int realScoreRound(char opponent, char outcome) {
        int result = 0;
        switch (outcome) {
            case 'X' -> result += SCORE_LOSE;
            case 'Y' -> result += SCORE_DRAW;
            case 'Z' -> result += SCORE_WIN;
            default -> result +=0;
        }

        switch (opponent) {
            case 'A' -> result += (outcome == 'X' ? SCORE_Z_SCISSORS : (outcome == 'Y' ? SCORE_X_ROCK : SCORE_Y_PAPER));
            case 'B' -> result += (outcome == 'X' ? SCORE_X_ROCK : (outcome == 'Y' ? SCORE_Y_PAPER : SCORE_Z_SCISSORS));
            case 'C' -> result += (outcome == 'X' ? SCORE_Y_PAPER : (outcome == 'Y' ? SCORE_Z_SCISSORS : SCORE_X_ROCK));
        }

        return result;
    }
}
