package model;

public class CoordDay12 {

    public int x;
    public int y;
    public int elevation;
    public int score = Integer.MAX_VALUE;


    public boolean equals(CoordDay12 coord) {
        return coord.x == this.x && coord.y == this.y;
    }
}
