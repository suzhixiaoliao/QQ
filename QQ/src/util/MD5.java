package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public String str;

	public String md5s(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
			System.out.println("result: " + buf.toString());// 32位的加密
			System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
			return str;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

//	private String hexchar2bin(String sPassword) {
//        String str1="";
//        for (int i = 0; i < sPassword.length(); i = i + 2) {
//            str1 += Convert.ToChar(Convert.ToInt32(sPassword.Substring(i, 2),16));
//        }
//        return str1;
//    }
	public static String hexchar2bin(String str)
    {
        String s = "";
        for (int i = 0; i < str.length(); i = i + 2){
            s += "\\x" + str.substring(i, 2);
        }
        return s;
    }
	
	
	public static void main(String agrs[]) {
		MD5 md5 = new MD5();
		String str = md5.md5s("huiqingwang");
		
//		ptu_in = "\x00\x00\x00\x00\x11\x54\x47\xb8";
//		str  = 
		System.out.println(hexchar2bin(str));
//		md51.md5s("4");// 加密4
	}

}