/**
  Ethernet header length is 14 as default.
  As 6 + 6 + 2 + payload.
  @param driver is inherited from Packet class.
*/

public class EthernetPacket extends Packet {

  /**
    EthernetPacket constructor, header length is 14 bytes.
  */

  public EthernetPacket(byte [] byteData){
    this.header = EncodingTools.headerTool(0, _ethernetHeaderLen, byteData);
    this.data = EncodingTools.dataTool(0, _ethernetHeaderLen, byteData);
    this.packet = EncodingTools.packetTool(0, byteData);

  }

/**
  return Ethernet payload, header and whole packet. used by driver.byteArrayToString() method and println to screen.
*/
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
    ipv4 -> 0x0800
    arp -> 0x0806
    return hex String
   */

  public String getProtocol(){
    return EncodingTools.hexJoinTool(12, 2, header, driver, 0);
  }

  public String getSrcMacAddress(){
    return EncodingTools.hexJoinTool(6, 6, header, driver, 1);
  }

  public String getDstMacAddress(){
    return EncodingTools.hexJoinTool(0, 6, header, driver, 1);
  }

  byte [] data;
  byte [] header;
  byte [] packet;
}
