
public class TCPPacket extends IPPacket {

  byte [] header;
  byte [] data;

  public TCPPacket(int headerLen, byte [] byteData){
    super(headerLen, byteData);
    int offset = 14 + 20; //14 for ethernet, 20 for ip header, no option is included.
    this.header = getHeader(offset, 20, byteData);
    this.data = getData(offset, 20, byteData);
  }

  public byte[] getTCPHeader(){
    return header;
  }

  public byte [] getTCPData(){
    return data;
  }

  public int getSrcPort(){
    return getPort(26, 4, byteData); // port start at 27
  }

  public int getDstPort(){
    return getPort(30, 4, byteData); //port start at 31
  }

}
