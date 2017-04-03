
public class TCPPacket extends IPPacket{

  public TCPPacket(byte [] byteData){
    super(byteData);
    int ipheader = super.getRealHdrLength();
    this.packet = ET.packetTool(_ethernetHeaderLen+ipheader, byteData);
    if (checkOption()) {
      int realTcpHeader = getRealHdrLength();
      this.header = ET.headerTool(_ethernetHeaderLen+ipheader, realTcpHeader, byteData);
    }else{
      ET.headerTool(_ethernetHeaderLen+ipheader, _tcpHeaderLen, byteData);
    }

    this.data = ET.dataTool(_ethernetHeaderLen+ipheader, _tcpHeaderLen, byteData);

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

  @Override
  public String parse(){
      String var6 = "-> TCP Level: " + "\n" +
                "getHdrLength(): " + 20 + "\n" +
                "Header.length: " + header.length + "\n" +
                "data.length: " + data.length + "\n" +
                "packet.length: " + packet.length + "\n" +
                "Length: " + getRealHdrLength() + "\n";

    return super.parse() + var6;
  }

// source port and dst port are first 2 and 2- 4 bytes of the header.
  public int getSrcPort(){
    return ET.bytesToIntTool(0, 2, packet);
  }

  public int getDstPort(){
    return ET.bytesToIntTool(2, 2, packet);
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
    return ET.packetTool(_tcpHeaderLen, packet);
  }

  //  default = 5 *32 /8 = 20 bytes, data offset is 12 bytes in ip packet, the last 4 bits of packet[12] is the IHL field.
  @Override
  public int getRealHdrLength(){
    return 20;
      //return ((ET.bytesToIntTool(12, packet)>> 4 )& 0xf) * 4;
  }

  byte [] data;
  byte [] header;
  byte [] packet;

}
