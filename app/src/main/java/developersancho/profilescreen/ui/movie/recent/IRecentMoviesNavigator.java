package developersancho.profilescreen.ui.movie.recent;

public interface IRecentMoviesNavigator {
    void handleError(Throwable throwable);

    void showNotFoundMessage();
}
