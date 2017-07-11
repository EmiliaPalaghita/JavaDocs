package com.timnet.examples;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by Emilia.Palaghita on 10-Jul-17.
 */
public class AnotherAppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AnotherAppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
}
