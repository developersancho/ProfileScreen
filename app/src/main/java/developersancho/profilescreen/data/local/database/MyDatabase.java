package developersancho.profilescreen.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import developersancho.profilescreen.data.local.dao.FollowerDao;
import developersancho.profilescreen.data.local.dao.MemberDao;
import developersancho.profilescreen.data.local.dao.MovieDao;
import developersancho.profilescreen.data.model.db.FollowerEntity;
import developersancho.profilescreen.data.model.db.MemberEntity;
import developersancho.profilescreen.data.model.db.MovieEntity;
import developersancho.profilescreen.utils.AppConstant;

@Database(entities = {MovieEntity.class, MemberEntity.class, FollowerEntity.class},
        version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract FollowerDao followerDao();

    public abstract MemberDao memberDao();

    private static MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, AppConstant.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
