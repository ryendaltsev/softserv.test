package my.test.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by ren on 20.09.16.
 */
public class RWNodeTest {


    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    private PrintStream stdOut;

    private String[] args = {"One", "Two", "Free", "Four"};

    @Before
    public void setUp() throws Exception {
        stdOut = System.out;
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(stdOut);
    }

    /**
     * The tests performs test of print RWNode elements in the forward direction
     * Elements One, Two, Tree, Four have to be printed in the same direction
     *
     * @throws Exception
     */
    @Test
    public void printList() throws Exception {
        String[] testSource = this.args;
        String expectedValue = getExpectedString(testSource);
        RWNode node = prepareStructureForTest(testSource);
        RWNode.printList(node);

        assertEquals(expectedValue, testOut.toString());
    }

    /**
     * The tests performs test of print RWNode elements in the towards direction
     * Elements One, Two, Tree, Four have to be printed in reversed way
     *  Four Tree Two One
     *
     * @throws Exception
     */
    @Test
    public void printListReverse() throws Exception {
        String[] testSource = args;
        String expectedValue = getExpectedString(this.reverse(testSource));
        RWNode node = prepareStructureForTest(testSource);
        RWNode.printListReverse(node);

        assertEquals(expectedValue, testOut.toString());
    }

    /**
     * Returns new source of string, the elements are in reverse order
     *
     * @param source
     * @return
     */
    private static String[] reverse(String[] source) {
        String[] result = source.clone();
        Collections.reverse(Arrays.asList(result));
        return result;
    }

    /**
     * Returns representation of array like it will done in println method
     * @param source
     * @return
     */
    private String getExpectedString(String[] source) {
        StringBuilder expectedValue = new StringBuilder();
        for (String name: source  ) {
            expectedValue.append(name);
            expectedValue.append(System.lineSeparator());
        }
        return expectedValue.toString();
    }

    /**
     * Prepares structure of RWNode elements what defined by source (array of string)
     * First element of array will be returned as result.
     *
     * @param source
     * @return
     */
    private RWNode prepareStructureForTest(String[] source) {
        class RWNodeImpl implements RWNode {

            private final String name;
            private final RWNode next;

            public RWNodeImpl(String name, RWNode next) {
                this.name = name;
                this.next = next;
            }

            @Override
            public String getName() {
                return this.name;
            }

            @Override
            public RWNode getNext() {
                return this.next;
            }
        }
        RWNode item = null;
        for (String name: this.reverse(source)  ) {
            item = new RWNodeImpl(name, item);
        }
        return item;
    }

}