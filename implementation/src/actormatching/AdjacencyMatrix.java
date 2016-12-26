package actormatching;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    /**
     * Given a list of actresses and actors set the females adjacent to all the
     * males.
     *
     * @param matrix The adjacency matrix representing the actor graph
     * @param actresses The list of actresses
     * @param actors The list of actors
     * @param female The females that are adjacent to the males
     * @param male The males that are adjacent to the females
     */
    private static void fill(
            AdjacencyMatrix matrix
            , ArrayList<String> actresses
            , ArrayList<String> actors
            , ArrayList<String> female
            , ArrayList<String> male
    ) {
        for (String woman : female) {
            for (String man : male) {
                matrix.setAdjacent(
                        actresses.indexOf(woman)
                        , actors.indexOf(man)
                );
            }
        }
    }

    /**
     * Read the cast of a given number of movies and set females adjacent to
     * males with whom they starred in the same movie.
     *
     * @param matrix The adjacency matrix representing the actor graph
     * @param scanner The scanner to read from
     * @param nr_movies The number of movies to read
     * @param actresses The list of actresses
     * @param actors The list of actors
     */
    private static void readMovies(
            AdjacencyMatrix matrix
            , Scanner scanner
            , int nr_movies
            , ArrayList<String> actresses
            , ArrayList<String> actors
    ) {
        for (int i = 0; i < nr_movies; i++) {
            scanner.nextLine(); // skip title
            final int cast_size = scanner.nextInt();
            scanner.nextLine(); // skip rest of line
            ArrayList<String> female = new ArrayList<>();
            ArrayList<String> male = new ArrayList<>();
            for (int k = 0; k < cast_size; k++) {
                String actor = scanner.nextLine();
                if (actors.contains(actor)) {
                    male.add(actor);
                } else {
                    female.add(actor);
                }
            }
            fill(matrix, actresses, actors, female, male);
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

    @Override
    public String toString() {
        return Arrays.stream(matrix)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }
}
