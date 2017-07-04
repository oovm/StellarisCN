package file;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyFileReader {
	public static ArrayList<String> readFile(String filePath){
		ArrayList<String> fileContent = new ArrayList<String>(); 
        try {
                String encoding="utf-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){
                    InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file),encoding);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String lineContent = null;
                    while((lineContent = bufferedReader.readLine()) != null){
                        fileContent.add(lineContent);
                    }
                    reader.close();
        }else{
            System.out.println("File not found");
        }
        } catch (Exception e) {
        System.out.println("File reader error");
            e.printStackTrace();
        }
     return fileContent;
    }
}
