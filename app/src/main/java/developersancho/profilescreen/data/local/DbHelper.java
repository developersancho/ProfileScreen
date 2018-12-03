package developersancho.profilescreen.data.local;

import android.app.Application;

import java.util.List;

import developersancho.profilescreen.data.local.dao.FollowerDao;
import developersancho.profilescreen.data.local.dao.MemberDao;
import developersancho.profilescreen.data.local.dao.MovieDao;
import developersancho.profilescreen.data.local.database.MyDatabase;
import developersancho.profilescreen.data.model.db.FollowerEntity;
import developersancho.profilescreen.data.model.db.MemberEntity;
import developersancho.profilescreen.data.model.db.MovieEntity;
import io.reactivex.Observable;

public class DbHelper implements IDbHelper {

    private MovieDao movieDao;
    private FollowerDao followerDao;
    private MemberDao memberDao;

    public DbHelper(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        movieDao = database.movieDao();
        followerDao = database.followerDao();
        memberDao = database.memberDao();
    }

    @Override
    public void insertFollowerAll(List<FollowerEntity> followerEntities) {
        followerDao.insertAll(followerEntities);
    }

    @Override
    public void deleteFollowerAll() {
        followerDao.deleteAll();
    }

    @Override
    public Observable<List<FollowerEntity>> getAllFollowers() {
        return Observable.fromCallable(() -> followerDao.getAllFollowers());
    }

    @Override
    public void insertMember(MemberEntity memberEntity) {
        memberDao.insertAll(memberEntity);
    }

    @Override
    public void deleteMember() {
        memberDao.deleteAll();
    }

    @Override
    public Observable<MemberEntity> getMember() {
        return Observable.fromCallable(() -> memberDao.getMember());
    }

    @Override
    public void insertMovieAll(List<MovieEntity> movieEntities) {
        movieDao.insertAll(movieEntities);
    }

    @Override
    public void deleteMovieAll() {
        movieDao.deleteAll();
    }

    @Override
    public Observable<List<MovieEntity>> getAllMovies() {
        return Observable.fromCallable(() -> movieDao.getAllMovies());
    }
}
