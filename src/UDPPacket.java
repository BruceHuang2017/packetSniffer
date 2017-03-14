
public class UDPPacket extends IPPacket{

  public UDPPacket(byte [] byteData){
    super(byteData);

    this.header = EncodingTools.headerTool(_ethernetHeaderLen+ _ipHeaderLen, _udpHeaderLen, byteData);
    this.data = EncodingTools.dataTool(_ethernetHeaderLen+ _ipHeaderLen, _udpHeaderLen, byteData);
    this.packet = EncodingTools.packetTool(_ethernetHeaderLen+ _ipHeaderLen, byteData);

  }

  @Override
  public byte [] getData(){
    return data;
  }

  @Override
  public byte [] getHeader(){
    return header;
  }

  @Override
  public byte [] getPacket(){
    return packet;
  }

  public int getSrcPort(){
    return EncodingTools.bytesToIntTool(0, 2, header, driver);

  }

  public int getDstPort(){
    return EncodingTools.bytesToIntTool(2, 2, header, driver);

  }

// return data length of udp packet in bytes
  public int getDataLength(){
    return ((EncodingTools.bytesToIntTool(4, 2, header, driver)& 0xff) -8);
  }

  byte [] data;
  byte [] header;
  byte [] packet;

}
