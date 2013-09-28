

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Weibo {

	private final static HttpClient client = new DefaultHttpClient();

	private final static String indexUrl = "http://t.qq.com/l316937601/profile?pgv_ref=im.minicard.title&ptlang=2052";
	public static void main(String[] args) throws IOException {

		getWeb(indexUrl);

	}
	
	
	static void getWeb(String url) throws IOException {
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3");
		HttpResponse response = client.execute(post);		
		HttpEntity entity = response.getEntity();
		
		//Ð´ÎÄ¼þ
		File destFile = new File("huiwq1990.txt");
		FileUtils.writeStringToFile(destFile, EntityUtils.toString(entity));
		
		EntityUtils.consume(entity);
	}
	
}
