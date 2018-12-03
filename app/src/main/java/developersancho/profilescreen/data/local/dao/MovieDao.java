package developersancho.profilescreen.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import developersancho.profilescreen.data.model.db.MovieEntity;

@Dao
public interface MovieDao {
    @Insert
    void insertAll(List<MovieEntity> movieEntities);

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Query("SELECT * FROM movie_table")
    List<MovieEntity> getAllMovies();
}
