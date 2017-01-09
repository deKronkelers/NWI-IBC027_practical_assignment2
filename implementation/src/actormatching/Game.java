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
        for (int candidate : candidates) {
            int[] costars = player.getCostars(graph, candidate);
            player.ignore(graph, candidate);
            if (makeMove(Player.getOpponentOf(player), costars) == player) {
                player.unignore(graph, candidate);
                return player;
            }
            player.unignore(graph, candidate);
        }
        return Player.getOpponentOf(player);
    }

    private enum Player {
        Veronique {
            @Override
            int[] getCostars(AdjacencyMatrix graph, int actor) {
                return graph.getAdjacentActors(actor);
            }

            @Override
            void ignore(AdjacencyMatrix graph, int actor) {
                graph.ignoreActress(actor);
            }

            @Override
            void unignore(AdjacencyMatrix graph, int actor) {
                graph.unignoreActress(actor);
            }
        },
        Mark {
            @Override
            int[] getCostars(AdjacencyMatrix graph, int actor) {
                return graph.getAdjacentActresses(actor);
            }

            @Override
            void ignore(AdjacencyMatrix graph, int actor) {
                graph.ignoreActor(actor);
            }

            @Override
            void unignore(AdjacencyMatrix graph, int actor) {
                graph.unignoreActor(actor);
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

        abstract void ignore(AdjacencyMatrix graph, int actor);

        abstract void unignore(AdjacencyMatrix graph, int actor);
    }
}
