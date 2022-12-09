package solvers;

import model.CoordDay9;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolverDay09 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 9 :";
    }

    @Override
    public String solvePuzzleA() {
        Map<String, Boolean> visited = new HashMap<>();
        CoordDay9 head = new CoordDay9();
        CoordDay9 tail = new CoordDay9();
        visited.put("" + tail.x + " " + tail.y, true);
        try (BufferedReader lines = Utils.getBufferInput("9")) {
            for (String line; (line = lines.readLine()) != null; ) {
                String[] directions = line.split(" ");
                //System.out.println(line);
                for (int i =0; i < Integer.parseInt(directions[1]); i++) {
                    switch (directions[0]) {
                        case "D" :
                            head.y--;
                            break;
                        case "U" :
                            head.y++;
                            break;
                        case "L" :
                            head.x--;
                            break;
                        case "R" :
                            head.x++;
                            break;
                    }
                    if (tail.follow(head) && !visited.containsKey("" + tail.x + " " + tail.y)) {
                        visited.put("" + tail.x + " " + tail.y, true);
                    }
                    //System.out.println("head : " + head.x + " , " + head.y + "; tail : " + tail.x + " , " + tail.y);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + visited.entrySet().size();
    }

    @Override
    public String solvePuzzleB() {
        Map<String, Boolean> visited = new HashMap<>();
        CoordDay9[] rope = new CoordDay9[10];
        for (int i = 0; i < rope.length ; i++) {
            rope[i] = new CoordDay9();
        }
        visited.put("" + rope[rope.length-1].x + " " + rope[rope.length-1].y, true);
        try (BufferedReader lines = Utils.getBufferInput("9")) {
            for (String line; (line = lines.readLine()) != null; ) {
                String[] directions = line.split(" ");
                for (int i =0; i < Integer.parseInt(directions[1]); i++) {
                    switch (directions[0]) {
                        case "D" :
                            rope[0].y--;
                            break;
                        case "U" :
                            rope[0].y++;
                            break;
                        case "L" :
                            rope[0].x--;
                            break;
                        case "R" :
                            rope[0].x++;
                            break;
                    }
                    for (int j = 1; j < rope.length; j++) {
                        rope[j].follow(rope[j-1]);
                    }
                    if (!visited.containsKey("" + rope[rope.length-1].x + " " + rope[rope.length-1].y)) {
                        visited.put("" + rope[rope.length-1].x + " " + rope[rope.length-1].y, true);
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + visited.entrySet().size();
    }
}
