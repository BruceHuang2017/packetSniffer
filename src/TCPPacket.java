
public class TCPPacket extends IPPacket{

  public TCPPacket(byte [] byteData){
    super(byteData);

    if (checkOption()) {
      int realTcpHeader = getRealHdrLength();
      this.header = EncodingTools.headerTool(_ethernetHeaderLen+_ipHeaderLen, realTcpHeader, byteData);
    }else{
      EncodingTools.headerTool(_ethernetHeaderLen+_ipHeaderLen, _tcpHeaderLen, byteData);
    }

    this.data = EncodingTools.dataTool(_ethernetHeaderLen+_ipHeaderLen, _tcpHeaderLen, byteData);

    this.packet = EncodingTools.packetTool(_ethernetHeaderLen+_ipHeaderLen, byteData);

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

// source port and dst port are first 2 and 2- 4 bytes of the header.
  public int getSrcPort(){
    return EncodingTools.bytesToIntTool(0, 2, header, driver);
  }

  public int getDstPort(){
    return EncodingTools.bytesToIntTool(2, 2, header, driver);
  }

  // true for data offset > 5, which means the header length is larger than 20 bytes, and header have options.
  @Override
  public boolean checkOption(){
    if (getRealHdrLength() == 20) {
      return false;
    }else{
      return true;
    }
  }

  // use packet tool to get option data.
  @Override
  public byte [] getOptionData(){
    return EncodingTools.packetTool(_tcpHeaderLen, header);
  }

  //  default = 5 *32 /8 = 20 bytes, data offset is 12 bytes in ip packet, the last 4 bits of packet[12] is the IHL field.
  @Override
  public int getRealHdrLength(){
    return ((EncodingTools.bytesToIntTool(12, header, driver)>> 4 )& 0xf) * 4;
  }

  byte [] data;
  byte [] header;
  byte [] packet;

}
