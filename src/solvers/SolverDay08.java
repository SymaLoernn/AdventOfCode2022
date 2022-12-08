package solvers;

import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolverDay08 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 8 :";
    }

    @Override
    public String solvePuzzleA() {
        long result = 0;
        List<String> lines = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        try (BufferedReader fileLines = Utils.getBufferInput("8")) {
            for (String line; (line = fileLines.readLine()) != null; ) {
                lines.add(line);
            }

            // Create columns
            for (int i = 0; i < lines.get(0).length(); i++) {
                StringBuilder sb = new StringBuilder();
                for (String line : lines) {
                    sb.append(line.charAt(i));
                }
                columns.add(sb.toString());
            }

            // check
            for (int x = 0; x < columns.size(); x++) {
                for (int y = 0; y < lines.size(); y++) {
                    if (checkTree(lines.get(y), columns.get(x), x, y, lines.get(y).charAt(x))) {
                        //System.out.println("x : " + x + "; y : " + y);
                        result++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + result;
    }

    public boolean checkTree(String line, String column, int x, int y, char tree) {
        String direction;
        // left
        direction = line.substring(0, x);
        if (checkDirection(direction, tree)) return true;

        // right
        direction = line.substring(x+1);
        if (checkDirection(direction, tree)) return true;

        // up
        direction = column.substring(0, y);
        if (checkDirection(direction, tree)) return true;

        // down
        direction = column.substring(y+1);
        if (checkDirection(direction, tree)) return true;

        return false;
    }

    public boolean checkDirection(String direction, char tree) {
        for (char c: direction.toCharArray()) {
            if (c >= tree) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String solvePuzzleB() {
        long result = 0;
        List<String> lines = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        try (BufferedReader fileLines = Utils.getBufferInput("8")) {
            for (String line; (line = fileLines.readLine()) != null; ) {
                lines.add(line);
            }

            // Create columns
            for (int i = 0; i < lines.get(0).length(); i++) {
                StringBuilder sb = new StringBuilder();
                for (String line : lines) {
                    sb.append(line.charAt(i));
                }
                columns.add(sb.toString());
            }

            // check
            long score = 0;
            for (int x = 0; x < columns.size(); x++) {
                for (int y = 0; y < lines.size(); y++) {
                    score = scoreTree(lines.get(y), columns.get(x), x, y, lines.get(y).charAt(x));
                    if (score > result) result = score;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + result;
    }

    public long scoreTree(String line, String column, int x, int y, char tree) {
        String direction;
        long result = 1;
        // left
        direction = line.substring(0, x);
        result *= scoreDirection(direction, tree, true);

        // right
        direction = line.substring(x+1);
        result *= scoreDirection(direction, tree, false);

        // up
        direction = column.substring(0, y);
        result *= scoreDirection(direction, tree, true);

        // down
        direction = column.substring(y+1);
        result *= scoreDirection(direction, tree, false);

        return result;
    }

    public int scoreDirection(String direction, char tree, boolean reverse) {
        int score = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(direction);
        if (reverse) {
            sb.reverse();
        }
        for (char c: sb.toString().toCharArray()) {
            score++;
            if (c >= tree) {
                return score;
            }
        }
        return score;
    }


}
