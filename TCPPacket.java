
public class TCPPacket extends IPPacket {

  byte [] header;
  byte [] data;

  public TCPPacket(int headerLen, byte [] byteData){
    super(headerLen, byteData);
    int offset = 14 + 20; //20 size, no option is included.
    this.header = getHeader(offset, 20, byteData);
    this.data = getData(offset, 20, byteData);
  }

  public byte[] getHeader(){
    return header;
  }

  public byte [] getData(){
    return data;
  }

  public int getSrcPort(){

  }

  public int getDstPort(){

  }
}
