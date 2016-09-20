package my.test.impl;

/**
 * Created by ren on 20.09.16.
 */
public interface RWNode {
    public String getName();
    public RWNode getNext();

    /**
     * Static method for printing out nodes names
     *
     * @param node
     */
    public static void printList(RWNode node){
        System.out.println(node.getName());
        if (node.getNext()!=null) {
            printList(node.getNext());
        }
    }

    /**
     * Static method for printing out nodes names in reversed direction
     *
     * @param node
     */
    public static void printListReverse(RWNode node){
        if (node.getNext()!=null) {
            printListReverse(node.getNext());
        }
        System.out.println(node.getName());
    }
}
