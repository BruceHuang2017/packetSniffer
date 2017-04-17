import java.nio.ByteBuffer;

public class PacketDriverExample implements EncodingTools
{
	public static void main(String[] args) {
        SimplePacketDriver driver=new SimplePacketDriver();
	//Get adapter names and print info
        String[] adapters=driver.getAdapterNames();
        System.out.println("Number of adapters: "+adapters.length);
        for (int i=0; i< adapters.length; i++) System.out.println("Device name in Java ="+adapters[i]);
	//Open first found adapter (usually first Ethernet card found)
        if (driver.openAdapter(adapters[0])) System.out.println("Adapter is open: "+adapters[0]);
	//Read a packet (blocking operation)
        byte [] packet=driver.readPacket();
        //Wrap it into a ByteBuffer
        ByteBuffer Packet=ByteBuffer.wrap(packet);
        //Print packet summary
        System.out.println("Packet: "+Packet+" with capacity: "+Packet.capacity());
        System.out.println(driver.byteArrayToString(packet));

// for test
				System.out.println("bytesToIntTool: " + EncodingTools.bytesToIntTool(0, packet, driver));
				System.out.println((EncodingTools.bytesToIntTool(0, packet, driver)& 0xf));
				System.out.println(EncodingTools.bytesToIntTool(14, packet, driver));
				System.out.println(((EncodingTools.bytesToIntTool(14, packet, driver)>> 4 )& 0xf));
				System.out.println((EncodingTools.bytesToIntTool(14, packet, driver)& 0xf));

				System.out.println("hexJoinTool: " + EncodingTools.hexJoinTool(0, 1, packet, driver, 0));
				System.out.println(EncodingTools.hexJoinTool(1, 1, packet, driver, 0));
				System.out.println(EncodingTools.hexJoinTool(14, 2, packet, driver, 0));

				System.out.println("mac: " + EncodingTools.hexJoinTool(0, 6, packet, driver, 1));

				System.out.println("hex 2 decimal: " + EncodingTools.hexToDecimalTool(EncodingTools.hexJoinTool(12, 2, packet, driver, 0)));
				System.out.println("bytes 2 decimal: " + EncodingTools.bytesToIntTool(12, 2, packet, driver));


				System.out.println("drivertool: " + driver.byteToHex(packet[0]));
				System.out.println(driver.byteToHex(packet[1]));
				System.out.println(driver.byteToHex(packet[14]));

				//Send the same packet now (change headers)
//				System.out.println("Dst: " + driver.byteToHex(packet[0]) + "." + driver.byteToHex(packet[1] + "." + driver.byteToHex(packet[2]) + "." + driver.byteToHex(packet[3])));

        for (int i=0; i< 6; i++) packet[i]=1; //Destination
        for (int i=0; i< 6; i++) packet[i+6]=2; //Source
        packet[12]=9; packet[13]=10; //Make up a type
	//Send packet
        if (!driver.sendPacket(packet)) System.out.println("Error sending packet!");


        }



}
