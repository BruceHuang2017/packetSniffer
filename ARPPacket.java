
public class ARPPacket extends EthernetPacket{

  public ARPPacket(int headerLen, byte [] byteData){
    super(headerLen, byteData);
    this.header = getHeader(0, headerLen, byteData);
    this.data = getData(0, headerLen, byteData);

  }

  public byte [] getARPHeader(){
    return header; //totally 28 bytes
  }

  public byte [] getARPData(){
    return new byte[0];
  }



}
