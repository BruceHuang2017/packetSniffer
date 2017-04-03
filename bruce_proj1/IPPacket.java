
public class IPPacket extends EthernetPacket{

  public IPPacket(byte [] byteData){
    super(byteData); // constructor required

    this.packet = ET.packetTool(_ethernetHeaderLen, byteData);
    // debug note: the order of code matters. getrealheaderlength() need packet. then the real header length will be used on header counting.
    if (checkOption()) {
      int realIpHeader = getRealHdrLength();
      this.header = ET.headerTool(_ethernetHeaderLen, realIpHeader, byteData);

    }else{
      this.header = ET.headerTool(_ethernetHeaderLen, _ipHeaderLen, byteData);
    }

    this.data = ET.dataTool(_ethernetHeaderLen, _ipHeaderLen, byteData);
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
    String var5 = super.parse();
    String var6 = "-> IP Level: " + "\n" +
//            "TEST: " + getRealHdrLength() +
            "Header Length: " + getRealHdrLength() + "\n" +
            "Fragment Offset: " + getFragmentOffset() + "\n" +
            "Protocol: " + getProtocol() + "\n" +
            "Source: " + getSrcIPAddress() + "\n" +
            "Destination: " + getDstIPAddress() + "\n";

    return var5 + var6;
  }

  // 0x06 -> tcp, 0x01 -> icmp, 0x11 -> udp, others
  @Override
  public String getProtocol(){
    return (ET.hexJoinTool(9, 1, packet, 0));
  }


  public String getFragmentOffset(){
    return null;
  }

  public void RFCreassembly(){

  }

  public boolean checkOverlapping(){
    return false;
  }

  public boolean checkOversized(){
    return false;
  }

  // version offset 0 bytes in ip packet, the first 4 bits of packet[14] is the version field. ipv4 ->0x4, ipv6 ->0x6.
  public int getVersion(){
    return ((ET.bytesToIntTool(0, packet)>> 4 )& 0xf);
  }

  public String getSrcIPAddress(){
    return ET.hexJoinTool(12, 4, packet, 2);
  }

  public String getDstIPAddress(){
    return ET.hexJoinTool(16, 4, packet, 2);
  }

// true for IHL > 5 and header have options.

  public boolean checkOption(){
    if (getRealHdrLength() == 20) {
      return false;
    }else{
      return true;
    }
  }


  // use packet tool to get option data.
  public byte [] getOptionData(){
    return ET.packetTool(_ipHeaderLen, header);
  }

  //  default = 5 *32 /8 = 20 bytes, IHL offset 0 bytes in ip packet, the last 4 bits of packet[14] is the IHL field.
  public int getRealHdrLength(){
      return (ET.bytesToIntTool(0, packet) & 0xf)*4;
  }

  byte [] data;
  byte [] header;
  byte [] packet;
}
