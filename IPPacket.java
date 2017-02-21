
public class IPPacket extends EthernetPacket {

  byte [] header;
  byte [] data;

  public IPPacket(int headerLen, byte [] byteData){
    super(headerLen, byteData);
    this.header = getHeader(headerLen, 20, byteData);
    this.data = getData(headerLen, 20, byteData);
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
    return getProtocol( 8, 1 , header);
  }


}
