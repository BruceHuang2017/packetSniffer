import java.nio.ByteBuffer;

public class packetParser{
  public static void main(String[] args) {
      switch (args[0]) {
// Exit after receiving count packets
        case "-c":

          break;
// Read packets from file (your program should read packets from the network by default)
        case "-r":

          break;
// Save output to filename
        case "-o":

          break;

// Print only packets of the specified type where type is one of: eth, arp, ip, icmp, tcp or udp
        case "-t": // should include -h somehow.

          break;

// Print header info only as specified by -t
        case "-h":
          System.out.println("Print header info only as specified by -t");
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
