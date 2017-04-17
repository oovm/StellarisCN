package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class MyFileComparer {
	public static void compareFile(String originFilePath, String targetFilePath) {
		ArrayList<String> originFileContent = file.MyFileReader.readFile(originFilePath);
		ArrayList<String> targetFileContent = file.MyFileReader.readFile(targetFilePath);
		ArrayList<String[]> otmp = new ArrayList<String[]>();
		for (String lineContent : originFileContent) {
			if (lineContent.contains(":")) {
				otmp.add(lineContent.split(":"));
			}
		}
		ArrayList<String[]> ttmp = new ArrayList<String[]>();
		for (String lineContent : targetFileContent) {
			if (lineContent.contains(":")) {
				ttmp.add(lineContent.split(":"));
			}
		}
		Iterator<String[]> oit = otmp.iterator();
		while (oit.hasNext()) {
			String[] olineContent = oit.next();
			Iterator<String[]> tit = ttmp.iterator();
			while (tit.hasNext()) {
				String[] tlineContent = tit.next();
				if (olineContent[0].trim().equals(tlineContent[0].trim())) {
					oit.remove();
					tit.remove();
					break;
				}
			}
		}
		if (!otmp.isEmpty()) {
			System.out.println("Origin");
			for (String[] olineContent : otmp) {
				System.out.println(olineContent[0]);
			}
		}
		if (!ttmp.isEmpty()) {
			System.out.println("Target");
			for (String[] tlineContent : ttmp) {
				System.out.println(tlineContent[0]);
			}
		}
	}

//	public static void main(String[] argv) {
//		String originFolderPath = "./Origin/localisation 1.5.1";
//		String targetFolderPath = "./Localisation/localisation";
//		File file = new File(originFolderPath);
//		String[] fileNameList = file.list();
//		for (String fileName : fileNameList) {
//			System.out.println(fileName);
//			compareFile(originFolderPath + "/" + fileName, targetFolderPath + "/" + fileName);
//		}
//	}
}
