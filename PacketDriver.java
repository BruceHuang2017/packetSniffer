public class PacketDriver(){

	SimplePacketDriver driver = new SimplePacketDriver();
	String[] adapter = driver.getAdapterNames[];
	byte [] packet = driver.readPacket();
	ByteBuffer Packet = ByteBuffer.wrap(packet);


	public byte [] getByteArrayPacket(){
		return packet;
	}

	public String[] getStringPacket(){
		return deiver.byteArrayToString(packet);
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



}
