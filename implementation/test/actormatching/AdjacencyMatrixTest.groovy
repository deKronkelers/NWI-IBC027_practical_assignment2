package actormatching

import groovy.transform.TypeChecked

/**
 * @author Hendrik Werner // s4549775
 */
@TypeChecked
class AdjacencyMatrixTest extends GroovyTestCase {
    void testSetAdjacent() {
        AdjacencyMatrix graph = new AdjacencyMatrix(1)
        assert graph.matrix[0][0] == 0
        graph.setAdjacent(0, 0)
        assert graph.matrix[0][0] == 1
        // test if parallel edges are disallowed
        graph.setAdjacent(0, 0)
        assert graph.matrix[0][0] == 1
    }

    void testGetAdjacent() {
        AdjacencyMatrix graph = new AdjacencyMatrix(1)
        assert !graph.getAdjacent(0, 0)
        graph.matrix[0][0] = 1
        assert graph.getAdjacent(0, 0)
        // test if parallel edges are still counted
        graph.matrix[0][0] = 7
        assert graph.getAdjacent(0, 0)
    }

    void testToString() {
        assert new AdjacencyMatrix(0).toString() == ""
        AdjacencyMatrix graph = new AdjacencyMatrix(2)
        assert graph.toString() == "[0, 0]\n[0, 0]"
        graph.matrix[0][0] = 1
        graph.matrix[1][1] = 1
        assert graph.toString() == "[1, 0]\n[0, 1]"
    }

    void testGetAdjacentActresses() {
        AdjacencyMatrix graph = new AdjacencyMatrix(2)
        for (int i = 0; i < 2; i++) {
            assert graph.getAdjacentActresses(i) == [] as int[]
        }
        graph.matrix[0][0] = 1
        assert graph.getAdjacentActresses(0) == [0] as int[]
        graph.matrix[1][0] = 1
        assert graph.getAdjacentActresses(0) == [0, 1] as int[]
    }

    void testGetAdjacentActors() {
        AdjacencyMatrix graph = new AdjacencyMatrix(2)
        for (int i = 0; i < 2; i++) {
            assert graph.getAdjacentActors(i) == [] as int[]
        }
        graph.matrix[0][0] = 1
        assert graph.getAdjacentActors(0) == [0] as int[]
        graph.matrix[0][1] = 1
        assert graph.getAdjacentActors(0) == [0, 1] as int[]
    }
}
