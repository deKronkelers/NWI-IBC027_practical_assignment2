package actormatching

import groovy.transform.CompileStatic

/**
 * @author Hendrik Werner // s4549775
 */
@CompileStatic
class TestUtils {
    static Map<File, String> loadTestCases(File directory) {
        Map<File, String> testCases = [] as HashMap
        File[] inputs = directory.listFiles({
            File dir, String name -> name.endsWith ".in"
        } as FilenameFilter)
        for (File input in inputs) {
            String testOutput = (input.path.replace(".in", ".out") as File).text
            testCases.put input, testOutput.trim()
        }
        testCases
    }
}
