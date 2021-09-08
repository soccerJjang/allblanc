package kr.co.allblanc.common.util;

public final class Base64Utils {
    /**
    * BASE64 Encoder
    * @param str
    * @return
    */
   public static String base64Encode(String str)  throws java.io.IOException {
       sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
       byte[] strByte = str.getBytes();
       String result = encoder.encode(strByte);
       return result ;
   }

   /**
    * BASE64 Decoder
    * @param str
    * @return
    */
   public static String base64Decode(String str)  throws java.io.IOException {
       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
       byte[] strByte = decoder.decodeBuffer(str);
       String result = new String(strByte);
       return result ;
   }
}
