package solvers;

import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;

public class SolverDay10 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 10 :";
    }

    @Override
    public String solvePuzzleA() {
        long score = 0;
        int X = 1;
        int cycle = 0;
        int toApply = 0;
        try (BufferedReader lines = Utils.getBufferInput("10")) {
            for (String line; (line = lines.readLine()) != null; ) {
                cycle++;
                // Analyse
                if (line.startsWith("addx")) {
                    toApply = Integer.parseInt(line.split(" ")[1]);
                } else {
                    toApply = 0;
                }

                // Check
                if (cycle%40 == 20) {
                    score += (long) cycle * X;
                }

                if (toApply != 0 ) {
                    cycle++;
                    if (cycle%40 == 20) {
                        score += (long) cycle * X;
                    }
                }

                // Apply
                X += toApply;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + score;
    }

    @Override
    public String solvePuzzleB() {
        int X = 1;
        int cycle = -1;
        boolean first = true;
        int toApply = 0;
        StringBuilder lineToPrint = new StringBuilder();
        lineToPrint.append("|");
        System.out.println("*----------------------------------------*");
        try (BufferedReader lines = Utils.getBufferInput("10")) {
            for (String line; (line = lines.readLine()) != null; ) {
                cycle = (cycle+1)%40;
                // Analyse
                if (line.startsWith("addx")) {
                    toApply = Integer.parseInt(line.split(" ")[1]);
                } else {
                    toApply = 0;
                }

                // Check
                if (!first && cycle == 0) {
                    lineToPrint.append("|");
                    System.out.println(lineToPrint);
                    lineToPrint.setLength(0);
                    lineToPrint.append("|");
                }
                if (cycle - 1 == X || cycle == X || cycle + 1 == X) {
                    lineToPrint.append("#");
                } else {
                    lineToPrint.append(".");
                }

                if (toApply != 0 ) {
                    cycle = (cycle+1)%40;
                    if (!first && cycle == 0) {
                        lineToPrint.append("|");
                        System.out.println(lineToPrint);
                        lineToPrint.setLength(0);
                        lineToPrint.append("|");
                    }
                    if (cycle - 1 == X || cycle == X || cycle + 1 == X) {
                        lineToPrint.append("#");
                    } else {
                        lineToPrint.append(".");
                    }
                }
                first = false;

                // Apply
                X += toApply;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lineToPrint.append("|");
        System.out.println(lineToPrint);
        System.out.println("*----------------------------------------*");
        return "^^^^^^^^^^^^^^";
    }
}
