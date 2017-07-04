package file;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MyFileWriter {
	public static void writeFile(String filePath, ArrayList<String> fileContent) {
		try {
			String encoding = "utf-8";
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), encoding);
			for (String lineContent : fileContent) {
				if (fileContent.indexOf(lineContent) == fileContent.size() - 1)
					writer.write(lineContent);
				else
					writer.write(lineContent + "\r\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
