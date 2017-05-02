import java.io.*;

/**
 * \brief Reads from an InputStream interface one bit at time
 */
public class BitInputStream {
    private InputStream input; ///< Where to read bytes from
    
    private int currentByte;    ///< Current byte we are reading from
    private int currentBitMask; ///< Current bit we are reading from

    /**
     * \brief Creates a new instance of BitReader attached to the given InputStream
     * 
     * @param input InputStream from which to read from
     */
    public BitInputStream(InputStream input) {
        if(input == null) throw new IllegalArgumentException("BitInputStream.BitInputStream(InputStream): input is null!");
        this.input          = input;
        this.currentBitMask = 0;
    }

    /**
     * \brief Read a bit
     * 
     * @return Bit read, or -1 on error
     */
    public int read() throws IOException {
        if(this.currentBitMask == 0) {
            this.currentByte = this.input.read();
            if(this.currentByte < 0) return -1;
            this.currentBitMask = 0x80;
        }

        System.out.println("BitMask: " + this.currentBitMask);

        int retVal = ((this.currentByte & this.currentBitMask) != 0) ? 1 : 0;
        this.currentBitMask  = (this.currentBitMask >> 1);
        return retVal;
    }

    /**
     * \brief Close the InputStream
     */
    public void close() throws IOException {
        this.input.close();
    }
}