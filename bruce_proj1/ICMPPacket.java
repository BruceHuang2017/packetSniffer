
public class ICMPPacket extends IPPacket{

  public ICMPPacket(byte [] byteData){
    super(byteData);

    this.header = ET.headerTool(_ethernetHeaderLen+_ipHeaderLen, _icmpHeaderLen, byteData);
    this.data = ET.dataTool(_ethernetHeaderLen+_ipHeaderLen, _icmpHeaderLen, byteData);
    this.packet = ET.packetTool(_ethernetHeaderLen+_ipHeaderLen, byteData);
  }

  public byte [] getData(){
    return data;
  }

  public byte [] getHeader(){
    return header;
  }

  public byte [] getPacket(){
    return packet;
  }
@Override
  public String parse(){
      return null;
  }

  byte [] data;
  byte [] header;
  byte [] packet;

}
