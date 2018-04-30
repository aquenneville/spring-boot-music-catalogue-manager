package github.aq.musiccataloguemanager.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import github.aq.musiccataloguemanager.model.Album;
import github.aq.musiccataloguemanager.model.Artist;
import github.aq.musiccataloguemanager.model.MusicEntry;
import github.aq.musiccataloguemanager.model.Song;

@Mapper
public interface MusicEntryMapper {

	@Select("SELECT id, artist_id, source_path, catalogue_path FROM location WHERE id = #{id}")	
	MusicEntry selectById(@Param("id") long id);
	
	@Insert("INSERT INTO location (name, artist_id, source_path, catalogue_path) VALUES(#{name}, #{artist.id}, #{source_path}, #{catalogue_path})")
	@Options(useGeneratedKeys=true)
	long insert(MusicEntry location);
	
	@Update("UPDATE location SET (name=#{name}, artist_id=#{artist.id}, path=#{sourcePath}, #{cataloguePath}) WHERE id=#{id}")
	long update(MusicEntry location);
	
	@Delete("DELETE FROM location WHERE id=#{id}")
	long delete(MusicEntry location);

	@Select("SELECT * FROM location")
	List<MusicEntry> selectAll();

	@Select("SELECT a.* FROM location l" +
			"INNER JOIN artist a ON a.id = l.artist_id WHERE source_path = #{artistName}")
	@Results(value = {			
			@Result(property = "artist", column = "artist_id", javaType = Artist.class, one = @One(
					select = "m.mu.mapper.ArtistMapper.selectById"))			
	})
	Artist selectByArtistName(@Param("artistName") String artistName);
	
	@Select("SELECT source_path FROM location l WHERE artist_id IS NULL")	
	List<String> selectAllSourceAlbums();
	
	@Select("SELECT source_path FROM location l WHERE artist_id IS NULL AND source_path = #{albumName}")	
	MusicEntry selectByAlbum(@Param("albumName") String albumName);
	
	@Select("SELECT source_path FROM location l WHERE artist_id IS NULL album_is is NULL AND source_path = #{songName}")
	MusicEntry selectBySong(@Param("songName") String songName); 
	
	// select list of songs of album
}
