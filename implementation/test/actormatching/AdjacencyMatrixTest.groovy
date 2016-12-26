package actormatching

/**
 * @author Hendrik Werner // s4549775
 */
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
}
