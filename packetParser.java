import java.nio.ByteBuffer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class packetParser{
  public static void main(String[] args) {
      switch (args[0]) {
// Exit after receiving count packets
        case "-c":
          for (int count = 0; count<args[1]; count ++)
            System.out.println(PacketDriver.getStringPacket();)
          break;
// Read packets from file (your program should read packets from the network by default)
        case "-r":
          if (args[1]==null) {
              while(true){
                PacketDriver.getStringPacket();
              }
          }else{
            String content = new String(Files.readAllBytes(Paths.get(args[1])));
            System.out.println(content);
          }
          break;
// Save output to filename, can only save one file for now, could use while loop to get mutipule packets.
        case "-o":
          String content = PacketDriver.getByteArrayPacket();
          Files.write(Paths.get(packetParserOutput.txt), content.getBytes(), StandardOpenOption.CREATE);
          break;

// Print only packets of the specified type where type is one of: eth, arp, ip, icmp, tcp or udp
        case "-t": // should include -h somehow.
          if (args.length == 3) {
            if(args[2] == "-t"){
              // print selected header.
              switch(args[1]){
                case "eth":
                  byte [] ethHeader = Packet.getEthernetHeader();

                  break;
                case "arp":
                  byte [] arpHeader = Packet.getARPHeader();

                  break;
                case "ip":
                  byte [] ipHeader = Packet.getIPHeader();

                  break;
                case "icmp":
                  byte [] icmpHeader = Packet.getICMPHeader();

                  break;
                case "tcp":
                  byte [] tcpHeader = Packet.getTCPHeader();

                  break;
                case "udp":
                  byte [] udpHeader = Packet.getUDPHeader();


                  break;
                default:
                  System.out.println("Illegal parameter usage");
                  return;
                }
              }
            }else if(args.length == 2){
              // print selected whole package.
              switch(args[1]){
                case "eth":
                // assume all packets are eth pac.
                  byte [] ethPacket = Packet.getEthernetPacket();

                  break;
                case "arp":
                  byte [] arpPacket = Packet.getARPPacket();

                  break;
                case "ip":
                  byte [] ipPacket = Packet.getIPPacket();

                  break;
                case "icmp":
                  byte [] icmpPacket = Packet.getICMPPacket();

                  break;
                case "tcp":
                  byte [] tcpPacket = Packet.getTCPPacket();

                  break;
                case "udp":
                  byte [] udpPacket = Packet.getUDPPacket();


                  break;
                default:
                  System.out.println("type is not legal.");
                  return;
                }
            }else{
              // not '-t type -h' or '-t type'.
              System.out.println("not '-t type -h' or '-t type'.");
            }
          break;

// Print header info only as specified by -t
        case "-h":
          System.out.println("Print header info only as specified by -t.");
          break;

// Print only packets with source address equal to saddress
        case "-src":

          break;

// Print only packets with destination address equal to daddress
        case "-dst":

          break;

// Print only packets where the source address matches saddress or the destination address matches daddress
        case "-sord":

          break;

// Print only packets where the source address matches saddress and the destination address matches daddress
        case "-sandd":

          break;

// Print only packets where the source port is in the range port1-port2
        case "-sport":

          break;

// Print only packets where the destination port is in the range port1-port2
        case "-dpost":

          break;

        default:
          System.out.println("Illegal parameter usage.");
          return;
      }

  }

}
