import solvers.*;

public class Main {


    public static void main(String[] args) {
        Solver solver = new SolverDay14();
        System.out.println(solver.getIntroString());
        System.out.println("Puzzle A : " + solver.solvePuzzleA());
        System.out.println("Puzzle B : " + solver.solvePuzzleB());

    }
}
