import java.util.*;
import java.io.*;

/**
 * \brief Facilitates of decoding strings of huffman codes into their origional values
 */
public class HuffmanDecoder<T> {
    private Huffman<T> huffman; ///< Huffman instance

    private Map<String,T> codeToValue; ///< Maps code strings to values

    int longestKey = 0; ///< Longest Huffman code, used for errorchecking

    /**
     * \brief Creates a new instance of HuffmanDecoder using the given Huffman object
     * 
     * @param huffman Huffman instance to gather codes from
     */
    public HuffmanDecoder(Huffman<T> huffman) {
        if(huffman == null) throw new IllegalArgumentException("HuffmanDecoder.HuffmanDecoder(Huffman<T>): huffman is null!");

        this.huffman = huffman;

        this.updateMap();
    }

    /**
     * \brief Retrieves map from Huffman class, reverses the map, finds the longest code, and updates instance
     * variables accordingly.
     */
    public void updateMap() {
        this.codeToValue = new HashMap<String,T>();

        Map<T,String> valueToCode = this.huffman.generateMap();
        T[] keys;
        
        @SuppressWarnings("unchecked")
        T[] emptyArray = (T[])new Object[0];
        
        keys = valueToCode.keySet().toArray(emptyArray);

        this.longestKey = 0;

        for(int i = 0; i < keys.length; i++) {
            this.codeToValue.put(valueToCode.get(keys[i]), keys[i]);
            if(valueToCode.get(keys[i]).length() > longestKey) {
                this.longestKey = valueToCode.get(keys[i]).length();
            }
        }
    }

    /**
     * \brief Decodes the string of Huffman codes into a list of values.
     * 
     * @param code String of Huffman codes
     * @return ArrayList of decoded values
     */
    public ArrayList<T> decode(String code) {
        if(code == null) throw new IllegalArgumentException("HuffmanDecoder.decode(String): code is null!");

        ArrayList<T> decodedItems = new ArrayList<T>();
        String currentCode = "";

        for(int i = 0; i < code.length(); i++) {
            currentCode = currentCode + code.charAt(i);
            if(currentCode.length() > longestKey) {
                throw new RuntimeException("HuffmanDecoder.decode(String): Could not find value matching code, maximum length exceeded!");
            }
            if(this.codeToValue.containsKey(currentCode)) {
                decodedItems.add(this.codeToValue.get(currentCode));
                currentCode = "";
            }
        }

        return decodedItems;
    }

    /**
     * \brief Decodes the bit stream of Huffman codes into a list of values.
     * 
     * @param input Bit stream of Huffman codes
     * @return ArrayList of decoded values
     */
    public ArrayList<T> decode(BitInputStream input) {
        if(input == null) throw new IllegalArgumentException("HuffmanDecoder.decode(BitInputStream): input is null!");

        ArrayList<T> decodedItems = new ArrayList<T>();
        String currentCode = "";
        int currentBit;

        try {
            while((currentBit = input.read()) >= 0) {
                currentCode = currentCode + ((currentBit == 1) ? "1" : "0");
                if(currentCode.length() > longestKey) {
                    throw new RuntimeException("HuffmanDecoder.decode(String): Could not find value matching code, maximum length exceeded!");
                }
                if(this.codeToValue.containsKey(currentCode)) {
                    decodedItems.add(this.codeToValue.get(currentCode));
                    currentCode = "";
                }
            }
        }
        catch(IOException e) {
            throw new RuntimeException("HuffmanDecoder.decode(BitInputStream): Error while reading from BitInputStream: " + e.getMessage());
        }

        return decodedItems;
    }
}