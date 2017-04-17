
public class IPPacket extends EthernetPacket{

  public IPPacket(byte [] byteData){
    super(byteData);

    if (checkOption()) {
      int realIpHeader = getRealHdrLength();
      this.header = EncodingTools.headerTool(_ethernetHeaderLen, realIpHeader, byteData);

    }else{
      EncodingTools.headerTool(_ethernetHeaderLen, _ipHeaderLen, byteData);
    }

    this.data = EncodingTools.dataTool(_ethernetHeaderLen, _ipHeaderLen, byteData);
    this.packet = EncodingTools.packetTool(_ethernetHeaderLen, byteData);

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

// 0x06 -> tcp, 0x01 -> icmp, 0x11 -> udp, others
  @Override
  public String getProtocol(){
    return (EncodingTools.hexJoinTool(9, 1, header, driver, 0));
  }

// version offset 0 bytes in ip packet, the first 4 bits of packet[14] is the version field. ipv4 ->0x4, ipv6 ->0x6.
  public int getVersion(){
    return ((EncodingTools.bytesToIntTool(0, header, driver)>> 4 )& 0xf);
  }

  public String getSrcIPAddress(){
    return EncodingTools.hexJoinTool(12, 4, header, driver, 2);
  }

  public String getDstIPAddress(){
    return EncodingTools.hexJoinTool(16, 4, header, driver, 2);
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
    return EncodingTools.packetTool(_ipHeaderLen, header);
  }

//  default = 5 *32 /8 = 20 bytes, IHL offset 0 bytes in ip packet, the last 4 bits of packet[14] is the IHL field.
  public int getRealHdrLength(){
    return ((EncodingTools.bytesToIntTool(0, header, driver)& 0xf ) * 4);
  }

  byte [] data;
  byte [] header;
  byte [] packet;

}
