package actormatching;

/**
 * @author Hendrik Werner // s4549775
 * @author Constantin Blach // s4329872
 */
public class Main {
    public static void main(String[] args) {
        AdjacencyMatrix graph = new AdjacencyMatrix(System.in);
        System.out.print(new Game(graph).play());
    }
}
