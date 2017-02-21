

public class EthernetPacket extends Packet{

// field in this class. not modifies type, works only in this class and subclasses.

  byte [] header;
  byte [] data;
//  int ethernetDstOffset = 0;
//  int ethernetSrcOffset = 6;
//  int typeOffset = 12;
//  int widthOfType = 2;
//  int widthOfAddress = 6;
//  int IP = 0x0800;
//  int ARP = 0x0806;

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

/*

// overwrite getheader
  public byte[] getHeader(){
    return getEthernetHeader();
  }

// overwrite getdata or get payload
  public byte[] getData(){
    return getEthernetData();
  }

*/

  public String getEthernetSrcAddress(){
    return getAddress(0, 6, byteData);
  }

  public String getEthernetDstAddress(){
    return getAddress(6, 6, byteData);
  }


// two options of protocols: ip [0800] and arp[0806].
  public int getEthernetType(){

/*
    int typeCode = 0;
    for(int i=0; i<widthOfType; i++)
      value[i] = byteData[typeOffset + i];
    typeCode = value[0]*100 + value[1];
    return typeCode;
*/
// offset 12, length of type field is 2

  return getProtocol(12, 2, byteData);
  }




}
