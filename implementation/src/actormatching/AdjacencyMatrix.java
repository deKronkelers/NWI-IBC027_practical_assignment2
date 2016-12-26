package actormatching;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Hendrik Werner // s4549775
 * @author Constantin Blach // s4329872
 */
public class AdjacencyMatrix {
    /**
     * Read some number of actors from the given scanner and save them in some
     * array list.
     *
     * @param scanner The scanner to read from
     * @param nr_actors The number of actors to read
     * @param actors The array list used to save actors
     */
    private static void readActors(
            Scanner scanner
            , int nr_actors
            , ArrayList<String> actors
    ) {
        for (int i = 0; i < nr_actors; i++) {
            actors.add(scanner.nextLine());
        }
    }

    private final int[][] matrix;

    public AdjacencyMatrix(int actors) {
        matrix = new int[actors][actors];
    }

    public AdjacencyMatrix(InputStream in) {
        final Scanner input = new Scanner(in);
        final int nr_actors = input.nextInt();
        final int nr_movies = input.nextInt();
        input.nextLine(); // skip rest of line
        final ArrayList<String> actresses = new ArrayList<>(nr_actors);
        final ArrayList<String> actors = new ArrayList<>(nr_actors);
        readActors(input, nr_actors, actresses);
        readActors(input, nr_actors, actors);
        matrix = new int[nr_actors][nr_actors];
        readMovies(this, input, nr_movies, actresses, actors);
    }

    public void setAdjacent(int actress, int actor) {
        matrix[actress][actor] = 1;
    }

    public boolean getAdjacent(int actress, int actor) {
        return matrix[actress][actor] > 0;
    }
}
