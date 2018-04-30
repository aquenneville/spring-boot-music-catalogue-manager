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

@Mapper
public interface AlbumMapper {

	@Select("SELECT id, name artist_id, path FROM album WHERE id = #{id}")
	@Results(value = {
		@Result(property = "id", column = "id"),
		@Result(property = "name", column = "name"),
		@Result(property = "artist", column = "artist_id", javaType = Artist.class, one = @One(select = "m.mu.model.ArtistMapper.selectById")),		
		@Result(property = "path", column = "path"), 
		@Result(property = "releaseDate", column = "release_date")
	})
	Album selectById(@Param("id") long id);
	
	@Insert("INSERT INTO album (name, artist_id, path) VALUES(#{name}, #{artist.id}, #{path}, #{relaseDate})")
	@Options(useGeneratedKeys=true)
	long insert(Album album);
	
	@Update("UPDATE artist SET (name=#{name}, artist=#{artist.id}, spotify_artist_id=#{spotifyArtistId}, path=#{path}, release_date=#{releaseDate}) WHERE id=#{id}")
	long update(Album album);
	
	@Delete("DELETE FROM album WHERE id=#{id}")
	long delete(Album album);

	@Select("SELECT * FROM album")
	List<Album> selectAll();
	
}
