/**
 Ethernet header length is 14 as default.
 As 6 + 6 + 2 + payload.
 */

public class EthernetPacket extends Packet {

  /**
   EthernetPacket constructor, header length is 14 bytes.
   */

  public EthernetPacket(byte [] byteData){
    this.header = ET.headerTool(0, _ethernetHeaderLen, byteData);
    this.data = ET.dataTool(0, _ethernetHeaderLen, byteData);
    this.packet = byteData;

  }

  /**
   return Ethernet payload, header and whole packet.
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

  @Override
  public String parse(){
    String var6 = new String();
    String srcmac = getSrcMacAddress();
    String dstmac = getDstMacAddress();
    // problem: if the type is others, program crash
    int type = Integer.parseInt(getProtocol());
    String Type = null;
    if(ET.bytesToIntTool(13,1,header) == 0)
      Type = "IPv4";
    else
      Type = "ARP";
    var6 = "***********************" + "\n" +
            "-> Ethernet Packet:" + "\n" +
            "Source: " + srcmac + "\n" +
            "Destination: " + dstmac + "\n" +
            "Type: " + Type + "(0" + type + ")" + "\n";
    return var6;
  }

  /**
   ipv4 -> 0x0800
   arp -> 0x0806
   return hex String
   */
  public String getProtocol(){
    return ET.hexJoinTool(12, 2, header, 0);
  }

  public String getSrcMacAddress(){
    return ET.hexJoinTool(6, 6, header, 1);
  }

  public String getDstMacAddress(){
    return ET.hexJoinTool(0, 6, header, 1);
  }

  byte [] data;
  byte [] header;
  byte [] packet;
}
