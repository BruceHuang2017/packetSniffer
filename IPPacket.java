
public class IPPacket extends EthernetPacket {

  byte [] header;
  byte [] data;
  byte [] packet;

  public IPPacket(int ethernetHeaderLen, byte [] byteData){
    super(ethernetHeaderLen, byteData);
    int offset = ethernetHeaderLen;
    this.header = getHeader(offset, 20, byteData); //20 bytes ip default
    this.data = getData(offset, 20, byteData);
    this.packet = getWholePacket(offset, byteData);
  }

  public byte[] getIPPacket(){
    return packet;
  }

  public byte [] getIPHeader(){
    return header; //size 20
  }

  public byte [] getIPData(){
    return data; //size ?
  }

// IPv4
  public int getIPVersion(){
    int v = 0x4;
    return v;
  }

// offset 8, length of protocol field is 1
  public int getIPProtocol(){
    return getProtocol( 8, 1 , header); // icmp 0x01, tcp 0x06, udp 0x11, others.
  }
  // tcp = 6, udp = 17, icmp = 1


}
