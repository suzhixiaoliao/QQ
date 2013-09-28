import java.io.ByteArrayOutputStream;


public class Pass {
	private static String hexString = "0123456789ABCDEF"; //此处不可随意改动
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "022f2f00cd4dbc3b5b5bbe02000000010101000065a491b0d0d53893be36bdca35860e571976c80bdc3efc138dad8b485d8e2d1567d18fccdbab9d9c5d12009d19401d565badfc750efaff102207e472432ccf40b955c088180012ad2a060e0dcae758a458010053c0d233862abbecc6a4255351df1c4d7767a9d232b1216cf7cf321b5144a7b4b5be411368e3e603";
		s= "022f2f00cd4dca3b5b5bbe02000100010101000065a49d3867923a4928c590e6f1e0ac51ded3fdbf5946ee8def8f1144f3bc2c255c299f13e74db84d9280361c27b5a12a5cbcd5717ab1f792788c08befb72384e6210b2cd219e02ea166fbba9e135784f34ba7e08490a13bd7272846905e67bb72aa2c9c4f20e4fec7588c71a1dadb7bb2d8d86f70c3f282e001c42ae7d0d17d7b25d03";
//		System.out.println(decode(s));
		for(int i = 0; i< s.length();i = i+2){
			System.out.print("0x"+s.substring(i,i+2)+",");
		}
	}

	
	
	/* 将16进制数字解码成字符串 */
	public static String decode(String bytes) {
	   ByteArrayOutputStream baos = new ByteArrayOutputStream(
	     bytes.length() / 2);
	   // 将每2位16进制整数组装成一个字节
	   for (int i = 0; i < bytes.length(); i += 2)
	    baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
	      .indexOf(bytes.charAt(i + 1))));
	   return new String(baos.toByteArray());
	}
}
