
public class UDPPacket extends IPPacket {

  byte [] header;
  byte [] data;

  public UDPPacket(int headerLen, byte [] byteData){
    super(headerLen, byteData);
    int offset = 14 + 20; // same as ip
    this.header = getHeader(offset, 8, byteData);
    this.data = getData(offset, 8, byteData);
  }

  public byte[] getUDPPacket(){
    //eth header 14 + IP 20
    return getWholePacket(34, byteData);
  }
  public byte[] getUDPHeader(){
    return header;
  }

  public byte [] getUDPData(){
    return data;
  }

  public int getSrcPort(){
    return getPort(26, 4, byteData); // port start at 27
  }

  public int getDstPort(){
    return getPort(30, 4, byteData); //port start at 31
  }

}
