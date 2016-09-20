package my.test.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ren on 20.09.16.
 */
public class PivotIndexFinderTest {


    private int[] sourceIdx3 =  {5, 9, 7, 17, 6, 5, 4, 6};
    private int[] sourceIdx7 = {1, 2, 3, 4, 5, 6, 7, 8, 29, -1};
    private int[] sourceIdx0 = {-1, 2, 3, 4, 5, 6, 7, 8};

    @Test
    public void hasPivotIndexes() throws Exception {
        doHasPivotIndexTest(this.sourceIdx3, true);
        doHasPivotIndexTest(this.sourceIdx7, true);
        doHasPivotIndexTest(this.sourceIdx0, false);
    }

    private void doHasPivotIndexTest(int[] source, boolean result) {
        PivotIndexFinder wrapper = new PivotIndexFinder(source);
        assertEquals(result, wrapper.hasPivotIndexes());
    }

    @Test
    public void isPivotIndex() throws Exception {
        PivotIndexFinder wrapper = new PivotIndexFinder(sourceIdx7);
        for (int idx = 1; idx < sourceIdx7.length - 1; idx++) {
            assertEquals(idx==7, wrapper.isPivotIndex(idx));
        }
    }

}