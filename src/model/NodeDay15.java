package model;

import solvers.SolverDay15;

import java.util.ArrayList;
import java.util.List;

public class NodeDay15 {
    public long x;
    public long y;
    public NodeDay15 closest;
    public List<NodeDay15> sensors = new ArrayList<>();
    public long dist;

    public long x20min;
    public long x20max;
    public boolean intersect20 = false;

    public NodeDay15(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public void setClosest(NodeDay15 closest) {
        this.closest = closest;
    }

    public void addSensor(NodeDay15 sensor) {
        this.sensors.add(sensor);
    }

    public void checkCoord20() {
        int ycheck = SolverDay15.LINE_CALC;
        long distToClosest = Math.abs(this.x - closest.x) + Math.abs(this.y - closest.y);
        long width = distToClosest - Math.abs(ycheck - this.y);
        if (width >= 0) {
            this.intersect20 = true;
            this.x20min = this.x - width;
            this.x20max = this.x + width;
        }
    }

    public void calcDist() {
        this.dist = Math.abs(this.x - closest.x) + Math.abs(this.y - closest.y);
    }

}
