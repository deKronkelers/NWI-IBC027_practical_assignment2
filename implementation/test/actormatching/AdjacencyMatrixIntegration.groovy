package actormatching

import groovy.transform.CompileStatic

import java.nio.charset.StandardCharsets

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
        Map<String, String> testCases =
                TestUtils.loadTestCases "../samples/" as File
        for (String input in testCases.keySet()) {
            long start = System.currentTimeMillis()
            InputStream stream = new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.US_ASCII)
            )
            new AdjacencyMatrix(stream)
            assert System.currentTimeMillis() - start < 500
        }
    }
}
