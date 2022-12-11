package solvers;

import model.MonkeyDay11;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolverDay11 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 11 :";
    }

    @Override
    public String solvePuzzleA() {
        List<MonkeyDay11> monkeys = new ArrayList<>();
        long result = 0;
        for (int i = 0; i < 8; i++) {
            monkeys.add(new MonkeyDay11(i));
        }
        try (BufferedReader lines = Utils.getBufferInput("11")) {
            for (int i = 0; i < 8; i++) {
                // Monkey line
                lines.readLine();
                String items = lines.readLine();
                String operation = lines.readLine();
                String test = lines.readLine();
                String ifTrue = lines.readLine();
                String ifFalse = lines.readLine();
                monkeys.get(i).init(items, operation, test, ifTrue, ifFalse, monkeys);
                //Empty line
                lines.readLine();

            }

            for (int i = 0; i < 20; i++) {
                for (MonkeyDay11 monkey : monkeys) {
                    monkey.processTurn();
                }
            }

            long[] maxes = {0,0};
            for (MonkeyDay11 monkey : monkeys) {
                if (monkey.inspectionAmount > maxes[0]) {
                    maxes[1] = maxes[0];
                    maxes[0] = monkey.inspectionAmount;
                } else if (monkey.inspectionAmount > maxes[1]) {
                    maxes[1] = monkey.inspectionAmount;
                }
            }
            result = maxes[0] * maxes[1];

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + result;
    }

    @Override
    public String solvePuzzleB() {
        List<MonkeyDay11> monkeys = new ArrayList<>();
        long result = 0;
        for (int i = 0; i < 8; i++) {
            monkeys.add(new MonkeyDay11(i));
        }
        try (BufferedReader lines = Utils.getBufferInput("11")) {
            for (int i = 0; i < 8; i++) {
                // Monkey line
                lines.readLine();
                String items = lines.readLine();
                String operation = lines.readLine();
                String test = lines.readLine();
                String ifTrue = lines.readLine();
                String ifFalse = lines.readLine();
                monkeys.get(i).init(items, operation, test, ifTrue, ifFalse, monkeys);
                //Empty line
                lines.readLine();

            }
            long criticalStress = 1;
            for (MonkeyDay11 monkey : monkeys) {
                criticalStress *= monkey.test;
            }
            for (MonkeyDay11 monkey : monkeys) {
                monkey.criticalStress = criticalStress;
            }

            for (int i = 0; i < 10000; i++) {
                for (MonkeyDay11 monkey : monkeys) {
                    monkey.processTurn();
                }
            }

            long[] maxes = {0,0};
            for (MonkeyDay11 monkey : monkeys) {
                if (monkey.inspectionAmount > maxes[0]) {
                    maxes[1] = maxes[0];
                    maxes[0] = monkey.inspectionAmount;
                } else if (monkey.inspectionAmount > maxes[1]) {
                    maxes[1] = monkey.inspectionAmount;
                }
            }
            result = maxes[0] * maxes[1];

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + result;
    }
}
