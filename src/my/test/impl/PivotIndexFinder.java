package my.test.impl;

import java.util.*;
import java.util.concurrent.*;

/**
 * Implementation of functionality what was requested (according point 3)
 * The class answers the question is array contains a pivot indexes or not.
 * Usage of functionality:
 *     - Create instance of finder and put array as argument into constructor.
 *     - Call hasPivotIndexes method when you need to know is the array contains pivot indexes or not
 *
 * Created by ren on 20.09.16.
 */
public class PivotIndexFinder {

    private int[] source;
    private Collection<Integer> pivots = null;

    public PivotIndexFinder(int[] source) {
        this.source = source;
        if (source==null) {
            throw new IllegalArgumentException("Null as argument is not allowed in constructor!");
        }
        if (source.length<3) {
            throw new IllegalArgumentException("Array has to have more than 2 elements!");
        }
    }

    public boolean hasPivotIndexes() {
        if (this.pivots==null) {
            findPivotIndexes();
        }
        return !this.pivots.isEmpty();
    }

    private void findPivotIndexes() {
        this.pivots = new HashSet<>();
        Set<Callable<Integer>> variants = new HashSet<>();
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
        finally {
            service.shutdown();
        }
    }

    protected boolean isPivotIndex(int pivotIndex) {
        int leftSum = Arrays.stream(Arrays.copyOfRange(this.source, 0, pivotIndex)).sum();
        int rightSum = Arrays.stream(Arrays.copyOfRange(this.source, pivotIndex + 1, this.source.length)).sum();
        System.out.println("Test index:" + pivotIndex);
        return leftSum == rightSum;
    }
}
