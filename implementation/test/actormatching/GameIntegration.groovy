package actormatching

import groovy.transform.TypeChecked

import java.nio.charset.StandardCharsets

/**
 * @author Hendrik Werner // s4549775
 */
@TypeChecked
class GameIntegration extends GroovyTestCase {
    void testPlay() {
        Map<String, String> testCases =
                TestUtils.loadTestCases "../samples/" as File
        for (Map.Entry<String, String> testCase : testCases.entrySet()) {
            InputStream stream = new ByteArrayInputStream(
                    testCase.getKey().getBytes(StandardCharsets.US_ASCII)
            )
            Game game = new Game(new AdjacencyMatrix(stream))
            assert game.play() == testCase.getValue()
        }
    }
}
