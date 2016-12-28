package actormatching

/**
 * @author Hendrik Werner // s4549775
 */
class AdjacencyMatrixIntegration extends GroovyTestCase {
    void testReadingAndPrinting() {
        InputStream stream = new FileInputStream("../samples/a1.in")
        assert new AdjacencyMatrix(stream).toString() == "[1, 1]\n[1, 0]"
        stream = new FileInputStream("../samples/a2.in")
        assert new AdjacencyMatrix(stream).toString() == "[1, 1]\n[0, 0]"
    }
}
