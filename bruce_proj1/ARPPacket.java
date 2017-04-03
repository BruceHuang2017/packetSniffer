

public class ARPPacket extends EthernetPacket{

  public ARPPacket(byte [] byteData){
    super(byteData);

    this.header = ET.headerTool(_ethernetHeaderLen, _arpHeaderLen, byteData);
    this.data = ET.dataTool(_ethernetHeaderLen, _arpHeaderLen, byteData);
    this.packet = ET.packetTool(_ethernetHeaderLen, byteData);
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
    String var5 =  super.parse();
    String var6 = "-> ARP Level:" + "\n" +
            "Sender Mac Address: " + getSenderMacAddress() + "\n" +
            "Sender IP Address: " + getSenderIPAddress() + "\n" +
            "Target Mac Address: " + getTargetMacAddress() + "\n" +
            "Target IP Address: " + getTargetIPAddress() + "\n";
    var6 = var5 + var6;
    return var6;
  }

  /**
   request, ip parts are null.
   reply, all not null.
   */

  public String getSenderMacAddress(){
    return ET.hexJoinTool(8, 6, packet, 1);
  }

  public String getSenderIPAddress(){
    return ET.hexJoinTool(14, 4, packet, 2);
  }

  public String getTargetMacAddress(){
    return ET.hexJoinTool(18, 6, packet, 1);
  }

  public String getTargetIPAddress(){
    return ET.hexJoinTool(24, 4, packet, 2);
  }

  /**
   0x0001 -> request
   0x0002 -> reply
   */

  public String getOperation(){
    return ET.hexJoinTool(6, 2, packet, 0);
  }

  byte [] data;
  byte [] header; //arp header
  byte [] packet;
}
