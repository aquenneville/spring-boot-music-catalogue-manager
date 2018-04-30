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
import github.aq.musiccataloguemanager.model.Song;

@Mapper
public interface SongMapper {

	@Select("SELECT id, artist_id, album_id FROM song WHERE id = #{id}")	
	@Results(value = {
		@Result(property = "id", column = "id"),			
		@Result(property = "album", column = "album_id", javaType = Album.class, one = @One(select = "m.mu.model.AlbumMapper.selectById")),
		@Result(property = "length", column = "length"),
		@Result(property = "path", column = "path") 
	})
	Song selectById(@Param("id") long id);
	
	@Insert("INSERT INTO artist (album_id, length, path) VALUES(#{album.id}, #{length}, #{path})")
	@Options(useGeneratedKeys=true)
	long insert(Song song);
	
	@Update("UPDATE song SET (album_id=#{album.id}, length=#{length}, path=#{path}")
	long update(Song song);
	
	@Delete("DELETE FROM song WHERE id=#{id}")
	long delete(Song song);

	@Select("SELECT * FROM song")
	List<Song> selectAll();
	
	@Select("SELECT concat FROM song s"
			+ "INNER JOIN album a ON s.album_id = a_id"
			+ "INNER JOIN artist ar ON a.artist_id = ar.id")
	@Results(value = {
			@Result(property = "id", column = "id"),			
			@Result(property = "album", column = "album_id", javaType = Album.class, one = @One(select = "m.mu.model.AlbumMapper.selectById")),
			@Result(property = "artist", column = "artist_id", javaType = Artist.class, one = @One(select = "m.mu.model.ArtistMapper.selectById"))
	})
	String getCataloguePath(@Param("songName") Long songId);
}
