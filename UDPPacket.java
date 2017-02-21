
public class UDPPacket extends IPPacket {

  byte [] header;
  byte [] data;

  public UDPPacket(int headerLen, byte [] byteData){
    super(headerLen, byteData);
    int offset = 14 + 20;
    this.header = getHeader(offset, 8, byteData);
    this.data = getData(offset, 8, byteData);
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
