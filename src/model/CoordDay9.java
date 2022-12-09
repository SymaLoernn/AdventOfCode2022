package model;

public class CoordDay9 {
    public int x = 0;
    public int y = 0;

    public boolean follow(CoordDay9 head) {
        int diffX = Math.abs(head.x - this.x);
        int diffY = Math.abs(head.y - this.y);
        if (diffX >= 2 || diffY >= 2) {
            if (head.x > this.x) {
                this.x++;
            } else if (head.x < this.x) {
                this.x--;
            }
            if (head.y > this.y) {
                this.y++;
            } else if (head.y < this.y) {
                this.y--;
            }
            return true;
        }
        return false;
    }
}
