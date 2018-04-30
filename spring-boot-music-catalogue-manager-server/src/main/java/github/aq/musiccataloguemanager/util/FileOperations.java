package github.aq.musiccataloguemanager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {

	public static void main(String... args) {
	    File[] files = new File("C:/").listFiles();
	    if (files != null) 
	       getArtistFolders(files);
	}

	public static List<String> getArtistFolders(File[] files) {
		List<String> artistFolderList = new ArrayList<String>(); 
	    for (File file : files) {
	        if (file.isDirectory()) {
	            artistFolderList.add(file.getAbsolutePath().toString());
	        } else {
	            System.out.println("File: " + file.toString());
	        }
	    }
	    return artistFolderList;
	}
	
	public static List<String> getAlbumFolders(String artistFolder) {
		File[] files = new File(artistFolder).listFiles();
		List<String> albumFolderList = new ArrayList<String>(); 
		for (File file : files) {
	        if (file.isDirectory()) {
	        	albumFolderList.add(file.getAbsolutePath().toString());
	        } else {
	            System.out.println("File: " + file.toString());
	        }
	    }
	    return albumFolderList;
	}

	public static List<String> getAlbumSongs(String albumName) {
		File[] files = new File(albumName).listFiles();
		List<String> albumSongsList = new ArrayList<String>(); 
		for (File file : files) {
	        if (file.isFile()) {
	        	albumSongsList.add(file.getAbsolutePath().toString());
	        } else {
	            System.out.println("Is dir: " + file.toString());
	        }
	    }
	    return albumSongsList;
	}
	
	public static void getFile(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            getFile(file.listFiles());
	        } else {
	            System.out.println("File: " + file.toString());
	        }
	    }
	}
	
	public static void copyFileUsingChannel(File source, File dest) throws IOException {
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		FileInputStream fis = new FileInputStream(source);
		FileOutputStream fos = new FileOutputStream(dest);
		try {
			sourceChannel = fis.getChannel();
			destChannel = fos.getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} finally {
			sourceChannel.close();
			destChannel.close();
			fis.close();
			fos.close();
		}
	}
}
