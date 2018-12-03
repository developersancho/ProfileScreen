package developersancho.profilescreen.ui.movie.popular;

public interface IPopulerMoviesNavigator {
    void handleError(Throwable throwable);

    void showNotFoundMessage();
}
