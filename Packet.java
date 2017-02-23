import java.nio.ByteBuffer;
/**
 * Packet driver.
 * Fetch a packet from internet and teardown to packetheader and packetdata.
 */

public class Packet extends PacketDriver{

//  SimplePacketDriver driver=new SimplePacketDriver();

  public static byte [] getHeader(int offset, int headerLen, byte [] byteData){
    byte [] header = new byte[headerLen];
    System.arraycopy(byteData, offset, header, 0, headerLen);
    return header;
  }

  public static byte [] getData(int offset, int headerLen, byte [] byteData){
    int dataLength = byteData.length - offset - headerLen;
    if (dataLength <= 0)
      return new byte[0];
    byte [] data = new byte[dataLength];
    offset = offset + headerLen;
    System.arraycopy(byteData, offset, data, 0, dataLength);
    return data;
  }

  public static String getAddress(int offset, int widthOfAddress, byte [] byteData){
    String address;
    byte [] addressByte = new byte[widthOfAddress];
    System.arraycopy(byteData, offset, addressByte, 0, widthOfAddress);
    address = driver.byteArrayToString(addressByte);
    return address;
  }

  public static int getProtocol(int offset, int widthOfProtocol, byte [] byteData){
    int value = 0;
    for(int i=0; i<widthOfProtocol; i++)
      value |= ((byteData[offset + widthOfProtocol - i - 1] & 0xff) << 8 * i);
    return value;
  }

  public static int getPort(int offset, int widthOfPort, byte [] byteData){
    return getProtocol(offset, widthOfPort, byteData);
  }

  public static byte [] getWholePacket(int offset, byte[] byteData){
    int packetLength = byteData.length - offset;
    byte [] packet = new byte[packetLength];
    System.arraycopy(byteData, offset, packet, 0, packetLength);
    return packet;
  }


}
