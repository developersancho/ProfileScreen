package developersancho.profilescreen.ui.movie.popular;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import developersancho.profilescreen.data.model.api.movies.Movie;
import developersancho.profilescreen.data.model.db.MovieEntity;
import developersancho.profilescreen.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PopularMoviesViewModel extends BaseViewModel<IPopulerMoviesNavigator> {
    private MutableLiveData<List<Movie>> movies;

    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        movies = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> fetchPopularMovies() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().createProfileService().getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null && response.getResult().getItems() != null) {
                        Collections.sort(response.getResult().getItems(), new Movie.LikeCountComparator());
                        movies.setValue(response.getResult().getItems());
                        insertMovieEntity(response.getResult().getItems());
                    } else {
                        getNavigator().showNotFoundMessage();
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
        return movies;
    }

    private void insertMovieEntity(List<Movie> movies) {
        List<MovieEntity> entityList = new ArrayList<>();
        for (Movie model : movies) {
            entityList.add(new MovieEntity(model.getName(), model.getProfilePictureUrl(),
                    model.isLiked(), model.getLikeCount(), model.getCreateDate()));
        }
        getDataManager().deleteMovieAll();
        getDataManager().insertMovieAll(entityList);
    }

    public MutableLiveData<List<Movie>> fetchPopularMoviesRoom() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null) {
                        convertMovieEntity(response);
                    } else {
                        getNavigator().showNotFoundMessage();
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
        return movies;
    }

    private void convertMovieEntity(List<MovieEntity> response) {
        List<Movie> list = new ArrayList<>();
        for (MovieEntity entity : response) {
            list.add(new Movie(entity.getName(), entity.getProfilePictureUrl(),
                    entity.isLiked(), entity.getLikeCount(), entity.getCreateDate()));
        }
        Collections.sort(list, new Movie.LikeCountComparator());
        movies.setValue(list);
    }
}
