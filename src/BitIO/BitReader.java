import java.io.*;

/**
 * \brief Reads from a Reader interface one bit at time
 */
public class BitReader {
    private Reader reader; ///< Where to read bytes from
    
    private int currentByte;    ///< Current byte we are reading from
    private int currentBitMask; ///< Current bit we are reading from

    /**
     * \brief Creates a new instance of BitReader attached to the given Reader
     * 
     * @param reader Reader from which to read from
     */
    public BitReader(Reader reader) {
        if(reader == null) throw new IllegalArgumentException("BitReader.BitReader(Reader): reader is null!");
        this.reader         = reader;
        this.currentBitMask = 0;
    }

    /**
     * \brief Read a bit
     * 
     * @return Bit read, or -1 on error
     */
    public int read() throws IOException {
        if(this.currentBitMask == 0) {
            this.currentByte = this.reader.read();
            if(this.currentByte < 0) return -1;
            this.currentBitMask = 0x80;
        }

        System.out.println("BitMask: " + this.currentBitMask);

        int retVal = ((this.currentByte & this.currentBitMask) != 0) ? 1 : 0;
        this.currentBitMask  = (this.currentBitMask >> 1);
        return retVal;
    }

    /**
     * \brief Close the Reader
     */
    public void close() throws IOException {
        this.reader.close();
    }
}