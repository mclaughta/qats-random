import grails.test.AbstractCliTestCase

class GenerateRandomSsnsTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGenerateRandomSsns() {

        execute(["generate-random-ssns"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}
