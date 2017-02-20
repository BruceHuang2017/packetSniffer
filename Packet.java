import java.nio.ByteBuffer;
/**
 * Packet driver.
 * Fetch a packet from internet and teardown to packetheader and packetdata.
 */
public class Packet{

  public static byte [] getHeader(int offset, int headerLen, byte [] byteData){
    byte [] header = new byte[headerLen];
    System.arraycopy(byteData, offset, header, 0, headerLen);
    return header;
  }

  public static byte [] getData(int offset, int headerLen, byte [] byteData){
    int dataLength = bytes.length - offset - headerLen;
    byte [] data = new byte[dataLength];
    offset = offset + headerLen;
    System.arraycopy(bytes, offset, data, 0, dataLength);
    return data;
  }

  public static String getSrcAddress(int offset, byte [] byteData){

  }

  public static String getDstAddress(int offset, byte [] byteData){

  }

}
