
public class ARPPacket extends EthernetPacket{

  public ARPPacket(int ethernetHeaderLen, byte [] byteData){
    super(ethernetHeaderLen, byteData);
    int offset = ethernetHeaderLen;
    // header == 8
    this.header = getHeader(offset, 8, byteData);
    this.data = getData(offset, 8, byteData);
    this.packet = getWholePacket(offset, byteData);

  }

  public byte [] getARPPacket(){
    return packet;
  }

  public byte [] getARPHeader(){
    return header; // size 8
  }

  public byte [] getARPData(){
    return data; // size 20
  }




}
