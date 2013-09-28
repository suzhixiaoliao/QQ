package util;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Security {
public static final String HEXSTRING = "0123456789ABCDEF";

    public static String md5(String originalText) throws Exception {
        byte buf[] = originalText.getBytes("ISO-8859-1");
        StringBuffer hexString = new StringBuffer();
        String result = "";
        String digit = "";

        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(buf);

            byte[] digest = algorithm.digest();

            for (int i = 0; i < digest.length; i++) {
                digit = Integer.toHexString(0xFF & digest[i]);

                if (digit.length() == 1) {
                    digit = "0" + digit;
                }

                hexString.append(digit);
            }

            result = hexString.toString();
        } catch (Exception ex) {
            result = "";
        }

        return result.toUpperCase();
    }

    public static String hexchar2bin(String md5str) throws UnsupportedEncodingException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(md5str.length() / 2);

        for (int i = 0; i < md5str.length(); i = i + 2)
        {
            baos.write((HEXSTRING.indexOf(md5str.charAt(i)) << 4 |
                    HEXSTRING.indexOf(md5str.charAt(i + 1))));
        }

        return new String(baos.toByteArray(), "ISO-8859-1");
    }

    /**
     *
     * @param qq http://check.ptlogin2.qq.com/check?uin={0}&appid=15000101&r={1} ���صĵ�����ֵ
     * @param password QQ����
     * @param verifycode ��֤��
     * @return ���ܺ������
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static String GetPassword(String qq,String password, String verifycode) throws Exception{

        String P = hexchar2bin(md5(password));
        String U = md5(P + hexchar2bin(qq.replace("\\x", "").toUpperCase()));
        String V = md5(U + verifycode.toUpperCase());

        return V;
    }
        
        public static void main(String[] args) throws Exception {
        	System.out.println("\\x00\\x00\\x00\\x00\\x11\\x54\\x47\\xb8");
            System.out.println(GetPassword("\\x00\\x00\\x00\\x00\\x11\\x54\\x47\\xb8","huiqingwang","!KES"));           //System.out.println(md5("\\x00\\x00\\x00\\x00\\x90\\xbe\\x76\\xce"));
       }
}