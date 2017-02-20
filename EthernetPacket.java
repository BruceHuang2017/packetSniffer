
public class EthernetPacket extends Packet{

// field in this class. not modifies type, works only in this class and subclasses.

  byte[] header;
  byte[] data;
  int ethernetDstOffset = 0;
  int ethernetSrcOffset = 6;
  int typeOffset = 12;
  int widthOfType = 2;
  int widthOfAddress = 6;

// methods in this class.

  public EthernetPacket(int headerLen, byte [] byteData){
    this.header = getHeader(0, headerLen, byteData);
    this.data = getData(0, headerLen, byteData);
  }

  public byte[] getEthernetHeader(){
    return header;
  }

  public byte[] getEthernetData(){
    return data;
  }

  public String getEthernetSrcAddress(){
    return getSrcAddress(ethernetSrcOffset, byteData);
  }

  public String getEthernetDstAddress(){
    return getDstAddress(ethernetDstOffset, byteData);
  }

  public int getType(){

  }



}
