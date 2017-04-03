import java.util.*;
import java.nio.ByteBuffer;

public class ProjectPackety{
    public static void main(String[] args) throws Exception{
        ArrayList<String> myArgs = new ArrayList<>();
        for (String w : args){
            myArgs.add(w);
        }
        int size = myArgs.size();
        SimplePacketDriver driver=new SimplePacketDriver();
        PacketParser pp = new PacketParser();
        String[] adapters=driver.getAdapterNames();
        byte[] packet;

        int cF, rF, oF, tF, hF, srcF, dstF, sordF, sanddF, sportF, dportF;
        cF=rF=oF=tF=hF=srcF=dstF=sordF=sanddF=sportF=dportF=0;
        int count=1;
        String readfilename, savefilename, type, saddress, daddress, sportA, sportB, dportA, dportB;

        if(myArgs.contains("-c")){
            if(myArgs.indexOf("-c")<size){
                cF=1;
                count = Integer.parseInt(myArgs.get(myArgs.indexOf("-c")+1));
                for(int i=0;i<count;i++){
                    packet=driver.readPacket();
                    System.out.println(pp.parse(packet));
                }
            }else{
                System.out.println("Count number required after -c.");
                return;
            }
        }

        if(myArgs.contains("-r")){
            if(myArgs.indexOf("-r")<size){
                rF=1;
                readfilename = myArgs.get(myArgs.indexOf("-r")+1);

            }else{
                System.out.println("Filename required to read.");
                return;
            }
        }

        if(myArgs.contains("-o")){
            if(myArgs.indexOf("-o")<size){
                oF=1;
                savefilename = myArgs.get(myArgs.indexOf("-o")+1);

            }else{
                System.out.println("Filename required to output.");
                return;
            }
        }

        if(myArgs.contains("-t")){
            if(myArgs.indexOf("-t")<size){
                tF=1;
                type = myArgs.get(myArgs.indexOf("-t")+1);
                if(myArgs.contains("-h"))
                    hF=1;


            }else{
                System.out.println("Type required after -t");
                return;
            }
        }

        if(myArgs.contains("-src")){
            if(myArgs.indexOf("-src")<size){
                srcF=1;
                saddress = myArgs.get(myArgs.indexOf("-src")+1);

            }else{
                System.out.println("Source Address is required after -src");
                return;
            }
        }

        if(myArgs.contains("-dst")){
            if(myArgs.indexOf("-dst")<size){
                dstF=1;
                daddress = myArgs.get(myArgs.indexOf("-dst")+1);

            }else{
                System.out.println("Destination Address is required after -dst");
                return;
            }
        }

        if(myArgs.contains("-sord")){
            if(myArgs.indexOf("-sord")<(size-1)){
                sordF=1;
                saddress = myArgs.get(myArgs.indexOf("-sord")+1);
                daddress = myArgs.get(myArgs.indexOf("-sord")+2);

            }else{
                System.out.println("Addresses are required after -sord");
                return;
            }

        }

        if(myArgs.contains("-sandd")){
            if(myArgs.indexOf("-sandd")<(size-1)){
                sanddF=1;
                saddress = myArgs.get(myArgs.indexOf("-sandd")+1);
                daddress = myArgs.get(myArgs.indexOf("-sandd")+2);

            }else{
                System.out.println("Addresses are required after -sandd");
                return;
            }

        }

        if(myArgs.contains("-sport")){
            if(myArgs.indexOf("-sport")<(size-1)){
                sportF=1;
                sportA = myArgs.get(myArgs.indexOf("-sport")+1);
                sportB = myArgs.get(myArgs.indexOf("-sport")+2);

            }else{
                System.out.println("Source port number is required after -sport");
                return;
            }

        }

        if(myArgs.contains("-dport")){
            if(myArgs.indexOf("-dport")<(size-1)){
                dportF=1;
                dportA = myArgs.get(myArgs.indexOf("-dport")+1);
                dportB = myArgs.get(myArgs.indexOf("-dport")+2);

            }else{
                System.out.println("Destination port number is required after -dport");
                return;
            }

        }

        if(cF+rF+tF+oF+hF+srcF+dstF+sordF+sanddF+sportF+dportF == 0){
            for (int i=0; i< adapters.length; i++)
                System.out.println("Device name in Java ="+adapters[i]);

            if (driver.openAdapter(adapters[0]))
                System.out.println("Adapter is open: "+adapters[0]);


            while(true){
                packet = driver.readPacket();

                EncodingTools ET = new EncodingTools();
                //System.out.println(ET.byteArrayToString(packet));
                System.out.println(driver.byteArrayToString(packet));

                EthernetPacket tryE = new EthernetPacket(packet);
                if (tryE.getProtocol()== "86DD"){
                    System.out.println("\n This is a IPv6 Packet. \n");
                }else if (Integer.parseInt(tryE.getProtocol()) == 800){
                    IPPacket tryIP = new IPPacket(packet);
                    if (Integer.parseInt(tryIP.getProtocol()) == 11){
                        UDPPacket tryUDP = new UDPPacket(packet);
                        System.out.println(tryUDP.parse());
                    }else if(Integer.parseInt(tryIP.getProtocol())==1){
                        ICMPPacket tryICMP = new ICMPPacket(packet);
                        System.out.println(tryICMP.parse());
                    }else if(Integer.parseInt(tryIP.getProtocol())==6){
                        TCPPacket tryTCP = new TCPPacket(packet);
                        System.out.println(tryTCP.parse());
                    }else{
                        System.out.println("Unrecognized packet: ");
                        System.out.println(tryIP.parse());
                    }
                }else if(Integer.parseInt(tryE.getProtocol()) == 806){
                    ARPPacket tryARP = new ARPPacket(packet);
                    System.out.println(tryARP.parse());
                }else{

                    System.out.println("\n This is an unrecognized ethernet packet: ");
                    System.out.println(tryE.parse());
                }
            }
        }else{


            System.out.println("its just a game");
            return;

        }

    }
}
