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
    }

    public Player makeMove(Player player, int[] candidates) {
        if (candidates.length == 0) {
            return Player.getOpponentOf(player);
        }
    }

    private enum Player {
        Veronique,
        Mark;

        private static Player getOpponentOf(Player player) {
            switch (player) {
                case Mark: return Veronique;
                default: return Mark;
            }
        }
    }
}
