package util;



import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ss {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws  
	 */
	public static void main(String[] args) throws  IOException {
		
		
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);  
		HttpHost httpHost = new HttpHost("web.qq.com");  
		HttpGet httpGet = new HttpGet("http://web.qq.com/");
        HttpResponse response = httpClient.execute(httpHost,httpGet);  
		
//		HttpPost httpPost = new HttpPost("http://web.qq.com/");
//		HttpResponse response = httpClient.execute(httpPost);
//        CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
//        Cookie[] cookies = cookiespec.match("ÓòÃû", 80/*¶Ë¿Ú*/, "/" , false , client.getState().getCookies());
        
		List<Cookie> cookies = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();  
        if (cookies.isEmpty()) {  
            System.out.println("None");  
        } else {  
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("- " + cookies.get(i).toString());
              
            }  
        }
     
      

	}

}
