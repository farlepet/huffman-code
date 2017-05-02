import java.util.*;

/**
 * Represents a Huffman code map generator
 */
public class Huffman<T> {

    HuffmanTree<T> tree;         ///< Tree class, builds code tree
    HuffmanTreeItem<T> treeRoot; ///< Completed and structured tree

    Map<T, String> codeMap; ///< Code map, generated by the tree

    /**
     * Creates a new Huffman instance, and initializes the tree.
     */
    public Huffman() {
        this.tree = new HuffmanTree<T>();
    }

    /**
     * Add data to the Huffman map generator
     * 
     * @param data Array of data to add
     */
    public void addData(T[] data) {
        if(data == null) throw new IllegalArgumentException("Huffman.addData(T[]): data is null!");

        for(int i = 0; i < data.length; i++) {
            this.tree.addValue(data[i]);
        }
    }

    /**
     * Add data to the Huffman map generator
     * 
     * @param data Data point to add
     */
    public void addData(T data) {
        if(data == null) throw new IllegalArgumentException("Huffman.addData(T): data is null!");

        this.tree.addValue(data);
    }

    /**
     * Generate a tree of the codes.
     */
    public void generateTree() {
        this.treeRoot = tree.generateTree();
    }

    /**
     * Generates and returns a code map, mapping every item to a binary string.
     * 
     * @return Code map.
     */
    public Map<T, String> generateMap() {
        if(this.treeRoot == null) throw new IllegalArgumentException("Huffman.generateMap(): this.treeRoot is null! Did you call Huffman.generateTree()?");

        this.codeMap = new HashMap<T, String>();
        this.generateMapStep(this.treeRoot, "");

        return this.codeMap;
    }

    /**
     * Steps through the code map recursively
     * 
     * @param treeRoot Current root of the tree we are traversing
     * @param prefix Current build binary string (left = 0, right = 1)
     */
    private void generateMapStep(HuffmanTreeItem<T> treeRoot, String prefix) {
        if(treeRoot.getValue() != null) {
            this.codeMap.put(treeRoot.getValue(), prefix);
        }

        if(treeRoot.getLeftChild() != null)  generateMapStep(treeRoot.getLeftChild(),  prefix + "0");
        if(treeRoot.getRightChild() != null) generateMapStep(treeRoot.getRightChild(), prefix + "1");
    }
}