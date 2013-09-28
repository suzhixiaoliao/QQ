package util;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


public class QQUtil {
	
	public final static int imageAmount = 20;
	public final static String processImagePath = "processImage/";
	public final static String imagePath = "image/";
	public final static String imageSuffix = ".jpg";
	
	public final static String imageUrl = "http://captcha.qq.com/getimage?aid=1007901&r=0.11348894378170371";

	
	public final static String chatReferer ="http://d.web2.qq.com/proxy.html?v=20110331002&callback=1&id=2";
	public final static String chatQunReferer = "http://d.web2.qq.com/proxy.html?v=20110331002&callback=1&id=2";
	public final static String chatQunUrl = "http://d.web2.qq.com/channel/send_qun_msg2";
	
	public final static String pollUrl =  "http://d.web2.qq.com/channel/poll2";
	public final static String pollReferer = "http://d.web2.qq.com/proxy.html?v=20110331002&callback=1&id=2";
}
