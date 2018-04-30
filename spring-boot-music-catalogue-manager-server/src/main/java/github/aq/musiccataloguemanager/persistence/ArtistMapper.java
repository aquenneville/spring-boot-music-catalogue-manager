package github.aq.musiccataloguemanager.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import github.aq.musiccataloguemanager.model.Artist;

@Mapper
public interface ArtistMapper {

	@Select("SELECT id, name, spotify_artist_id, path FROM artist WHERE id = #{id}")	
	Artist selectById(@Param("id") long id);
	
	@Insert("INSERT INTO artist (name, spotify_artist_id, path) VALUES(#{name}, #{spotify_aritist_id}, #{path})")
	@Options(useGeneratedKeys=true)
	long insert(Artist artist);
	
	@Update("UPDATE artist SET (name=#{name}, spotify_artist_id=#{spotifyArtistId}, path=#{path}) WHERE id=#{id}")
	long update(Artist artist);
	
	@Delete("DELETE FROM artist WHERE id=#{id}")
	long delete(Artist artist);

	@Select("SELECT * FROM artist")
	List<Artist> selectAll();

}
