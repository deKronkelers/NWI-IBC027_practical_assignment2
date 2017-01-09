package actormatching;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Hendrik Werner // s4549775
 * @author Constantin Blach // s4329872
 */
public class AdjacencyMatrix {
    /**
     * Read some number of actors from the given scanner and save their indices
     * and sex in maps.
     *
     * @param scanner The scanner to read from
     * @param nr_actors The number of actors to read
     * @param indices The indices of the actors
     * @param isMale The sex of the actors
     * @param male The sex of the actors
     */
    private static void readActors(
            Scanner scanner
            , int nr_actors
            , Map<String, Integer> indices
            , Map<String, Boolean> isMale
            , boolean male
    ) {
        for (int i = 0; i < nr_actors; i++) {
            String actor = scanner.nextLine();
            indices.put(actor, i);
            isMale.put(actor, male);
        }
    }

    /**
     * Given a list of actresses and actors set the females adjacent to all the
     * males.
     *
     * @param matrix The adjacency matrix representing the actor graph
     * @param indices The indices of the actors
     * @param female The females that are adjacent to the males
     * @param male The males that are adjacent to the females
     */
    private static void fill(
            AdjacencyMatrix matrix
            , Map<String, Integer> indices
            , List<String> female
            , List<String> male
    ) {
        for (String woman : female) {
            for (String man : male) {
                matrix.setAdjacent(
                        indices.get(woman)
                        , indices.get(man)
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
     * @param indices The indices of the actors
     * @param isMale The sex of the actors
     */
    private static void readMovies(
            AdjacencyMatrix matrix
            , Scanner scanner
            , int nr_movies
            , Map<String, Integer> indices
            , Map<String, Boolean> isMale
    ) {
        for (int i = 0; i < nr_movies; i++) {
            scanner.nextLine(); // skip title
            final int cast_size = scanner.nextInt();
            scanner.nextLine(); // skip rest of line
            final List<String> female = new ArrayList<>();
            final List<String> male = new ArrayList<>();
            for (int k = 0; k < cast_size; k++) {
                final String actor = scanner.nextLine();
                if (isMale.get(actor)) {
                    male.add(actor);
                } else {
                    female.add(actor);
                }
            }
            fill(matrix, indices, female, male);
        }
    }

    private final int[][] matrix;
    private final boolean[] ignoredActors;
    private final boolean[] ignoredActresses;

    public AdjacencyMatrix(int actors) {
        matrix = new int[actors][actors];
        ignoredActors = new boolean[actors];
        ignoredActresses = new boolean[actors];
    }

    public AdjacencyMatrix(InputStream in) {
        final Scanner input = new Scanner(in);
        final int nr_actors = input.nextInt();
        final int nr_movies = input.nextInt();
        input.nextLine(); // skip rest of line
        final Map<String, Integer> indices = new HashMap<>(nr_actors * 2);
        final Map<String, Boolean> isMale = new HashMap<>(nr_actors * 2);
        readActors(input, nr_actors, indices, isMale, false);
        readActors(input, nr_actors, indices, isMale, true);
        matrix = new int[nr_actors][nr_actors];
        ignoredActors = new boolean[nr_actors];
        ignoredActresses = new boolean[nr_actors];
        readMovies(this, input, nr_movies, indices, isMale);
    }

    public void setAdjacent(int actress, int actor) {
        matrix[actress][actor] = 1;
    }

    public boolean getAdjacent(int actress, int actor) {
        return matrix[actress][actor] > 0;
    }

    public int[] getActressIndices() {
        return IntStream.range(0, matrix.length)
                .filter(i -> !ignoredActresses[i])
                .toArray();
    }

    public void ignoreActress(int actress) {
        ignoredActresses[actress] = true;
    }

    public void unignoreActress(int actress) {
        ignoredActresses[actress] = false;
    }

    public void ignoreActor(int actor) {
        ignoredActors[actor] = true;
    }

    public void unignoreActor(int actor) {
        ignoredActors[actor] = false;
    }

    public int[] getAdjacentActresses(int actor) {
        return IntStream.range(0, matrix.length)
                .filter(i -> !ignoredActresses[i])
                .filter(i -> getAdjacent(i, actor))
                .toArray();
    }

    public int[] getAdjacentActors(int actress) {
        return IntStream.range(0, matrix[0].length)
                .filter(i -> !ignoredActors[i])
                .filter(i -> getAdjacent(actress, i))
                .toArray();
    }

    @Override
    public String toString() {
        return Arrays.stream(matrix)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }
}
