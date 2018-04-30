package github.aq.musiccataloguemanager.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import github.aq.musiccataloguemanager.configuration.MusicCatalogueManagerConfiguration;
import github.aq.musiccataloguemanager.model.Album;
import github.aq.musiccataloguemanager.model.Artist;
import github.aq.musiccataloguemanager.model.MusicEntry;
import github.aq.musiccataloguemanager.model.Song;
import github.aq.musiccataloguemanager.persistence.MusicEntryMapper;
import github.aq.musiccataloguemanager.persistence.SongMapper;
import github.aq.musiccataloguemanager.threads.PopulateAlbumTask;
import github.aq.musiccataloguemanager.threads.PopulateArtistTask;
import github.aq.musiccataloguemanager.threads.PopulateSongTask;
import github.aq.musiccataloguemanager.util.FileOperations;

public class ETLService {

	@Autowired
	MusicEntryMapper locationMapper;
	
	@Autowired
	SongMapper songMapper;
	
	@Autowired
	MusicCatalogueManagerConfiguration configuration;
	
	public String triggerPopulateArtistDatabaseProcess() {
		String pathOrigin = configuration.getPathOriginRoot();
		List<String> artisFoldertList = FileOperations.getArtistFolders(new File(pathOrigin).listFiles());
		
		// work already done ??
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (String artistFolderName: artisFoldertList) {
			Runnable populateTask = new PopulateArtistTask(artistFolderName);
			executor.execute(populateTask);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {};
		System.out.println("Finished all threads");
		return null;
	}
	
	public String triggerPopulateAlbumDatabaseProcess() {
		List<String> artistList = FileOperations.getArtistFolders(new File("C:/").listFiles());
	
		for (String artistName: artistList) {
			Artist artist = locationMapper.selectByArtistName(artistName);
			List<String> albumList = FileOperations.getAlbumFolders(artistName);
			ExecutorService executor = Executors.newFixedThreadPool(5);
			for (String albumName: albumList) {
				Runnable populateTask = new PopulateAlbumTask(albumName, artist);
				executor.execute(populateTask);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {};
			System.out.println("Finished all threads");
		}
		return null;
	}

	public String triggerPopulateSongsDatabaseProcess() {
		List<String> albumList = locationMapper.selectAllSourceAlbums();
		
		for (String albumName: albumList) {
			List<String> songs = FileOperations.getAlbumSongs(albumName);
			MusicEntry locationAlbum = locationMapper.selectByAlbum(albumName);	// path origin
			Album album = locationAlbum.getAlbum();
			ExecutorService executor = Executors.newFixedThreadPool(5);
			for (String songName: songs) {
				Runnable populateTask = new PopulateSongTask(songName, album);
				executor.execute(populateTask);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {};
			System.out.println("Finished all threads");
		}
		return null;	
	}
	
	public String createNewMusicLibrary() {
		List<String> albumNames = locationMapper.selectAllSourceAlbums();
		for (String albumName: albumNames) {
			MusicEntry locationAlbum = locationMapper.selectByAlbum(albumName);
			Album album = locationAlbum.getAlbum();
			File file = new File("root_path"+Paths.get(album.getArtist().getName(), album.getName()));
			if (file.mkdirs()) {
				System.out.println("Created the path: " + "root_path"+Paths.get(album.getArtist().getName(), album.getName()));
			} else {
				System.out.println("Failed to create the path: " + "root_path"+Paths.get(album.getArtist().getName(), album.getName()));
			}
			
			List<String> songNames = FileOperations.getAlbumSongs(albumName);
			for (String songName: songNames) {
				MusicEntry locationSong = locationMapper.selectBySong(songName);
				Song song = locationSong.getSong();
				String pathCatalogue = songMapper.getCataloguePath(song.getId());
				Path pathOrigin = song.getPathOrigin();
				try {
					FileOperations.copyFileUsingChannel(new File(pathOrigin.toString()), new File(pathCatalogue));
				} catch (IOException exc) {
					
				}
			}
		}
		
		
		
		return null;
	}
	

}
