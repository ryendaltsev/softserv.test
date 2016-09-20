package my.test.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ren on 20.09.16.
 */
public class ArrayWrapperTest {


    @Test
    public void hasPivotIndexes() throws Exception {
        int[] source1 = {5, 9, 7, 17, 6, 5, 4, 6};
        doHasPivotIndexTest(source1);
        int[] source2 = {1,2,3,4,5,6,7,8, 29, -1};
        doHasPivotIndexTest(source2);
    }

    private void doHasPivotIndexTest(int[] source) {
        ArrayWrapper wrapper = new ArrayWrapper(source);
        assertTrue(wrapper.hasPivotIndexes());
    }

    @Test
    public void isPivotIndex() throws Exception {
        int[] source = {5, 9, 7, 17, 6, 5, 4, 6};
        ArrayWrapper wrapper = new ArrayWrapper(source);
        assertTrue(wrapper.isPivotIndex(3));
        assertFalse(wrapper.isPivotIndex(4));
    }

}