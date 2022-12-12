package solvers;

import model.CoordDay12;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolverDay12 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 12 :";
    }

    @Override
    public String solvePuzzleA() {
        CoordDay12[][] map;
        int score = 0;
        CoordDay12 start = new CoordDay12();
        CoordDay12 end = new CoordDay12();
        try (BufferedReader lines = Utils.getBufferInput("12")) {
            List<CoordDay12[]> temp = new ArrayList<>();
            int y = -1;
            for (String lineString; (lineString = lines.readLine()) != null; ) {
                y++;
                int x = -1;
                CoordDay12[] line = new CoordDay12[lineString.length()];
                temp.add(line);
                for (char elevation : lineString.toCharArray()) {
                    x++;
                    if (elevation == 'S') {
                        start.x = x;
                        start.y = y;
                        start.score = 0;
                        start.elevation = 0;
                        line[x] = start;
                    } else if (elevation == 'E') {
                        end.x = x;
                        end.y = y;
                        end.elevation = 26;
                        line[x] = end;
                    } else {
                        CoordDay12 coord = new CoordDay12();
                        coord.x = x;
                        coord.y = y;
                        coord.elevation = elevation - 96;
                        line[x] = coord;
                    }
                }
            }
            map = new CoordDay12[temp.size()][temp.get(0).length];
            for (int i = 0; i < temp.size(); i++) {
                map[i] = temp.get(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dijkstra(map, start, end);
        return "" + end.score;
    }

    private int dijkstra(CoordDay12[][] map, CoordDay12 start, CoordDay12 end) {
        List<CoordDay12> toTraverse = new ArrayList<>();
        toTraverse.add(start);
        int width = map[0].length;
        int height = map.length;

        while(true) {
            if (toTraverse.isEmpty()) return Integer.MAX_VALUE;
            CoordDay12 currentNode = toTraverse.get(0);
            toTraverse.remove(0);

            if (currentNode.equals(end)) {
                break;
            }

            List<CoordDay12> toConsider = new ArrayList<>();
            if (currentNode.x > 0) toConsider.add(map[currentNode.y][currentNode.x - 1]);
            if (currentNode.x < width - 1) toConsider.add(map[currentNode.y][currentNode.x + 1]);
            if (currentNode.y > 0) toConsider.add(map[currentNode.y - 1][currentNode.x]);
            if (currentNode.y < height - 1) toConsider.add(map[currentNode.y + 1][currentNode.x]);

            for (CoordDay12 node : toConsider) {
                if (node.elevation <= currentNode.elevation + 1 && node.score > currentNode.score + 1) {
                    node.score = currentNode.score+1;
                    toTraverse.add(node);
                }
            }

        }
        return end.score;

    }

    @Override
    public String solvePuzzleB() {
        CoordDay12[][] map;
        CoordDay12 start = new CoordDay12();
        CoordDay12 end = new CoordDay12();
        try (BufferedReader lines = Utils.getBufferInput("12")) {
            List<CoordDay12[]> temp = new ArrayList<>();
            int y = -1;
            for (String lineString; (lineString = lines.readLine()) != null; ) {
                y++;
                int x = -1;
                CoordDay12[] line = new CoordDay12[lineString.length()];
                temp.add(line);
                for (char elevation : lineString.toCharArray()) {
                    x++;
                    if (elevation == 'S') {
                        start.x = x;
                        start.y = y;
                        start.elevation = 0;
                        line[x] = start;
                    } else if (elevation == 'E') {
                        end.x = x;
                        end.y = y;
                        end.elevation = 26;
                        line[x] = end;
                    } else {
                        CoordDay12 coord = new CoordDay12();
                        coord.x = x;
                        coord.y = y;
                        coord.elevation = elevation - 96;
                        line[x] = coord;
                    }
                }
            }
            map = new CoordDay12[temp.size()][temp.get(0).length];
            for (int i = 0; i < temp.size(); i++) {
                map[i] = temp.get(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int minScore = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            for (CoordDay12 coord : map[i]) {
                if (coord.elevation == 1) {
                    coord.score = 0;
                    int score = dijkstra(map, coord, end);
                    if (score < minScore) minScore = score;
                    resetMap(map);
                }
            }
        }
        this.dijkstra(map, start, end);
        return "" + minScore;
    }

    private void resetMap(CoordDay12[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (CoordDay12 coord : map[i]) {
                coord.score = Integer.MAX_VALUE;
            }
        }
    }
}
