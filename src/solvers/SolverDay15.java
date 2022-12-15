package solvers;

import model.NodeDay15;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolverDay15 implements Solver {

    public static final int LINE_CALC = 2000000;
    public static final int MIN_DISTRESS = 0;
    public static final int MAX_DISTRESS = 4000000;

    @Override
    public String getIntroString() {
        return "Day 15 :";
    }

    @Override
    public String solvePuzzleA() {
        List<NodeDay15> sensors = new ArrayList<>();
        List<NodeDay15> beacons = new ArrayList<>();
        try (BufferedReader lines = Utils.getBufferInput("15")) {
            for (String line; (line = lines.readLine()) != null; ) {
                NodeDay15 sensor;
                NodeDay15 beacon;
                String sensorString = line.split("at ")[1].split(":")[0];
                String beaconString = line.split("at ")[2];

                sensor = new NodeDay15(Long.parseLong(sensorString.split(", ")[0].substring(2)), Long.parseLong(sensorString.split(", ")[1].substring(2)));
                sensors.add(sensor);
                beacon = new NodeDay15(Long.parseLong(beaconString.split(", ")[0].substring(2)), Long.parseLong(beaconString.split(", ")[1].substring(2)));
                // check if beacon is known
                boolean toAdd = true;
                for (NodeDay15 known : beacons) {
                    if (beacon.x == known.x && beacon.y == known.y) {
                        beacon = known;
                        toAdd = false;
                        break;
                    }
                }
                if (toAdd) {
                    beacons.add(beacon);
                }
                beacon.addSensor(sensor);
                sensor.setClosest(beacon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        for (NodeDay15 sensor : sensors) {
            sensor.checkCoord20();
            if (sensor.intersect20) {
                if (sensor.x20min < minX) {
                    minX = sensor.x20min;
                }
                if (sensor.x20max > maxX) {
                    maxX = sensor.x20max;
                }
            }
        }
        boolean[] magicLine = new boolean[(int)(maxX - minX + 1)];
        for (NodeDay15 sensor : sensors) {
            if (sensor.intersect20) {
                for (long i = sensor.x20min - minX; i <= sensor.x20max - minX; i++) {
                    magicLine[(int)i] = true;
                }
            }
        }
        for (NodeDay15 beacon : beacons) {
            if (beacon.y == LINE_CALC)
                magicLine[(int)beacon.x] = false;
        }
        int result = 0;
        for (int i = 0; i < magicLine.length; i++) {
            if (magicLine[i]) result++;
        }




        return "" + result;
    }

    @Override
    public String solvePuzzleB() {
        List<NodeDay15> sensors = new ArrayList<>();
        List<NodeDay15> beacons = new ArrayList<>();
        try (BufferedReader lines = Utils.getBufferInput("15")) {
            for (String line; (line = lines.readLine()) != null; ) {
                NodeDay15 sensor;
                NodeDay15 beacon;
                String sensorString = line.split("at ")[1].split(":")[0];
                String beaconString = line.split("at ")[2];

                sensor = new NodeDay15(Long.parseLong(sensorString.split(", ")[0].substring(2)), Long.parseLong(sensorString.split(", ")[1].substring(2)));
                sensors.add(sensor);
                beacon = new NodeDay15(Long.parseLong(beaconString.split(", ")[0].substring(2)), Long.parseLong(beaconString.split(", ")[1].substring(2)));
                // check if beacon is known
                boolean toAdd = true;
                for (NodeDay15 known : beacons) {
                    if (beacon.x == known.x && beacon.y == known.y) {
                        beacon = known;
                        toAdd = false;
                        break;
                    }
                }
                if (toAdd) {
                    beacons.add(beacon);
                }
                beacon.addSensor(sensor);
                sensor.setClosest(beacon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (NodeDay15 sensor : sensors) {
            sensor.calcDist();
        }
        boolean found = false;
        long result;
        int sensorNumber = -1;
        long xToCheck = -1;
        long yToCheck = -1;
        for (NodeDay15 sensor : sensors) {
            sensorNumber++;
            //System.out.println("Sensor : " + sensorNumber);

            for (long k = 0; k <= sensor.dist+1; k++) {
                // up left
                yToCheck = sensor.y - (sensor.dist+1) + k;
                xToCheck = sensor.x - k;
                if (xToCheck >= MIN_DISTRESS && xToCheck <= MAX_DISTRESS && yToCheck >= MIN_DISTRESS && yToCheck <= MAX_DISTRESS) {
                    found = true;
                    for (NodeDay15 sensor2 : sensors) {
                        if (Math.abs(xToCheck - sensor2.x) + Math.abs(yToCheck - sensor2.y) <= sensor2.dist) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        result = (4000000 * xToCheck) + yToCheck;
                        return "" + result;
                    }
                }


                // up right
                yToCheck = sensor.y - (sensor.dist+1) + k;
                xToCheck = sensor.x + k;
                if (xToCheck >= MIN_DISTRESS && xToCheck <= MAX_DISTRESS && yToCheck >= MIN_DISTRESS && yToCheck <= MAX_DISTRESS) {
                    found = true;
                    for (NodeDay15 sensor2 : sensors) {
                        if (Math.abs(xToCheck - sensor2.x) + Math.abs(yToCheck - sensor2.y) <= sensor2.dist) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        result = (4000000 * xToCheck) + yToCheck;
                        return "" + result;
                    }
                }

                // down left
                yToCheck = sensor.y + (sensor.dist+1) - k;
                xToCheck = sensor.x - k;
                if (xToCheck >= MIN_DISTRESS && xToCheck <= MAX_DISTRESS && yToCheck >= MIN_DISTRESS && yToCheck <= MAX_DISTRESS) {
                    found = true;
                    for (NodeDay15 sensor2 : sensors) {
                        if (Math.abs(xToCheck - sensor2.x) + Math.abs(yToCheck - sensor2.y) <= sensor2.dist) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        result = (4000000 * xToCheck) + yToCheck;
                        return "" + result;
                    }
                }

                // down right
                yToCheck = sensor.y + (sensor.dist+1) - k;
                xToCheck = sensor.x + k;
                if (xToCheck >= MIN_DISTRESS && xToCheck <= MAX_DISTRESS && yToCheck >= MIN_DISTRESS && yToCheck <= MAX_DISTRESS) {
                    found = true;
                    for (NodeDay15 sensor2 : sensors) {
                        if (Math.abs(xToCheck - sensor2.x) + Math.abs(yToCheck - sensor2.y) <= sensor2.dist) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        result = (4000000 * xToCheck) + yToCheck;
                        return "" + result;
                    }
                }
            }
        }



        return "";
    }
}
