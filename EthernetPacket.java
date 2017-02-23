

public class EthernetPacket extends Packet{

  byte [] header;
  byte [] data;
  byte [] packet;
//  int ethernetHeaderLen = 14;
//  int ethernetDstOffset = 0;
//  int ethernetSrcOffset = 6;
//  int typeOffset = 12;
//  int widthOfType = 2;
//  int widthOfAddress = 6;
//  int IP = 0x0800;
//  int ARP = 0x0806;

// methods in this class.

  public EthernetPacket(int ethernetHeaderLen, byte [] byteData){
    int offset = 0;
    this.header = getHeader(offset, ethernetHeaderLen, byteData);
    this.data = getData(offset, ethernetHeaderLen, byteData);
    this.packet = getWholePacket(offset, byteData);
  }

  public byte[] getEthernetPacket(){
    return packet; // size ?
  }

  public byte[] getEthernetHeader(){
    return header; //size 14
  }

  public byte[] getEthernetData(){
    return data; // size ?
  }

  public String getEthernetSrcAddress(){
    return getAddress(0, 6, byteData); //size 6
  }

  public String getEthernetDstAddress(){
    return getAddress(6, 6, byteData); // size 6
  }


// two options of protocols: ip [0800] and arp[0806].
  public int getType(){
    return getProtocol(12, 2, byteData); // ip 0x0800, arp 0x0806, others. size 2

    /*
        int typeCode = 0;
        for(int i=0; i<widthOfType; i++)
          value[i] = byteData[typeOffset + i];
        typeCode = value[0]*100 + value[1];
        return typeCode;
    */
    // offset 12, length of type field is 2
  }



}
