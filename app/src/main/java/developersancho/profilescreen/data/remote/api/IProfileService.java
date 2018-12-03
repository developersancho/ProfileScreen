package developersancho.profilescreen.data.remote.api;

import developersancho.profilescreen.data.model.api.followers.FollowerResponse;
import developersancho.profilescreen.data.model.api.members.MemberResponse;
import developersancho.profilescreen.data.model.api.movies.MovieResponse;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface IProfileService {

    @GET("member")
    Single<MemberResponse> getMember();

    @GET("followers")
    Single<FollowerResponse> getFollowers();

    @GET("movies")
    Single<MovieResponse> getMovies();

}
