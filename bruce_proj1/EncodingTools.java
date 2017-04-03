import java.nio.ByteBuffer;

public class EncodingTools{

    public final int bytesToIntTool(int offset, byte [] byteData){
        return bytesToIntTool(offset, 1, byteData);
    }

    public final int bytesToIntTool(int offset, int length, byte [] byteData){
    	int value = 0;
    	for(int i=0; i<length; i++)
	        value |= ((byteData[offset + length - i - 1] & 0xff) << 8 * i);
    	return value;
    }

    public final int hexToDecimalTool(String hex){
        String dic = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        char[] a = hex.toCharArray();
        int value =0;
        for (int i=0; i<hex.length(); i++) {
            int d=dic.indexOf(a[i]);
            value = value *16 +d;
        }
        return value;
    }

    /**
     @param flag ==0: directly join hex value into one string.
     ==1: join mac address
     ==2: join ip address
     */
    public final String hexJoinTool(int offset, int length, byte [] byteData, int flag){
        int value=0;
//        ByteBuffer bb = new ByteBuffer();
//        byteData = bb.array();
        StringBuffer sb = new StringBuffer();
        if (flag == 0) {
            // join hex strings
            for(int i=0; i<length; i++)
                sb.append(byteToHex(byteData[offset + i]));
            return sb.toString();

        }else if(flag == 1) {

            // join hex with ':' for mac address
            for(int i=0; i<length; i++){
                sb.append(byteToHex(byteData[offset + i]));
                sb.append(":");
            }

            // delete the last ':'
            sb.reverse();
            sb.delete(0,1);
            sb.reverse();
            return sb.toString();
        }else if(flag == 2){

            // join hex with '.' for ip address,
            for(int i=0; i<length; i++){
                sb.append(0xff & (byteData[offset + i]));
		sb.append(".");
            }

            // delete the last '.'
            sb.reverse();
            sb.delete(0,1);
            sb.reverse();
            return sb.toString();
        }else{
            // error report should be done.
            return null;
        }
    }

    /**
     @param offset location of the start of the packet
     @param length header of the packet.
     @param byteData initial bytes array packet.
     @param e error flag = 1 when data length is less than 0.
     */

    public final byte [] dataTool(int offset, int length, byte [] byteData){

        int dataL = byteData.length - offset - length;
        if (dataL == 0) return new byte[dataL];
        if (dataL < 0) {
            byte [] e = new byte[1];
            e[0] = 1;
            return e;
        }
        byte [] data = new byte[dataL];
        System.arraycopy(byteData, offset, data, 0, dataL);
        return data;
    }

    public final byte [] headerTool(int offset, int length, byte [] byteData){
        byte [] header = new byte [length];
        System.arraycopy(byteData, offset, header, 0, length);
        return header;
    }

    public final byte [] packetTool(int offset, byte [] byteData){
        int length = byteData.length - offset;
        byte [] packet = new byte [length];
        System.arraycopy(byteData, offset, packet, 0, length);
        return packet;
    }


    private final char[] hexDigit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final char[] asciiChar = new char[]{' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'};

    private final String byteToHex(byte var1) {
        char[] var2 = new char[]{hexDigit[var1 >> 4 & 15], hexDigit[var1 & 15]};
        return new String(var2);
    }

    public String byteArrayToString(byte[] var1) {
        int var4 = 0;
        int var5 = var1.length / 16 + 1;
        String var6 = new String();

        for(int var7 = 0; var7 < var5; ++var7) {
            String var8 = new String();
            String var9 = new String();

            byte var2;
            int var11;
            for(var11 = 0; var11 < 8 && var4 + 1 <= var1.length; ++var11) {
                var2 = var1[var4++];
                var8 = var8 + byteToHex(var2) + " ";
            }

            var11 = 49 - var8.length() - var9.length();
            var6 = var6 + var8 + var9 ;

        }

        return var6;
    }



}
