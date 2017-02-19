
public class EthernetPacket extends Packet{

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

  public String getScrAddress(){

  }

  public String getDstAddress(){

  }

  public int getType(){

  }

// field in this class.

  byte[] header;
  byte[] data;
}
