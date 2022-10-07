package com.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ServerAPI.FileType;

public class FileTest {
 
	public static String getshownImagePath(String fileExtention ) {
        String path;
		switch (fileExtention) {
             case ".txt":
            	 path =  "filtersIcons/format_text.png"; break;
            case ".mp3":
            	path =  "filtersIcons/format_mp3.png"; break;
            case ".mp4":
            case ".mov":
            case ".wmv":
            case ".mpv":
            case ".flv":
            	path =  "filtersIcons/format_video.png"; break;
            case ".pdf":
            	path =  "filtersIcons/format_pdf.png"; break;
            case ".docx":
            	path =  "filtersIcons/format_word.png"; break;
            case ".xlsx":
                path = "filtersIcons/format_excel.png"; break;
            case ".ppt":
            	path =  "filtersIcons/format_ppt.png"; break;
            case ".zip":
            	path =  "filtersIcons/format_zip.png"; break;
            case ".rar":
            	path =  "filtersIcons/format_zip.png"; break;
            default:
            	path = "filtersIcons/format_file.png"; break;
        }
       return path;  
    }
	
	public static String getExtentionOf(String exp) {
		int i = exp.lastIndexOf('.');
		String ext = exp.substring(i);
		return ext;
	}
	
	 
	public static String getFilePath(String FileExtention) {
        switch (FileExtention) {
            case ".png":
            case ".jpg":
            case ".gif":
            case ".jpeg": 
            case ".svg":
                 return "Files\\Media\\Images\\";
            case ".pdf":
            case ".ppt":
            case ".docx":
            case ".xlsx":
            case ".txt":
                return "Files\\Media\\Documents\\";
            case ".mp4":
            case ".mov":
            case ".wmv":
            case ".mpv":
            case ".flv":
            case ".mp3":
            	 return "Files\\Media\\Video\\";
             default:
                return "Files\\";
        }
    }
	public static String getFilePath(int fileType) {
        switch (fileType) {
            case FileType.IMAGE:
                  return "Files\\Media\\Images\\";
            case FileType.DOCUMENT:
                 return "Files\\Media\\Documents\\";
            case FileType.VIDEO:
             	 return "Files\\Media\\Video\\";
             default:
                return "Files\\";
        }
    }
	
	public static int getFileType(String FileExtention) {
        switch (FileExtention) {
            case ".png":
            case ".jpg":
            case ".gif":
            case ".jpeg":
            case ".svg":
                 return FileType.IMAGE;  
            case ".pdf":
            case ".ppt":
            case ".docx":
            case ".xlsx":
            case ".txt":
            	return FileType.DOCUMENT;
            case ".mp4":
            case ".mov":
            case ".wmv":
            case ".mpv":
            case ".flv":
            case ".mp3":
            	return FileType.VIDEO;
             default:
            	 return FileType.DOCUMENT;
        }
	}
	public static void copyFile(File CopyFrom,File copyTo)  {
		FileInputStream fin = null ;
		FileOutputStream fout = null ;
 		try {
 			fin = new FileInputStream(CopyFrom);
 			fout = new  FileOutputStream(copyTo);
			int data;
			while((data = fin.read()) != -1)
				fout.write(data);
			
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}finally {	
			try {
				fin.close();
				fout.close();
 			} catch (IOException e) {
 				e.printStackTrace();
			}
		}
 	}
	
	 
}
