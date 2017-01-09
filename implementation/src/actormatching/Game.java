package actormatching;

/**
 * @author Hendrik Werner // s4549775
 * @author Constantin Blach // s4329872
 */
public class Game {
    private final AdjacencyMatrix graph;

    public Game(AdjacencyMatrix graph) {
        this.graph = graph;
    }

    public String play() {
        return makeMove(Player.Veronique, graph.getActressIndices()).name();
    }

    public Player makeMove(Player player, int[] candidates) {
        if (candidates.length == 0) {
            return Player.getOpponentOf(player);
        }
    }

    private enum Player {
        Veronique {
            @Override
            int[] getCostars(AdjacencyMatrix graph, int actor) {
                return graph.getAdjacentActors(actor);
            }
        },
        Mark {
            @Override
            int[] getCostars(AdjacencyMatrix graph, int actor) {
                return graph.getAdjacentActresses(actor);
            }
        };

        /**
         * @param player The player
         * @return The opponent of the player
         */
        private static Player getOpponentOf(Player player) {
            switch (player) {
                case Mark: return Veronique;
                default: return Mark;
            }
        }

        /**
         * @param graph A graph of costars
         * @param actor The index of an actor (or actress)
         * @return The costars of the actor (or actress)
         */
        abstract int[] getCostars(AdjacencyMatrix graph, int actor);
    }
}
