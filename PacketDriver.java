import java.nio.ByteBuffer;


// fetch a packet : byteData
public class PacketDriver{

	public static Packet identifyPacket(byte [] byteData) {

		SimplePacketDriver driver = new SimplePacketDriver();
		String[] adapter = driver.getAdapterNames[];
		byte [] byteData = driver.readPacket();
		ByteBuffer Packet = ByteBuffer.wrap(byteData);

		EthernetPacket ether = new EthernetPacket(14, byteData);

		public byte [] getByteArrayPacket(){
			return byteData;
		}

		public String[] getStringPacket(){
			return deiver.byteArrayToString(byteData);
		}

		public ByteBuffer getBufferPacket(){
			return Packet;
		}

		public int getPacketCapacity(){
			return Packet.capacity();
		}

		public int getNumberOfAdapters(){
	        return adapters.length;
		}

		public boolean checkDefaultAdapter(){
			return driver.openAdapter(adapters[0]);
		}

		int ethProtocol = ether.getType();

		switch(ethProtocol){
			case 0x0806:
				return new ARPPacket(? byteData);
			case 0x0806:
				IPPacket ip = new IPPacket(?, byteData);
				switch (ip.getIPProtocol()) {
					case 0x01:
						return new ICMPPacket(?, byteData);
					case 0x06:
						return new TCPPacket(?, byteData);
					case 0x11:
						return new UDPPacket(?, byteData);
					default:
						System.out.println("unrecogenized ip transmition protocol.");
						return;
				}

			default:
				system.out.println("unrecogenized eth transmition protocol.");
				return;

		}
 }



}
