import java.nio.ByteBuffer;


public class PacketHunter{

  SimplePacketDriver driver = new SimplePacketDriver();
  String [] adapter;
  byte [] bytePacket;
  ByteBuffer bufferPacket;
  String ethProtocol;
  String ipProtocol;

  public PacketHunter(){

    this.adapter = driver.getAdapterNames();
    this.bytePacket = driver.readPacket(); // blocking operation

    this.bufferPacket = ByteBuffer.wrap(bytePacket);

// turn data into an Packet object.
    EthernetPacket packetObject = new EthernetPacket(bytePacket);
    try {
      TCPPacket tcpPacket = (TCPPacket)packetObject;
    }catch( Exception e ) {
      e.printStackTrace();
    }

    if (packetObject instanceof IPPacket) {
      System.out.println("yes");
    }
//    packetObject.EthernetPacket(bytePacket);

    this.ethProtocol = packetObject.getProtocol();

  }

  public String getPacketType(){
    return null;
  }

  public static void main(String[] args) throws Exception{
    PacketHunter a = new PacketHunter();
    return a.getPacketType();
  }


}
