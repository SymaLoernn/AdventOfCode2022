package solvers;

import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class SolverDay5 implements Solver{
    @Override
    public String getIntroString() {
        return "Day 5 : ";
    }

    @Override
    public String solvePuzzleA() {

        boolean init = true;
        int stackAmount = -1;
        List<Stack<Character>> stacks = new ArrayList<>();
        try (BufferedReader lines = Utils.getBufferInput("5-A")) {

            for (String line; (line = lines.readLine()) != null; ) {

                if (init) {
                    // Init
                    // Check for end of init
                    if (line.startsWith(" 1")) {
                        init = false;
                        lines.readLine();
                        endInit(stacks);
                        continue;
                    }

                    // First line, check for amount of stacks
                    if (stackAmount == -1) {
                        stackAmount = countStacks(line);
                        for (int i = 0; i < stackAmount; i++) {
                            stacks.add(new Stack<>());
                        }
                    }

                    for (int i = 0; i < stackAmount; i++) {
                        if (line.charAt((i * 4) + 1) != ' ') stacks.get(i).push(line.charAt((i * 4) + 1));
                    }

                } else {
                    // Move boxes
                    int amount = Integer.parseInt(line.split(" ")[1]);
                    int from = Integer.parseInt(line.split(" ")[3]);
                    int to = Integer.parseInt(line.split(" ")[5]);
                    transferCrates(stacks.get(from - 1), stacks.get(to - 1), amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalState(stacks);
    }

    @Override
    public String solvePuzzleB() {
        boolean init = true;
        int stackAmount = -1;
        List<Stack<Character>> stacks = new ArrayList<>();
        try (BufferedReader lines = Utils.getBufferInput("5-A")) {

            for (String line; (line = lines.readLine()) != null; ) {

                if (init) {
                    // Init
                    // Check for end of init
                    if (line.startsWith(" 1")) {
                        init = false;
                        lines.readLine();
                        endInit(stacks);
                        continue;
                    }

                    // First line, check for amount of stacks
                    if (stackAmount == -1) {
                        stackAmount = countStacks(line);
                        for (int i = 0; i < stackAmount; i++) {
                            stacks.add(new Stack<>());
                        }
                    }

                    for (int i = 0; i < stackAmount; i++) {
                        if (line.charAt((i * 4) + 1) != ' ') stacks.get(i).push(line.charAt((i * 4) + 1));
                    }

                } else {
                    // Move boxes
                    int amount = Integer.parseInt(line.split(" ")[1]);
                    int from = Integer.parseInt(line.split(" ")[3]);
                    int to = Integer.parseInt(line.split(" ")[5]);
                    trasferCratesUPGRADED(stacks.get(from - 1), stacks.get(to - 1), amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalState(stacks);
    }

    private void transferCrates(Stack<Character> from, Stack<Character> to, int amount) {
        for (int i = 0; i < amount; i++) {
            to.push(from.pop());
        }
    }

    private void trasferCratesUPGRADED(Stack<Character> from, Stack<Character> to, int amount) {
        Stack<Character> temp = new Stack<>();
        for (int i = 0; i < amount; i++) {
            temp.push(from.pop());
        }

        for (int i = 0; i < amount; i++) {
            to.push(temp.pop());
        }
    }

    private String finalState(List<Stack<Character>> allStacks) {
        StringBuilder sb = new StringBuilder();
        allStacks.forEach(stack -> sb.append(stack.pop()));
        return sb.toString();
    }

    private int countStacks(String line) {
        return (line.length() + 1)/4;
    }

    private void endInit(List<Stack<Character>> stacks) {
        for (Stack<Character> stack : stacks) {
            Collections.reverse(stack);
            StringBuilder sb = new StringBuilder();
            for (Character c : stack) {
                sb.append(c);
            }
            //System.out.println(sb);
        }
    }
}
