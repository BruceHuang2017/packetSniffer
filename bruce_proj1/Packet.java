import java.nio.ByteBuffer;


public class Packet{
    public byte [] getHeader(){
        return null;
    }

    public byte [] getData(){
        return null;
    }

    public byte [] getPacket(){
        return null;
    }

    public String parse(){
        return null;
    }
    EncodingTools ET = new EncodingTools();
    final int _ethernetHeaderLen = 14;
    final int _ipHeaderLen = 20;
    final int _arpHeaderLen = 8;
    final int _icmpHeaderLen = 8;
    final int _tcpHeaderLen = 20;
    final int _udpHeaderLen = 8;

}
