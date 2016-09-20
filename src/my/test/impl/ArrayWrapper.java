package my.test.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * Created by ren on 20.09.16.
 */
public class ArrayWrapper {

    private int[] source;
    private Collection<Integer> pivots = null;

    public ArrayWrapper(int[] source) {
        this.source = source;
    }

    public boolean hasPivotIndexes() {
        if (this.pivots==null) {
            searchPivotIndexes();
        }
        return !this.pivots.isEmpty();
    }

    private void searchPivotIndexes() {
        this.pivots = new HashSet<>();
        LinkedList<Callable<Integer>> variants = new LinkedList<>();
        for (int i = 1; i < this.source.length - 1; i++) {
            int pivot = i;
            variants.add( () -> isPivotIndex(pivot) ? pivot : null );
        }
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            for (Future<Integer> result: service.invokeAll(variants)) {
                if (result.get() != null) {
                    pivots.add(result.get());
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            new RuntimeException("The process was not finished. Reason:" + e.getMessage() + "!");
        }
    }

    protected boolean isPivotIndex(int pivotIndex) {
        int leftSum = Arrays.stream(Arrays.copyOfRange(this.source, 0, pivotIndex)).sum();
        int rightSum = Arrays.stream(Arrays.copyOfRange(this.source, pivotIndex + 1, this.source.length)).sum();
        return leftSum == rightSum;
    }
}
