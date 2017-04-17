package file;
import java.io.FileWriter;
import java.util.ArrayList;

public class MyFileWriter {
	public static void writeFile(String filePath, ArrayList<String> fileContent) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filePath);
			for (String lineContent : fileContent)
				fw.write(lineContent);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
