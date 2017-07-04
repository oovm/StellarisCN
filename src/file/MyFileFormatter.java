package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.filechooser.FileSystemView;

public class MyFileFormatter {
	public static void formatFile(String originFilePath, String targetFilePath, String sourceFilePath) {
		ArrayList<String> originFileContent = file.MyFileReader.readFile(originFilePath);
		ArrayList<String> sourceFileContent = file.MyFileReader.readFile(sourceFilePath);
		ArrayList<String> targetFileContent = new ArrayList<String>();
		ArrayList<String[]> stmp = new ArrayList<String[]>();
		for (String lineContent : sourceFileContent) {
			if (lineContent.contains("#")) {
				lineContent = lineContent.substring(0, lineContent.indexOf("#"));
			}
			if (lineContent.contains(":")) {
				stmp.add(new String[] { lineContent.substring(0, lineContent.indexOf(":")),
						lineContent.substring(lineContent.indexOf(":") + 1) });
			}
		}
		for (String lineContent : originFileContent) {
			if (lineContent.contains(":")) {
				if (lineContent.contains("#")) {
					if (lineContent.indexOf("#") > lineContent.indexOf(":")) {
						String comment = lineContent.substring(lineContent.indexOf("#") + 1);
						lineContent = lineContent.substring(0, lineContent.indexOf("#"));
						String[] olineContent = new String[] { lineContent.substring(0, lineContent.indexOf(":")),
								lineContent.substring(lineContent.indexOf(":") + 1) };
						Iterator<String[]> sit = stmp.iterator();
						while (sit.hasNext()) {
							String[] slineContent = sit.next();
							if (olineContent[0].trim().equals(slineContent[0].trim())) {
								lineContent = olineContent[0] + ":" + slineContent[1].trim() + "#" + comment;
								sit.remove();
								break;
							}
						}
					}
				} else {
					String[] olineContent = new String[] { lineContent.substring(0, lineContent.indexOf(":")),
							lineContent.substring(lineContent.indexOf(":") + 1) };
					Iterator<String[]> sit = stmp.iterator();
					while (sit.hasNext()) {
						String[] slineContent = sit.next();
						if (olineContent[0].trim().equals(slineContent[0].trim())) {
							lineContent = olineContent[0] + ":" + slineContent[1].trim();
							sit.remove();
							break;
						}
					}
				}
			}
			targetFileContent.add(lineContent);
		}
		MyFileWriter.writeFile(targetFilePath, targetFileContent);
	}

	public static void main(String[] argv) {
		String originFolderPath = "./Origin/localisation 1.5.1";
		String targetFolderPath = "./Localisation/localisation";
		String sourceFolderPath = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + "/english";
		File file = new File(originFolderPath);
		String[] fileNameList = file.list();
		for (String fileName : fileNameList) {
			System.out.println(fileName);
			formatFile(originFolderPath + "/" + fileName, targetFolderPath + "/" + fileName,
					sourceFolderPath + "/" + fileName);
		}
	}
}
