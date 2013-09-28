package web.register;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import util.QQUtil;

/**
 * 获取验证码
 * @author hg
 *
 */
public class VerificationCode {

	public final static HttpClient client = new DefaultHttpClient();
	
	
	public static void main(String[] args){
		
		HttpGet get = new HttpGet(QQUtil.imageUrl);
		HttpResponse response = null;
		HttpEntity entity = null;
		File destFile = null;

		for(int i=0;i<QQUtil.imageAmount;i++){
			
			try {
				response = client.execute(get);
				
				entity = response.getEntity();
				
				//写文件
				destFile = new File(QQUtil.imagePath+i+QQUtil.imageSuffix);

				FileUtils.writeByteArrayToFile(destFile, EntityUtils.toByteArray(entity));
				
				EntityUtils.consume(entity);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

		}
		
		
	}

	
	/**
	 * 实现图片下载的另外一种方法
	 * @param httpUrl
	 * @param filePath
	 */
//	for(int i=0;i<QQUtil.imageAmount;i++){
//	getVerificationCode(QQUtil.imageUrl,QQUtil.imagePath+i+QQUtil.imageSuffix);
//}
	public static void getVerificationCode(String httpUrl,String filePath) {
		URL url;
		BufferedInputStream in;
		FileOutputStream file;
		try {
		   System.out.println("取网络图片");
//		   String fileName = httpUrl.substring(httpUrl.lastIndexOf("/"));
		   url = new URL(httpUrl);
		   in = new BufferedInputStream(url.openStream());
		   file = new FileOutputStream(new File(filePath));
		   int t;
		   while ((t = in.read()) != -1) {
		    file.write(t);
		   }
		   file.flush();
		   file.close();
		   in.close();
		   System.out.println("图片获取成功");
		} catch (MalformedURLException e) {
		   e.printStackTrace();
		} catch (FileNotFoundException e) {
		   e.printStackTrace();
		} catch (IOException e) {
		   e.printStackTrace();
		}
		}
}
