import java.nio.ByteBuffer;

/** Sort packet as much details as possible.
 * every packet is an Ethernet packet initially.
 * then ARP or IPv4,
 * then if IPv4, TCP, UDP or TCMP.
 *
 */
public class PacketParser{
    byte[] packet;

    public String parse(byte[] packet){
        this.packet=packet;
        return null;

    }

}
