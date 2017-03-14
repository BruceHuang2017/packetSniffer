
public class ICMPPacket extends IPPacket{

  public ICMPPacket(byte [] byteData){
    super(byteData);

    this.header = EncodingTools.headerTool(_ethernetHeaderLen+_ipHeaderLen, _icmpHeaderLen, byteData);
    this.data = EncodingTools.dataTool(_ethernetHeaderLen+_ipHeaderLen, _icmpHeaderLen, byteData);
    this.packet = EncodingTools.packetTool(_ethernetHeaderLen+_ipHeaderLen, byteData);

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

  byte [] data;
  byte [] header;
  byte [] packet;

}
