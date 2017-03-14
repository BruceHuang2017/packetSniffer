
public class UDPPacket extends IPPacket {

  byte [] header;
  byte [] data;
  byte [] packet;

  public UDPPacket(int ethernetHeaderLen, byte [] byteData){
    super(ethernetHeaderLen, byteData);
    int offset = ethernetHeaderLen + 20; // ip
    this.header = getHeader(offset, 8, byteData);
    this.data = getData(offset, 8, byteData);
    this.packet = getWholePacket(offset, byteData);
  }

  public byte[] getUDPPacket(){
    return packet;
  }

  public byte[] getUDPHeader(){
    return header; //size 8
  }

  public byte [] getUDPData(){
    return data; // size ?
  }

  public int getSrcPort(){
    return getPort(26, 4, byteData); // port start at 27 (ethernet + ip + someUdp)
  }

  public int getDstPort(){
    return getPort(30, 4, byteData); //port start at 31
  }

}
