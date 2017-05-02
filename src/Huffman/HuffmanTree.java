import java.util.*;
import java.math.*;

/**
 * Represents and creates Huffman code trees
 */
public class HuffmanTree<T> {
    private ArrayList<HuffmanTreeItem<T>> treeItems; ///< List of items currently in the tree

    /**
     * Create a new HuffmanTree instance
     */
    public HuffmanTree() {
        this.treeItems = new ArrayList<HuffmanTreeItem<T>>();
    }

    /**
     * Add value to the tree, or increment it's count
     * 
     * @param val Value to add
     */
    public void addValue(T val) {
        if(val == null) throw new IllegalArgumentException("HuffmanTree.addValue(T): val is null!");

        for(int i = 0; i < this.treeItems.size(); i++) {
            if(this.treeItems.get(i).getValue().equals(val)) { 
                this.treeItems.get(i).incCount();
                return;
            }
        }

        this.treeItems.add(new HuffmanTreeItem<T>(val));
    }

    /**
     * Returns the average count for every tree item
     */
    private BigInteger getAverageCount() {
        BigInteger sum = BigInteger.ZERO;
        BigInteger nItems = BigInteger.ZERO;

        for(int i = 0; i < this.treeItems.size(); i++) {
            sum = sum.add(this.treeItems.get(i).getCount());
            nItems = nItems.add(BigInteger.ONE);
        }

        return sum.divide(nItems);
    }

    /**
     * Generates a tree from the given tree items
     */
    public HuffmanTreeItem<T> generateTree() {
        ArrayList<HuffmanTreeItem<T>> tree;;

        while(this.treeItems.size() > 1) {
            tree = new ArrayList<HuffmanTreeItem<T>>();

            Collections.sort(this.treeItems);

            BigInteger avg = this.getAverageCount();

            for(int i = 0; i < this.treeItems.size(); i++) {
                // Check if value is greater than average, so that they can be pushed higher up the list
                if(this.treeItems.get(i).getCount().compareTo(avg) < 0) {
                    HuffmanTreeItem<T> item = new HuffmanTreeItem<T>();

                    item.setLeftChild(this.treeItems.get(i));
                    if(i < this.treeItems.size() - 1) {
                        item.setRightChild(this.treeItems.get(i + 1));
                        i++;
                    }

                    item.setCountFromChildren();

                    tree.add(item);
                } else {
                    tree.add(this.treeItems.get(i));
                }
            }

            this.treeItems = new ArrayList<HuffmanTreeItem<T>>();
            for(int j = 0; j < tree.size(); j++) this.treeItems.add(tree.get(j));
        }

        return this.treeItems.get(0);
    }
}