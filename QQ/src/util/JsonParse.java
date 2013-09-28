package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class JsonParse {

	public static String parse(String jsonStr, String parm) {
		String returnStr;

		int index = 0;
		int end = 0;

		index = jsonStr.indexOf(parm) + parm.length() + 2;
		// System.out.println(jsonStr.charAt(index));

		if (jsonStr.substring(index).startsWith("\"")) {
			index = index + 1;
			end = end - 1;
		}
		String indexStr = jsonStr.substring(index);

		end = end + indexStr.indexOf(",");
		returnStr = indexStr.substring(0, end);
		System.out.println(parm + ":" + returnStr);
		return returnStr;
	}

	public static String replace(String jsonStr, String parm, String val) {
		String returnStr = "";

		int index = 0;
		int end = 0;

		index = jsonStr.indexOf(parm) + parm.length() + 2;
		// System.out.println(jsonStr.charAt(index));

		// Èç¹ûÊÇ×Ö·û´®
		if (jsonStr.substring(index).startsWith("\"")) {

			index = index + 1;

			String beforeStr = jsonStr.substring(0, index);

			String indexStr = jsonStr.substring(index);

			end = end + indexStr.indexOf("\"");

			String afterStr = jsonStr.substring(index + end);

			returnStr = beforeStr + val + afterStr;

		} else {

//			index = index +;

			String beforeStr = jsonStr.substring(0, index);

			String indexStr = jsonStr.substring(index);

			end = end + indexStr.indexOf(",");

			String afterStr = jsonStr.substring(index + end);

			returnStr = beforeStr + val + afterStr;

		}

		return returnStr;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		List<String> str = FileUtils.readLines(new File("file/json.txt"));
		// parse(str.get(0), "psessionid");

		List<String> str2 = FileUtils.readLines(new File("file/msg.txt"));

		
		String msg = str2.get(0);
		
		msg = JsonParse.replace(msg, "msg_id", "2345");
		System.out.println(msg);
		msg = JsonParse.replace(msg, "clientid", "ss");
		System.out.println(msg);
		msg = JsonParse.replace(msg, "psessionid", "12");
		System.out.println(msg);
	}

}
