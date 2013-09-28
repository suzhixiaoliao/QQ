package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;

public class TestJson {

	/**
	 * @param args
	 * @throws IOException
	 */

	public static String parse(String jsonStr, String parm) {
		String returnStr;
//		String indexStr = ;
		int index = jsonStr.indexOf(parm)+parm.length()+3;
		String indexStr = jsonStr.substring(index);
		
		int end = indexStr.indexOf("\"");
		returnStr = indexStr.substring(0,end);
		System.out.println(parm+":"+returnStr);
		return returnStr;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> str = FileUtils.readLines(new File("file/json.txt"));
		parse(str.get(0),"vfwebqq");
		
//		String returnStr = str.get(0);
//		String indexStr ;
//		
//		indexStr = ""
//		int index = returnStr.indexOf("vfwebqq");
//		String temp = returnStr.substring(index);
//		System.out.println(index);
//		
//		System.out.println(temp);
//		
//		System.out.println(temp.indexOf(","));
//		String vfwebqq = temp.substring( "vfwebqq".length()+2,index);
//		System.out.println(vfwebqq);
//		
//		String psessionid = returnStr.substring(returnStr.indexOf("psessionid")+"psessionid".length()+2,returnStr.indexOf("user_state")-3);
//		System.out.println("psessionid:" + psessionid);

		JSONArray arry = JSONArray.fromObject(str.get(0));
		System.out.println("json×Ö·û´®ÄÚÈÝÈçÏÂ");
		System.out.println(arry);
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = jsonObject.get(key).toString();
				map.put(key, value);
			}
		}
	}
}
