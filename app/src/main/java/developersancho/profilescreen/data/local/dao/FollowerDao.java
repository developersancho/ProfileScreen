package developersancho.profilescreen.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import developersancho.profilescreen.data.model.db.FollowerEntity;

@Dao
public interface FollowerDao {
    @Insert
    void insertAll(List<FollowerEntity> followerEntities);

    @Query("DELETE FROM follower_table")
    void deleteAll();

    @Query("SELECT * FROM follower_table")
    List<FollowerEntity> getAllFollowers();
}
