package developersancho.profilescreen.ui.profile;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import developersancho.profilescreen.data.model.api.followers.Follower;
import developersancho.profilescreen.data.model.api.members.Member;
import developersancho.profilescreen.data.model.db.FollowerEntity;
import developersancho.profilescreen.data.model.db.MemberEntity;
import developersancho.profilescreen.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends BaseViewModel<IProfileNavigator> {

    private MutableLiveData<List<Follower>> followers;
    private MutableLiveData<Member> member;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        followers = new MutableLiveData<>();
        member = new MutableLiveData<>();
    }

    public MutableLiveData<List<Follower>> fetchFollowers() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().createProfileService().getFollowers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null && response.getResult().getItems() != null) {
                        followers.setValue(response.getResult().getItems());
                        insertFollowerEntity(response.getResult().getItems());
                    } else {
                        getNavigator().showNotFoundMessage();
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
        return followers;
    }

    private void insertFollowerEntity(List<Follower> followers) {
        List<FollowerEntity> entityList = new ArrayList<>();
        for (Follower model : followers) {
            entityList.add(new FollowerEntity(model.getProfilePictureUrl()));
        }
        getDataManager().deleteFollowerAll();
        getDataManager().insertFollowerAll(entityList);
    }

    public MutableLiveData<List<Follower>> fetchFollowersRoom() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().getAllFollowers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null) {
                        convertFollowerEntity(response);
                    } else {
                        getNavigator().showNotFoundMessage();
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
        return followers;
    }

    private void convertFollowerEntity(List<FollowerEntity> response) {
        List<Follower> list = new ArrayList<>();
        for (FollowerEntity entity : response) {
            list.add(new Follower(entity.getProfilePictureUrl()));
        }
        followers.setValue(list);
    }

    public MutableLiveData<Member> fetchMember() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().createProfileService().getMember()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null && response.getResult() != null) {
                        member.setValue(response.getResult());
                        insertMemberEntity(response.getResult());
                    } else {
                        getNavigator().showNotFoundMessage();
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
        return member;
    }

    private void insertMemberEntity(Member item) {
        MemberEntity memberEntity = new MemberEntity(item.getUsername(), item.getProfilePictureUrl(), item.getProfileBackgroundPictureUrl());
        getDataManager().deleteMember();
        getDataManager().insertMember(memberEntity);
    }

    public MutableLiveData<Member> fetchMemberRoom() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().getMember()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null) {
                        convertMemberEntity(response);
                    } else {
                        getNavigator().showNotFoundMessage();
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
        return member;
    }

    private void convertMemberEntity(MemberEntity response) {
        Member item = new Member(response.getUsername(), response.getProfilePictureUrl(), response.getProfileBackgroundPictureUrl());
        member.setValue(item);
    }
}
