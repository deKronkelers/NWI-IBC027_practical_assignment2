package actormatching

import groovy.transform.CompileStatic

/**
 * @author Hendrik Werner // s4549775
 */
@CompileStatic
class AdjacencyMatrixIntegration extends GroovyTestCase {
    void testReadingAndPrinting() {
        InputStream stream = new FileInputStream("../samples/a1.in")
        assert new AdjacencyMatrix(stream).toString() == "[1, 1]\n[1, 0]"
        stream = new FileInputStream("../samples/a2.in")
        assert new AdjacencyMatrix(stream).toString() == "[1, 1]\n[0, 0]"
    }

    void testReadingPerformance() {
        Map<File, String> testCases =
                TestUtils.loadTestCases "../samples/" as File
        for (File input in testCases.keySet()) {
            long start = System.currentTimeMillis()
            new AdjacencyMatrix(new FileInputStream(input))
            assert System.currentTimeMillis() - start < 500
        }
    }
}
