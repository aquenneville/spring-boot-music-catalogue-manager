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
import github.aq.musiccataloguemanager.model.ApiIdentifier;
import github.aq.musiccataloguemanager.model.Artist;

@Mapper
public interface ApiIdentifierMapper {

	@Select("SELECT id, name, artist_id, spotify_artist_id FROM api_identifier WHERE id = #{id}")
	@Results(value = {
		@Result(property = "id", column = "id"),
		@Result(property = "name", column = "name"),
		@Result(property = "artist", column = "artist_id", javaType = Artist.class, one = @One(select = "m.mu.model.ArtistMapper.selectById")),		
		@Result(property = "spotifyArtistId", column = "spotify_artist_id")
	})
	ApiIdentifier selectById(@Param("id") long id);
	
	@Insert("INSERT INTO api_identifier (name, artist_id, spotify_artist_id) VALUES(#{name}, #{artist.id}, #{spotify_artist_id})")
	@Options(useGeneratedKeys=true)
	long insert(ApiIdentifier apiIdentifier);
	
	@Update("UPDATE api_identifier SET (name=#{name}, artist_id=#{artist.id}, spotify_artist_id=#{spotifyArtistId}) WHERE id=#{id}")
	long update(ApiIdentifier apiIdentifier);
	
	@Delete("DELETE FROM api_identifier WHERE id=#{id}")
	long delete(ApiIdentifier apiIdentifier);
	
	@Select("SELECT * FROM api_identifier")
	List<ApiIdentifier> selectAll();
}
