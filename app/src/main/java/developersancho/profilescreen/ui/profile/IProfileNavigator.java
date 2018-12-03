package developersancho.profilescreen.ui.profile;

public interface IProfileNavigator {
    void handleError(Throwable throwable);

    void showNotFoundMessage();
}
