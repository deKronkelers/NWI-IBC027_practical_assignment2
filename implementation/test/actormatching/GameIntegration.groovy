package actormatching

import groovy.transform.TypeChecked

/**
 * @author Hendrik Werner // s4549775
 */
@TypeChecked
class GameIntegration extends GroovyTestCase {
    void testPlay() {
        Map<File, String> testCases =
                TestUtils.loadTestCases "../samples/" as File
        for (Map.Entry<File, String> testCase : testCases.entrySet()) {
            InputStream input = new FileInputStream(testCase.key)
            Game game = new Game(new AdjacencyMatrix(input))
            assert game.play() == testCase.value
        }
    }
}
