

public class ARPPacket extends EthernetPacket{

  public ARPPacket(byte [] byteData){
    super(byteData);

    this.header = EncodingTools.headerTool(_ethernetHeaderLen, _arpHeaderLen, byteData);
    this.data = EncodingTools.dataTool(_ethernetHeaderLen, _arpHeaderLen, byteData);
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

  /**
    request, ip parts are null.
    reply, all not null.
  */

  public String getSenderHwAddress(){
    return EncodingTools.hexJoinTool(8, 6, header, driver, 1);
  }

  public String getSenderPtAddress(){
    return EncodingTools.hexJoinTool(14, 4, header, driver, 2);
  }

  public String getTargetHwAddress(){
    return EncodingTools.hexJoinTool(18, 6, header, driver, 1);
  }

  public String getTargetPtAddress(){
    return EncodingTools.hexJoinTool(24, 4, header, driver, 2);
  }

  /**
    0x0001 -> request
    0x0002 -> reply
  */

  public String getOperation(){
    return EncodingTools.hexJoinTool(6, 2, header, driver, 0);
  }

  byte [] data;
  byte [] header; //arp header
  byte [] packet;
}
