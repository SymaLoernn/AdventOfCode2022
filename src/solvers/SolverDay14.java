package solvers;

import model.NodeDay14;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolverDay14 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 14 :";
    }

    @Override
    public String solvePuzzleA() {
        int maxX = 0;
        int maxY = 0;
        List<NodeDay14> nodes = new ArrayList<>();
        NodeDay14 currentNode = new NodeDay14(0,0);
        String[] coordPairs;
        String[] coords;
        int[][] map;

        // Parse input
        try (BufferedReader lines = Utils.getBufferInput("14")) {
            for (String line; (line = lines.readLine()) != null; ) {
                coordPairs = line.split(" -> ");
                for (int j = 0; j < coordPairs.length; j++) {

                    coords = coordPairs[j].split(",");
                    if (j == 0) {
                        currentNode = new NodeDay14(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                        nodes.add(currentNode);
                    } else {
                        currentNode.next = new NodeDay14(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                        currentNode = currentNode.next;
                    }
                    if (currentNode.x > maxX) maxX = currentNode.x;
                    if (currentNode.y > maxY) maxY = currentNode.y;

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Map construct
        map = new int[maxY + 10][maxX + 10];
        for (NodeDay14 node : nodes) {
            NodeDay14 tempNode = node;
            NodeDay14 previousNode = null;
            while (tempNode != null) {
                if (previousNode != null) {
                    if (previousNode.x != tempNode.x) {
                        for (int i = Math.min(previousNode.x, tempNode.x); i <= Math.max(previousNode.x, tempNode.x); i++) {
                            map[tempNode.y][i] = 1;
                        }
                    } else {
                        for (int i = Math.min(previousNode.y, tempNode.y); i <= Math.max(previousNode.y, tempNode.y); i++) {
                            map[i][previousNode.x] = 1;
                        }
                    }
                }

                previousNode = tempNode;
                tempNode = tempNode.next;
            }
        }

        // Falling sand
        int sandAmount = 0;
        NodeDay14 sandNode = new NodeDay14(500,0);
        while (true) {
            // is Sand Node out ?
            if (sandNode.y > maxY) {
                break;
            }

            // is Sand node settled
            if (map[sandNode.y + 1][sandNode.x - 1] != 0 && map[sandNode.y + 1][sandNode.x] != 0 && map[sandNode.y + 1][sandNode.x + 1] != 0) {
                map[sandNode.y][sandNode.x] = 2;
                sandNode.y = 0;
                sandNode.x = 500;
                sandAmount++;
            } else {
                // Move sand down first, then left then right
                if (map[sandNode.y+1][sandNode.x] == 0) {
                    sandNode.y += 1;
                } else if (map[sandNode.y+1][sandNode.x-1] == 0) {
                    sandNode.y += 1;
                    sandNode.x -=1;
                } else {
                    sandNode.y += 1;
                    sandNode.x +=1;
                }
            }
        }
        return "" + sandAmount;
    }

    @Override
    public String solvePuzzleB() {
        int maxX = 0;
        int maxY = 0;
        List<NodeDay14> nodes = new ArrayList<>();
        NodeDay14 currentNode = new NodeDay14(0,0);
        String[] coordPairs;
        String[] coords;
        int[][] map;

        // Parse input
        try (BufferedReader lines = Utils.getBufferInput("14")) {
            for (String line; (line = lines.readLine()) != null; ) {
                coordPairs = line.split(" -> ");
                for (int j = 0; j < coordPairs.length; j++) {

                    coords = coordPairs[j].split(",");
                    if (j == 0) {
                        currentNode = new NodeDay14(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                        nodes.add(currentNode);
                    } else {
                        currentNode.next = new NodeDay14(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                        currentNode = currentNode.next;
                    }
                    if (currentNode.x > maxX) maxX = currentNode.x;
                    if (currentNode.y > maxY) maxY = currentNode.y;

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Map construct
        map = new int[maxY + 10][maxX + 500];
        for (NodeDay14 node : nodes) {
            NodeDay14 tempNode = node;
            NodeDay14 previousNode = null;
            while (tempNode != null) {
                if (previousNode != null) {
                    if (previousNode.x != tempNode.x) {
                        for (int i = Math.min(previousNode.x, tempNode.x); i <= Math.max(previousNode.x, tempNode.x); i++) {
                            map[tempNode.y][i] = 1;
                        }
                    } else {
                        for (int i = Math.min(previousNode.y, tempNode.y); i <= Math.max(previousNode.y, tempNode.y); i++) {
                            map[i][previousNode.x] = 1;
                        }
                    }
                }

                previousNode = tempNode;
                tempNode = tempNode.next;
            }
        }
        Arrays.fill(map[maxY + 2], 1);

        // Falling sand
        int sandAmount = 0;
        NodeDay14 sandNode = new NodeDay14(500,0);
        while (true) {
            // is Sand Node out ?
            if (map[0][500] != 0) {
                break;
            }

            // is Sand node settled
            if (map[sandNode.y + 1][sandNode.x - 1] != 0 && map[sandNode.y + 1][sandNode.x] != 0 && map[sandNode.y + 1][sandNode.x + 1] != 0) {
                map[sandNode.y][sandNode.x] = 2;
                sandNode.y = 0;
                sandNode.x = 500;
                sandAmount++;
            } else {
                // Move sand down first, then left then right
                if (map[sandNode.y+1][sandNode.x] == 0) {
                    sandNode.y += 1;
                } else if (map[sandNode.y+1][sandNode.x-1] == 0) {
                    sandNode.y += 1;
                    sandNode.x -=1;
                } else {
                    sandNode.y += 1;
                    sandNode.x +=1;
                }
            }
        }
        return "" + sandAmount;
    }
}
