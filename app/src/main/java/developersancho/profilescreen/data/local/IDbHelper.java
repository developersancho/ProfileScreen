package developersancho.profilescreen.data.local;

import java.util.List;

import developersancho.profilescreen.data.model.db.FollowerEntity;
import developersancho.profilescreen.data.model.db.MemberEntity;
import developersancho.profilescreen.data.model.db.MovieEntity;
import io.reactivex.Observable;

public interface IDbHelper {

    void insertFollowerAll(List<FollowerEntity> followerEntities);

    void deleteFollowerAll();

    Observable<List<FollowerEntity>> getAllFollowers();


    void insertMember(MemberEntity memberEntity);

    void deleteMember();

    Observable<MemberEntity> getMember();


    void insertMovieAll(List<MovieEntity> movieEntities);

    void deleteMovieAll();

    Observable<List<MovieEntity>> getAllMovies();
}
