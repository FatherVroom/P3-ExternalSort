import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Able to go through the blocks of bytes (which can be
 * thought of as an array of bytes) and read records inside the block. Each
 * block contains 512 records and each record is 16 bytes of memory
 * 
 * @author Aniket Adhikari
 * @version 2 April 2022
 *
 */
public class Parser {
    private static final int BLOCK_SIZE = 8192;
    private String fileName;
    private RandomAccessFile raf;

    /**
     * Creates a Parser object, which is meant to go through fileName
     * 
     * @param fileName
     *            the name of the file that is meant to be found
     * @throws FileNotFoundException
     *             if the file we want to parse doesn't exist in our folder
     */
    public Parser(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        try {
            raf = new RandomAccessFile(fileName, "r");
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not find the file: "
                + fileName);
        }
    }


    /**
     * Takes the binary input file and constructs a byte array the size of a
     * single block, which is 8192 bytes, and 512 "records" or key-value pairs
     * 
     * @return a block of data as a byte array of size 8192
     * @throws IOException
     *             when EOF has been reached
     */
    public byte[] getBlockAsByteArray() throws IOException {

        ByteBuffer block = ByteBuffer.allocate(BLOCK_SIZE);
        try {
            for (int i = 1; i <= BLOCK_SIZE; i++) {
                byte b = raf.readByte();
                block.put(b);
            }
        }
        catch (EOFException e) {
            throw new EOFException("End of the file titled, " + fileName
                + ", has been reached");
        }
        return block.array();
    }
}
