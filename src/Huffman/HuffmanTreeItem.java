import java.math.*;

/**
 * \brief Represents an item in a Huffman tree
 */
public class HuffmanTreeItem<T> implements Comparable<HuffmanTreeItem<T>> {
    private T value;          ///< Value of item
    private BigInteger count; ///< Quantity of item

    private HuffmanTreeItem<T> leftChild;  ///< Child on the left side, or null
    private HuffmanTreeItem<T> rightChild; ///< Child on the right side, or null

    /**
     * \brief Create new HuffmanTreeItem instance with the given value and count
     * 
     * @param value Value
     * @param count Count
     */
    public HuffmanTreeItem(T value, BigInteger count) {
        if(count == null) throw new IllegalArgumentException("HuffmanTreeItem.addData(T,BigInteger): count is null!");

        this.value = value;
        this.count = count;

        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * \brief Create new HuffmanTreeItem instance with the given value and a count of one
     * 
     * @param value Value
     */
    public HuffmanTreeItem(T value) {
        this(value, BigInteger.ONE);
    }

    /**
     * \brief Create new HuffmanTreeItem instance with a value of null and a count of one
     */
    public HuffmanTreeItem() { this(null); }

    /**
     * \brief Increment the count by one.
     */
    public void incCount() { this.count = this.count.add(BigInteger.ONE); }

    /**
     * \brief Returns the current count
     * 
     * @return Current count
     */
    public BigInteger getCount() { return this.count; }

    /**
     * \brief Returns the item's value
     * 
     * @return The item's value
     */
    public T          getValue() { return this.value; }

    /**
     * \brief Returns the left child
     * 
     * @return The left child
     */
    public HuffmanTreeItem<T> getLeftChild()  { return this.leftChild; }

    /**
     * \brief Returns the right child
     * 
     * @return The right child
     */
    public HuffmanTreeItem<T> getRightChild() { return this.rightChild; }

    /**
     * \brief Sets the count based on the count of it's children
     */
    public void setCountFromChildren() {
        this.count = BigInteger.ZERO;
        if(this.leftChild  != null) this.count = this.count.add(this.leftChild.getCount());
        if(this.rightChild != null) this.count = this.count.add(this.rightChild.getCount());
    }

    /**
     * \brief Sets the left child
     * 
     * @param treeItem Value of left child
     */
    public void setLeftChild(HuffmanTreeItem<T> treeItem) {
        this.leftChild = treeItem;
    }
    
    /**
     * \brief Sets the right child
     * 
     * @param treeItem Value of right child
     */
    public void setRightChild(HuffmanTreeItem<T> treeItem) {
        this.rightChild = treeItem;
    }

    /**
     * \brief Compares count to another HuffmanTreeItem
     * 
     * @param obj HuffmanTreeItem object to compare to
     * @return BigInteger comparison of this count and obj's count
     */
    public int compareTo(HuffmanTreeItem<T> obj) { return this.count.compareTo(obj.getCount()); }
}