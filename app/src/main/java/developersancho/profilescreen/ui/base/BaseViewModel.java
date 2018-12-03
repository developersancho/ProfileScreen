package developersancho.profilescreen.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import developersancho.profilescreen.data.repository.DataManager;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<N> extends AndroidViewModel {
    private DataManager dataManager;
    private ObservableBoolean isLoading = new ObservableBoolean(false);
    private CompositeDisposable compositeDisposable;
    private WeakReference<N> navigator;


    public BaseViewModel(@NonNull Application application) {
        super(application);
        dataManager = new DataManager(application);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public N getNavigator() {
        return navigator.get();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }

}
