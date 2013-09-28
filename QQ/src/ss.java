import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class ss {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, ParseException {
		
		
		
		
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date1 =df.parse("2013-06-07 10:09:36");
//		System.out.println(msg);
		System.out.println(date1.getTime() );
		
		System.out.println(System.currentTimeMillis());

	
		
		// TODO Auto-generated method stub
		//��ȡCookie����Ϣ
		HttpClient httpclientme = new DefaultHttpClient();
		// ����һ������Cookie�洢��ʵ��
		CookieStore cookieStore = new BasicCookieStore();
		//����һ��������������Ϣ
		HttpContext localContext = new BasicHttpContext();
		//�ڱ����������а�һ�����ش洢
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		//���������·��
		HttpGet httpget1 = new HttpGet("http://web.qq.com/"); 
		//���ݱ��ص�http�����ĸ�������
		HttpResponse response = httpclientme.execute(httpget1, localContext);
		//��ȡ������Ϣ
		HttpEntity entity = response.getEntity();
		System.out.println(response.getStatusLine());
		if (entity != null) {
		    System.out.println("Response content length: " + entity.getContentLength());
		}

		//��ȡcookie�еĸ�����Ϣ
		List<Cookie> cookies = cookieStore.getCookies();
		for (int i = 0; i < cookies.size(); i++) {
		    System.out.println("Local cookie: " + cookies.get(i));
		}
		//��ȡ��Ϣͷ����Ϣ
		Header[] headers = response.getAllHeaders();
		for (int i = 0; i<headers.length; i++) {
		    System.out.println(headers[i]);
		}
	}

}
