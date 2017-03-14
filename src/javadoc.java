
JAVA = \
  Packet \
    EthernetPacket \
      ARPPacket \
      IPPacket \
        ICMPPacket \
        TCPPacket \
        UDPPacket \
  PacketHunter \
  ProjectPackety \
  EncodingTools \


  Packet:
    define and initialize methods&fields for all subclasses.
    constructor:
      null
    method - type - modifier - details: ----------(will be overwritten)
      getData() - byte [] - public - fetch data payload of a packet
      getHeader() - byte [] - public  - fetch header of a packet
      getPacket() - byte [] - public  - whole packet in bytes array
    field - type - modifier:
      driver - SimplePacketDriver - public

  EthernetPacket:
    constructor:
      EthernetPacket(byteData)
    method - type - modifier - details:
      getData() - byte [] - public - get eth payload
      getHeader() - byte [] - public - get eth header, default 14
      getPacket() - byte [] - public - get eth packet, offset = 0
      getProtocol() - int - public - get eth type, ip=800, arp=806
//      getEthProtocol() - return getProtocol
      getSrcMacAddress() - String - public - get mac address
      getDstMacAddress() - String - public - get mac address
    field - type - modifier - details:
      data - byte [] - no - eth payload
      header - byte [] - no - eth header
      packet - byte [] - no - whole eth packet

  ARPPacket:
    constructor:
      ARPPacket(byteData)
    method - type - modifier - details:
      getData() - byte [] - public - get arp data, default 0
      getHeader() - byte [] - public - get arp header, default 28
      getPacket() - byte [] - public - get arp packet, offset = 0
    field - type - modifier - details:
      data - byte [] - no - arp data
      header - byte [] - no - arp header
      packet - byte [] - no arp packet

  IPPacket:
    constructor:
      IPPacket(byteData)
    method - type - modifier - details:
      getData() - byte [] - public - get ip data, default 0
      getHeader() - byte [] - public - get ip header, default 20, use checkOption to resize header if necessay
      getPacket() - byte [] - public - get ip packet, offset = 14
      getProtocol() - int - public - get ip protocol, icmp=0x01 , tcp=0x06 , udp=0x11, others.
      getIPProtocol(?) - return getProtocol
      getVersion() - int - public - default IPv4
      getSrcIPAddress() - String - public - source address
      getDstIPAddress() - String - public - destination address
      checkOption() - boolean - public - check internet header length IHL>5, true
      getOptionData() - byte [] - public - use checkOption, return data or byte [0]
      getRealHdrLength() - byte [] - public - use checkOption ture, return real header length.
    field - type - modifier - details:
      data - byte [] - no - ip data
      header - byte [] - no - ip header
      packet - byte [] - no - ip packet

  ICMPPacket:
    constructor:
      ICMPPacket(byteData)
    method - type - modifier - details:
      getData() - byte [] - public - get icmp data, default 4
      getHeader() - byte [] - public - get icmp header, default 4
      getPacket() - byte [] public - get icmp packet
    field - type - modifier - details:
      data - byte [] - no - icmp data
      header - byte [] - no - icmp header
      packet - byte [] - no - icmp packet

  TCPPacket:
    constructor:
      TCPPacket(byteData)
    method - type - modifier - details:
      getData() - byte [] - public - get tcp data
      getHeader() - byte [] - public - get tcp header, default 20, use checkOption to resize header length if necessary
      getPacket() - byte [] - public - get tcp packet
      getSrcPort() - int - public - get source port number
      getDstPort() - int - public - get destination port number
      checkOption() - boolean - public - check data offset >5, true
      getOptionData() - byte [] - public - use checkOption, return data or byte [0]
      getRealHdrLength() - int - public - use checkOption true, return real header length
    field - type - modifier - details:
      data - byte [] - no - tcp data
      header - byte [] - no - tcp header
      packet - byte [] - no - tcp packet

  UDPPacket:
    constructor:
      UDPPacket(byte [] byteData)
    method - type - modifier - details:
      getData() - byte [] - public - get udp data
      getHeader() - byte [] - public - get udp header, default 8
      getPacket() - byte [] - public - get udp packet
      getSrcPort() - int - public - get source port number, use tools
      getDstPort() - int - public - get destination port number, use tools
    field - type - modifier - details:
      data - byte [] - no - udp data
      header - byte [] - no - udp header
      packet - byte [] - no - udp packet

  PacketHunter:
    constructor:
      PacketHunter()
    method - type - modifier - details:
      getPacketType() - String - public - eth, ip, tcp, udp, icmp, arp
//      huntStart() - void - public - initialize driver
//      huntResult() - void - public - printout

    field - type - modifier:
      driver - SimplePacketDriver - no
      adapter - String [] - no
      bytePacket - byte [] - no
      bufferPacket - ByteBuffer - no
//    stringPacket - String [] - no
      ethProtocol - int - no - switch, default eth packet, check eth type, sort packet and give results to getpacketype method.
      ipProtocol - int - no - switch, default ippacket, which means unknow protocol.

  ProjectPackety: (import packets, err check, exception, commandline, save, read files, main function)
    constructor:
      null
    method - type - modifier - details:
      specifiedPacket(String, byte[] byteData) - byte[] - no - when -t commandline is received, use this method to return required type of packets in bytes. if statements is used to specifed packet type. eth, arp, ip, udp, tcp, icmp, others.
      countPacket(cnt) - void - no - keep receiving packet, print to the screen, till count ends.

    field - type - modifier:
      ?


  EncodingTools:
    constructor:
      null
    method - type - modifier - details:
      bytesToIntTool(int, byte[], SimplePacketDriver) - int - public  - return a byte -> hex -> integer
      bytesToIntTool(int, int, byte[], SimplePacketDriver) - int - public  - return bytes array -> hex -> converted to decimal integer.
      hexJoinTool(int, int, byte[], SimplePacketDriver, int flag) - String - public - return ,bytes -> hex string representing the packet
              flag==0: directly join hex value into one string.
                  ==1: join mac address
                  ==2: join ip address

      dataTool(int offset, int length, bytes) - byte [] - public - get data from byte packet.
      headerTool() - same as dataTool()
      packetTool() - same as dataTool()
//      addressTool(int offset, int length, bytes) - String - public - return string of a hardware address.
    field - type - modifier:
      driver - SimplePacketDriver - no
      _ethernetHeaderLen - int - no
      _arpHeaderLen - int - no
      _ipHeaderLen - int - no
      _icmpHeaderLen - int - no
      _tcpHeaderLen - int - no
      _udpHeaderLen - int - no







end
youpeng bruce huang
1458421
