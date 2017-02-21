
public class EthernetPacket extends Packet{

// field in this class. not modifies type, works only in this class and subclasses.

  byte[] header;
  byte[] data;
  int ethernetDstOffset = 0;
  int ethernetSrcOffset = 6;
  int typeOffset = 12;
  int widthOfType = 2;
  int widthOfAddress = 6;
  int IP = 0x0800;
  int ARP = 0x0806;

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

// overwrite getheader
  public byte[] getHeader(){
    return getEthernetHeader;
  }

// overwrite getdata or get payload
  public byte[] getData(){
    return getEthernetData;
  }

  public String getEthernetSrcAddress(){
    return getAddress(ethernetSrcOffset, widthOfAddress, byteData);
  }

  public String getEthernetDstAddress(){
    return getAddress(ethernetDstOffset, widthOfAddress, byteData);
  }


// two options of protocols: ip and arp.
  public int getEthernetType(){
    return getProtocol();
  }

}
