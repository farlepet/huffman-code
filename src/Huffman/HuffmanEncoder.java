import java.util.*;

/**
 * \brief Facilitates the encoding of values to Huffman codes
 */
public class HuffmanEncoder<T> {
    private Huffman<T> huffman; ///< Huffman instance

    Map<T,String> valueToCode; ///< Map mapping values to code strings

    /**
     * \brief Creates a new instance of HuffmanEncoder using the given Huffman object
     * 
     * @param huffman Huffman instance to gather codes from
     */
    public HuffmanEncoder(Huffman<T> huffman) {
        if(huffman == null) throw new IllegalArgumentException("HuffmanEncoder.HuffmanEncoder(Huffman<T>): huffman is null!");

        this.huffman = huffman;

        this.valueToCode = this.huffman.generateMap();
    }

    /**
     * \brief Retrieves map from Huffman class, and updates the instance variable
     */
    public void updateMap() {
        this.valueToCode = this.huffman.generateMap();
    }

    /**
     * \brief Encodes an array of values to a string of 1's and 0's
     * 
     * @param input Input array of values to encode
     * @return output String of codes
     */
    public String encode(T[] input) {
        if(input == null) throw new IllegalArgumentException("HuffmanEncoder.decode(T): input is null!");

        String output = "";

        for(int i = 0; i < input.length; i++) {
            output = output + this.valueToCode.get(input[i]);
        }

        return output;
    }
}