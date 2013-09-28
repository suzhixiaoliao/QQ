package login;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import util.Constant;
import util.JsonParse;
import util.MD5Security;
import util.QQUtil;
import util.URLEncode;

public class PreLogin {

	private final static HttpClient client = new DefaultHttpClient();

	private final static String preLoginVerCodeUrl = "https://ssl.captcha.qq.com/getimage?aid=1003903&r=0.9517937599486047&uin=290736056";

	public String pgv_pvid = "1532985858";
	public String ssid = "s1064040546";
	public String ptisp = "edu";

	public String verifycode = "";
	public String pt2gguin = "";
	public String login_sig = "BGO4ItiiN9Xs78uuihsEIiJbFdY8iBNnOLJQQRwBx6e12Du4PweqEYYcO-7JXxPO";

	public String uikey = "";

	// konw how to get

	// after preLogin
	public int ptuinStatus = 0;
	public String ptuin = "";
	public String vercode_default = "";

	// after get vercode
	public String verifysession = "";

	// from first login
	public String superkey = "";
	public String  superuin= "";
	
	public String skey = "";
	public String RK = "";
	public String ptuserinfo = "";
	public String ptcz = "";
	public String ptwebqq = "";
	
	//
	public String clientid = "82814629";
	public String psessionid	= "";
	public String vfwebqq = "";
	public String msg_id = "82345679";

	public static void main(String[] args) throws Exception {
		PreLogin login = new PreLogin();
		
		login.preLogin();
		
		System.out.println();
		if(login.ptuinStatus == 1){
			System.out.println("需要获取验证码");
			login.verifyCode(preLoginVerCodeUrl);
		}else{
			
			login.verifycode = login.vercode_default;
		}
		

		login.login();
		Thread.sleep(100);
		login.login2();
		
//		Thread.sleep(2000);
//		login.poll();
		Thread.sleep(100);
		login.poll();
		Thread.sleep(100);
		login.chat();
		
		Thread.sleep(100);
		login.poll();
//		login.chatQun();
//		login.chat();
		Thread.sleep(10000);
	}

	public void uikey() {
		String url = "https://ui.ptlogin2.qq.com/cgi-bin/login?target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20130417001";

	}

	
	/**
	 * 检查是否需要验证码
	 * 
	 * 返回数据有两种情况
		1.ptui_checkVC('1','2c18e75d3acac5280a8c057e1d07e1ff66e349fd6974a09e','\x00\x00\x00\x00\x59\x74\x80\x66');
		2.ptui_checkVC('0','!TXP','\x00\x00\x00\x00\x59\x74\x80\x66');
		其中 ‘1’代表需要验证码，‘0’代表不需要验证码  ('!TXP')就是默认的验证码；
		'\x00\x00\x00\x00\x59\x74\x80\x66' 代表 ptui值
	 * 
	 * @throws IOException
	 */
	public void preLogin() throws IOException {
		String preLoginUrl = "https://ssl.ptlogin2.qq.com/check?uin="
				+ Constant.qq
				+ "&appid=1003903&js_ver=10029&js_type=0&"
				+ login_sig
				+ "&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html&r=0.7898640231757976";

		HttpPost post = new HttpPost(preLoginUrl);
		post.setHeader("User-Agent", Constant.userAgent);
//		post.setHeader("Connection", "Keep-Alive");
//		post.setHeader("Host", "ssl.captcha.qq.com");

		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		System.out.println(response.getHeaders("Set-Cookie")[1].getValue());
		ptisp = response.getHeaders("Set-Cookie")[1].getValue().split(";")[0]
				.substring("ptisp=".length());
		System.out.println("ptisp: " + ptisp);

		String entityStr = EntityUtils.toString(entity);

		
		String ptuinStatusStr = entityStr.split(",")[0].substring(1);
		ptuinStatusStr = ptuinStatusStr.substring(ptuinStatusStr.length() - 2, ptuinStatusStr.length() - 1);
		ptuinStatus = Integer.valueOf(ptuinStatusStr);
		
		vercode_default = entityStr.split(",")[1].substring(1);
		vercode_default = vercode_default.substring(0, vercode_default.length() - 1);
		
		ptuin = entityStr.split(",")[2].substring(1);
		ptuin = ptuin.substring(0, ptuin.length() - 3);

		System.out.println("ptui_checkVC:"+entityStr);
		System.out.println("ptuin: " +ptuin + "; ptuinStatusStr: "+ptuinStatusStr + "; vercode_default:"+vercode_default);
		
		EntityUtils.consume(entity);
		
		
	}

	public void verifyCode(String url) throws IOException {
		
//		HttpPost post = new HttpPost(url);
//		post.setHeader("User-Agent", Constant.userAgent);
//		String cookie = "pgv_pvid=" + pgv_pvid
//				+ "; pgv_info=pgvReferrer=&ssid=" + ssid + "; ptisp=" + ptisp;
//		System.out.println("verifycode cookie: " + cookie);
//		post.setHeader("Cookie", cookie);
//		post.setHeader("Host", "ssl.captcha.qq.com");
		
		HttpGet get = new HttpGet(url);
		get.setHeader("User-Agent", Constant.userAgent);
		
		
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();

		verifysession = response.getHeaders("Set-Cookie")[0].getValue();
		System.out.println("get verifysession:" + verifysession);
		System.out.println("verifysession: "
				+ verifysession.split(";")[0].substring("verifysession="
						.length()));

		// write picture
		InputStream in = entity.getContent();
		FileOutputStream file = new FileOutputStream(new File("code.bmp"));
		int t;
		while ((t = in.read()) != -1) {
			file.write(t);
		}
		in.close();
		file.close();

		EntityUtils.consume(entity);
		
		BufferedReader strInput = new BufferedReader(new InputStreamReader(
				System.in));
		verifycode = strInput.readLine();
	}

	public void login() throws Exception {

	

		String password = MD5Security.GetPassword(ptuin, Constant.password,
				verifycode);
		String loginUrl = "https://ssl.ptlogin2.qq.com/login?u="
				+ Constant.qq
				+ "&p="
				+ password
				+ "&"
				+ "verifycode="
				+ verifycode
				+ "&webqq_type=10&remember_uin=1&login2qq=1&aid=1003903&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&h=1&ptredirect=0&ptlang=2052&from_ui=1&pttype=1&dumy=&fp=loginerroralert&action=56-99-3809511&mibao_css=m_webqq&t=1&g=1&js_type=0&js_ver=10029&"
				+ "login_sig=" + login_sig;

		System.out.println("loginUrl:" + loginUrl);

		HttpGet get = new HttpGet(loginUrl);
		get.setHeader("User-Agent", Constant.userAgent);
//		post.setHeader("Connection", "Keep-Alive");
//		post.setHeader("Host", "ssl.ptlogin2.qq.com");
//		String cookie = "pgv_pvid=" + pgv_pvid
//				+ "; pgv_info=pgvReferrer=&ssid=" + ssid + "; ptisp=" + ptisp
//				+ "; verifysession=" + verifysession + "; uikey=" + uikey
//				+ "; chkuin=" + Constant.qq + "; confirmuin=" + Constant.qq;
//		post.setHeader("Cookie", cookie);

		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity, "utf-8"));

		System.out.println(response.getHeaders("Set-Cookie")[0].getValue());
		pt2gguin = response.getHeaders("Set-Cookie")[0].getValue().split(";")[0]
				.substring("pt2gguin=".length());
		System.out.println("pt2gguin:" + pt2gguin);

		superuin = response.getHeaders("Set-Cookie")[1].getValue();
		System.out.println(superuin);
		superuin = superuin.split(";")[0].substring("superuin=".length());
		System.out.println("superuin:" + superuin);

		superkey = response.getHeaders("Set-Cookie")[2].getValue();
		System.out.println(superkey);
		superkey = superkey.split(";")[0].substring("superkey=".length());
		System.out.println("superkey: " + superkey);

		skey = response.getHeaders("Set-Cookie")[4].getValue();
		System.out.println(skey);
		skey = skey.split(";")[0].substring("skey=".length());
		System.out.println("skey: " + skey);

		RK = response.getHeaders("Set-Cookie")[6].getValue();
		System.out.println(RK);
		RK = RK.split(";")[0].substring("RK=".length());
		System.out.println("RK: " + RK);

		ptuserinfo = response.getHeaders("Set-Cookie")[7].getValue();
		System.out.println(ptuserinfo);
		ptuserinfo = ptuserinfo.split(";")[0].substring("ptuserinfo=".length());
		System.out.println("ptuserinfo: " + ptuserinfo);

		ptcz = response.getHeaders("Set-Cookie")[8].getValue();
		System.out.println(ptcz);
		ptcz = ptcz.split(";")[0].substring("ptcz=".length());
		System.out.println("ptcz:" + ptcz);

		ptwebqq = response.getHeaders("Set-Cookie")[10].getValue();
		System.out.println(ptwebqq);
		ptwebqq = ptwebqq.split(";")[0].substring("ptwebqq=".length());
		System.out.println("ptwebqq:" + ptwebqq);

		EntityUtils.consume(entity);
	}
	
	
	

	public void login2() throws IOException {
		String url = "http://d.web2.qq.com/channel/login2";
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", Constant.userAgent);
		post.setHeader("Referer","http://d.web2.qq.com/proxy.html?v=20110331002&callback=1&id=2");
//		post.setHeader("Connection", "Keep-Alive");
//		post.setHeader("Host", "ssl.captcha.qq.com");
//
//		post.setHeader(
//				"Cookie",
//				"pgv_pvid=5451193082; ac=1,021,; pt2gguin=o0290736056; RK=fS4nMJpf+j; uin_cookie=995843006; euin_cookie=B97BE018614E37A798D0318984B48A3ED744B393F35A8EC8; ptui_loginuin=290736056; pgv_info=pgvReferrer=&ssid=s1203241255; ptisp=edu; "
//						+ "verifysession="
//						+ verifysession
//						+ "; "
//						+ "uin=o0290736056; skey=@FQ5YPklID; ptcz=d203da56ea94d8257826f3d679d37a685e238aaa6124b98e462c20ff674e296d; uikey=4162aca5f70a7df7584e72cfe61e8266e7935ffd933f8f7b496314d20dda7283; chkuin=290736056; confirmuin=290736056; superuin=o0290736056; superkey=GraFUCfQ78H6llIYsv71DzVDIrsA660-RYVWJAJTEZA_; ETK=; ptuserinfo=2f2340232a2f; ptvfsession=521a11ba98416447e58da6c2487f812f4fd4a07ebe851451bedd8392d40f1edecf7b8d51c9bb78fbc7c46e593eea296d");

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
//		List<String> msg = FileUtils.readLines(new File("msg.txt"));
		qparams.add(new BasicNameValuePair("clientid", clientid));
		qparams.add(new BasicNameValuePair("psessionid", "null"));
		String r = "{\"status\":\"online\",\"ptwebqq\":\""+ptwebqq+"\",\"passwd_sig\":\"\",\"clientid\":\""+clientid+"\",\"psessionid\":null}";
		qparams.add(new BasicNameValuePair("r",	r));
//		System.out.println("r:"+r);
		UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
		post.setEntity(params);

		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		String returnStr = EntityUtils.toString(entity);
		System.out.println(returnStr);
		
		vfwebqq = JsonParse.parse(returnStr, "vfwebqq");
		
		psessionid = JsonParse.parse(returnStr, "psessionid");
		EntityUtils.consume(entity);
	}

	
	
	
	
	public void chat() throws IOException {
		String url = "http://d.web2.qq.com/channel/send_buddy_msg2";
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", Constant.userAgent);
		
		post.setHeader("Referer",QQUtil.chatReferer);
		post.setHeader("Connection", "Keep-Alive");
		post.setHeader("Host", "d.web2.qq.com");
		
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();		
		
		qparams.add(new BasicNameValuePair("clientid", clientid));
		qparams.add(new BasicNameValuePair("psessionid", psessionid));
		
		List<String> str = FileUtils.readLines(new File("file/msg.txt"));
		String msg = str.get(0);
		
		msg = JsonParse.replace(msg, "msg_id", msg_id);
		msg = JsonParse.replace(msg, "clientid",clientid);
		msg = JsonParse.replace(msg, "psessionid",psessionid);
		qparams.add(new BasicNameValuePair("r",URLEncode.Utf8URLEncode(msg)));
		System.out.println("chatmsg:"+msg);
		UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
		post.setEntity(params);

		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		System.out.println(EntityUtils.toString(entity));

		EntityUtils.consume(entity);
	}
	
	
	public void chatQun() throws IOException {
		String url = QQUtil.chatQunUrl;
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", Constant.userAgent);
		
		post.setHeader("Referer",QQUtil.chatQunReferer);

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		
		
		qparams.add(new BasicNameValuePair("clientid", clientid));
		qparams.add(new BasicNameValuePair("psessionid", psessionid));
		
		List<String> str = FileUtils.readLines(new File("file/msg_qun.txt"));
		String msg = str.get(0);
		
		msg = JsonParse.replace(msg, "msg_id", msg_id);
		msg = JsonParse.replace(msg, "clientid",clientid);
		msg = JsonParse.replace(msg, "psessionid",psessionid);
		qparams.add(new BasicNameValuePair("r",msg));
		System.out.println("chatmsg:"+msg);
		UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
		post.setEntity(params);

		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		System.out.println(EntityUtils.toString(entity));

		EntityUtils.consume(entity);
	}
	
	
	public void friend(String url) throws IOException {
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", Constant.userAgent);
		post.setHeader("Connection", "Keep-Alive");
		post.setHeader("Host", "ssl.captcha.qq.com");

		post.setHeader(
				"Cookie",
				"pgv_pvid=5451193082; ac=1,021,; pt2gguin=o0290736056; RK=fS4nMJpf+j; uin_cookie=995843006; euin_cookie=B97BE018614E37A798D0318984B48A3ED744B393F35A8EC8; ptui_loginuin=290736056; pgv_info=pgvReferrer=&ssid=s1203241255; ptisp=edu; "
						+ "verifysession="
						+ verifysession
						+ "; "
						+ "uin=o0290736056; skey=@FQ5YPklID; ptcz=d203da56ea94d8257826f3d679d37a685e238aaa6124b98e462c20ff674e296d; uikey=4162aca5f70a7df7584e72cfe61e8266e7935ffd933f8f7b496314d20dda7283; chkuin=290736056; confirmuin=290736056; superuin=o0290736056; superkey=GraFUCfQ78H6llIYsv71DzVDIrsA660-RYVWJAJTEZA_; ETK=; ptuserinfo=2f2340232a2f; ptvfsession=521a11ba98416447e58da6c2487f812f4fd4a07ebe851451bedd8392d40f1edecf7b8d51c9bb78fbc7c46e593eea296d");
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		EntityUtils.consume(entity);
	}
	
	
	public void poll() throws IOException {
		String url = QQUtil.pollUrl;
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", Constant.userAgent);
		
		post.setHeader("Referer",QQUtil.pollReferer);

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		
		
		qparams.add(new BasicNameValuePair("clientid", clientid));
		qparams.add(new BasicNameValuePair("psessionid", psessionid));
		
		List<String> str = FileUtils.readLines(new File("file/poll.txt"));
		String msg = str.get(0);

		msg = JsonParse.replace(msg, "clientid",clientid);
		msg = JsonParse.replace(msg, "psessionid",psessionid);
		qparams.add(new BasicNameValuePair("r",msg));
//		System.out.println("chatmsg:"+msg);
		UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
		post.setEntity(params);

		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		System.out.println("poll返回值:"+EntityUtils.toString(entity));

		EntityUtils.consume(entity);
	}
}