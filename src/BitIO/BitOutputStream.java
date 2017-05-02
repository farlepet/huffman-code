import java.io.*;

/**
 * \brief Writes bits to a OutputStream in one-byte sets
 */
public class BitOutputStream {
    private OutputStream output; ///< Output to write to

    private int currentBitMask; ///< Current bit
    private int currentByte;    ///< Current byte being manipulated

    /**
     * \brief Creates new instance of BitOutputStream with the given OutputStream
     * 
     * @param output OutputStream to write to
     */
    public BitOutputStream(OutputStream output) {
        if(output == null) throw new IllegalArgumentException("BitOutputStream.BitOutputStream(OutputStream): output is null!");
        this.output         = output;
        this.currentBitMask = 0x80;
    }

    /**
     * \brief Write bit
     * 
     * @param bit Bit to write
     */
    public void write(int bit) throws IOException {
        if(bit > 0) {
            this.currentByte = this.currentByte | this.currentBitMask;
        }
        this.currentBitMask = this.currentBitMask >> 1;

        if(this.currentBitMask == 0) {
            this.output.write(this.currentByte);
            this.currentByte    = 0;
            this.currentBitMask = 0x80;
        }
    }

    /**
     * \brief Write remaining bits, if applicable, and close OutputStream.
     */
    public void close() throws IOException {
        if(this.currentBitMask != 0x80) this.output.write(this.currentByte);
        this.output.close();
    }
}