
public class TCPPacket extends IPPacket {

  byte [] header;
  byte [] data;
  byte [] packet;

  public TCPPacket(int ethernetHeaderLen, byte [] byteData){
    super(ethernetHeaderLen, byteData);
    int offset = ethernetHeaderLen + 20;
//14 for ethernet, 20 for ip header, no option is included.
//default TCP header length == 20
    this.header = getHeader(offset, 20, byteData);
    this.data = getData(offset, 20, byteData);
    this.packet = getWholePacket(offset, byteData);
  }

  public byte[] getTCPPacket(){
    return packet;
  }

  public byte[] getTCPHeader(){
    return header; //size 20
  }

  public byte [] getTCPData(){
    return data; // size ?
  }

  public int getSrcPort(){
    return getPort(26, 4, byteData); // port start at 27, size 4
  }

  public int getDstPort(){
    return getPort(30, 4, byteData); //port start at 31, size 4 bytes
  }

}
