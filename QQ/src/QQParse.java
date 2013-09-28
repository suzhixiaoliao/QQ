import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class QQParse {

	
	
	public static void parse(String content){
		Document doc = Jsoup.parse(content);

		Elements msgBoxElements = doc.getElementsByClass("msgBox");
		for(Element msg :msgBoxElements){
		

			System.out.println(msg.text());
			
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String str = FileUtils.readFileToString(new File("huiwq1990.txt"));
			parse(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
