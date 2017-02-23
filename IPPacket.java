
public class IPPacket extends EthernetPacket {

  byte [] header;
  byte [] data;

  public IPPacket(int headerLen, byte [] byteData){
    super(headerLen, byteData);
    this.header = getHeader(headerLen, 20, byteData); //20 bytes headerlength
    this.data = getData(headerLen, 20, byteData);
  }

  public byte[] getIPPacket(){
    //eth header 14
    return getWholePacket(14, byteData);
  }

  public byte [] getIPHeader(){
    return header;
  }

  public byte [] getIPData(){
    return data;
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


}
