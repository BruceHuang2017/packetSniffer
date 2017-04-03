
public class UDPPacket extends IPPacket{

  public UDPPacket(byte [] byteData){
    super(byteData);

    this.header = ET.headerTool(_ethernetHeaderLen+ _ipHeaderLen, _udpHeaderLen, byteData);
    this.data = ET.dataTool(_ethernetHeaderLen+ _ipHeaderLen, _udpHeaderLen, byteData);
    this.packet = ET.packetTool(_ethernetHeaderLen+ _ipHeaderLen, byteData);
  }

  @Override
  public byte [] getData(){
    return data;
  }

  @Override
  public byte [] getHeader(){
    return header;
  }

  @Override
  public byte [] getPacket(){
    return packet;
  }

  @Override
  public String parse(){
    String var5 = super.parse();
    String var6 = "-> UDP Level: " + "\n" +
            "Source Port: " + getSrcPort() + "\n" +
            "Destination Port: " + getDstPort() + "\n" +
            "Length: " + getDataLength() + "\n";

    return var5 + var6;
  }

  public int getSrcPort(){
    return ET.bytesToIntTool(0, 2, header);

  }

  public int getDstPort(){
    return ET.bytesToIntTool(2, 2, header);

  }

// return data length of udp packet in bytes
  public int getDataLength(){
    return ((ET.bytesToIntTool(4, 2, header)& 0xff) -8);
  }

  byte [] data;
  byte [] header;
  byte [] packet;

}
