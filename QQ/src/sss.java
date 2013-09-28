import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class sss {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		
		
		
		DefaultHttpClient  httpclient = new DefaultHttpClient();
		CookieStore cookieStore = new BasicCookieStore();
		HttpClientParams.setCookiePolicy(httpclient.getParams(), CookiePolicy.NETSCAPE);  
		
		HttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		
		HttpPost httpget = new HttpPost("http://web.qq.com/"); 
		HttpResponse response = httpclient.execute(httpget, localContext);

		CookieOrigin cookieOrigin = (CookieOrigin) localContext.getAttribute(
		        ClientContext.COOKIE_ORIGIN);
		System.out.println("Cookie origin: " + cookieOrigin);
		CookieSpec cookieSpec = (CookieSpec) localContext.getAttribute(
		        ClientContext.COOKIE_SPEC);
		System.out.println("Cookie spec used: " + cookieSpec);
//		cookieSpec.
//		cookieOrigin.
//		 Cookie[] cookies = cookieSpec.match("web.qq.com", 80, "/" , false , cookieOrigin);
//		cookieStore.
//			List<Cookie> cookies = cookieStore.getCookies();  
		List<Cookie> cookies = httpclient.getCookieStore().getCookies();  
	        if (cookies.isEmpty()) {  
	            System.out.println("None");  
	        } else {  
	            for (int i = 0; i < cookies.size(); i++) {
	                System.out.println("- " + cookies.get(i).toString());
	              
	            }  
	        }
		/*
		HttpClient httpclient = new DefaultHttpClient();
		// Create a local instance of cookie store
		CookieStore cookieStore = new BasicCookieStore();
		
		
		
		
		// Create local HTTP context
		HttpContext localContext = new BasicHttpContext();
		// Bind custom cookie store to the local context
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		HttpGet httpget = new HttpGet("http://web.qq.com/");
		// Pass local context as a parameter
		HttpResponse response = httpclient.execute(httpget, localContext);

		// 获取cookie中的各种信息
		List<Cookie> cookies = cookieStore.getCookies();
		for (int i = 0; i < cookies.size(); i++) {
			System.out.println("Local cookie: " + cookies.get(i));
		}*/
	}
}
