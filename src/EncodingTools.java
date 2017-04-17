
public interface EncodingTools{

    int _ethernetHeaderLen = 14;
    int _ipHeaderLen = 20;
    int _arpHeaderLen = 8;
    int _icmpHeaderLen = 8;
    int _tcpHeaderLen = 20;
    int _udpHeaderLen = 8;

  public static int bytesToIntTool(int offset, byte [] byteData, SimplePacketDriver driver){
    return Integer.parseInt(driver.byteToHex(byteData[offset]), 16);
  }

  public static int bytesToIntTool(int offset, int length, byte [] byteData, SimplePacketDriver driver){
    String hexBuffer = hexJoinTool(offset, length, byteData, driver, 0);
    return hexToDecimalTool(hexBuffer);
  }

  public static int hexToDecimalTool(String hex){
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
  public static String hexJoinTool(int offset, int length, byte [] byteData, SimplePacketDriver driver, int flag){
    int value=0;
    StringBuffer sb = new StringBuffer();
    if (flag == 0) {

      // join hex strings
      for(int i=0; i<length; i++)
        sb.append(driver.byteToHex(byteData[offset + i]));
      return sb.toString();

    }else if(flag == 1) {

      // join hex with ':' for mac address
      for(int i=0; i<length; i++){
        sb.append(driver.byteToHex(byteData[offset + i]));
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
        sb.append(driver.byteToHex(byteData[offset + i]));
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

  public static byte [] dataTool(int offset, int length, byte [] byteData){
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

  public static byte [] headerTool(int offset, int length, byte [] byteData){
    byte [] header = new byte [length];
    System.arraycopy(byteData, offset, header, 0, length);
    return header;
  }

  public static byte [] packetTool(int offset, byte [] byteData){
    int length = byteData.length - offset;
    byte [] packet = new byte [length];
    System.arraycopy(byteData, offset, packet, 0, length);
    return packet;
  }



}
