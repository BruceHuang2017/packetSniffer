
public class ICMPPacket extends IPPacket {

  byte [] header;
  byte [] data;
  byte [] packet;

  public ICMPPacket(int ethernetHeaderLen, byte [] byteData){
    super(ethernetHeaderLen, byteData);
    int offset = ethernetHeaderLen + 20; // ethernet + IP
    this.header = getHeader(offset, 4, byteData); //icmp Header Length = 4
    this.data = getData(offset, 4, byteData);
    this.packet = getWholePacket(offset, byteData);
  }

  public byte[] getICMPPacket(){
    return packet;
  }

  public byte[] getICMPHeader(){
    return header; // size 4
  }

  public byte [] getICMPData(){
    return data; // size 4
  }

}
