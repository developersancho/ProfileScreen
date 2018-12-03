package developersancho.profilescreen.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import developersancho.profilescreen.data.model.db.MemberEntity;

@Dao
public interface MemberDao {
    @Insert
    void insertAll(MemberEntity memberEntity);

    @Query("DELETE FROM member_table")
    void deleteAll();

    @Query("SELECT * FROM member_table")
    MemberEntity getMember();
}
