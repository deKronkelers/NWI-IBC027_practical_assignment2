package actormatching;

/**
 * @author Hendrik Werner // s4549775
 * @author Constantin Blach // s4329872
 */
public class AdjacencyMatrix {
    private final int[][] matrix;

    public AdjacencyMatrix(int actors) {
        matrix = new int[actors][actors];
    }

    public void setAdjacent(int actress, int actor) {
        matrix[actress][actor]++;
    }

    public boolean getAdjacent(int actress, int actor) {
        return matrix[actress][actor] > 0;
    }
}
