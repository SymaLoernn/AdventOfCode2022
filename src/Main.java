import solvers.Solver;
import solvers.SolverDay1;

public class Main {


    public static void main(String[] args) {
        Solver solver = new SolverDay1();
        System.out.println(solver.getIntroString());
        System.out.println("Puzzle A : " + solver.solvePuzzleA());
        System.out.println("Puzzle B : " + solver.solvePuzzleB());

    }
}
