
public class ICMPPacket extends IPPacket {

  byte [] header;
  byte [] data;

  public ICMPPacket(int headerLen, byte [] byteData){
//    super(headerLen, byteData);
//    int offset = headerLen + 20;// ethernet + IP
    int offset = 14 + 20;// ethernet + IP
    this.header = getHeader(offset, 4, byteData); //headerlength = 4
    this.data = getData(offset, 4, byteData);
  }

  public byte[] getICMPPacket(){
    //eth header 14 + IP 20
    return getWholePacket(34, byteData);
  }

  public byte[] getICMPHeader(){
    return header;
  }

  public byte [] getICMPData(){
    return data;
  }

}
