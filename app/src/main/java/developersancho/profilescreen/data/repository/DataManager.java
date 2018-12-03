package developersancho.profilescreen.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import developersancho.profilescreen.data.local.DbHelper;
import developersancho.profilescreen.data.model.db.FollowerEntity;
import developersancho.profilescreen.data.model.db.MemberEntity;
import developersancho.profilescreen.data.model.db.MovieEntity;
import developersancho.profilescreen.data.remote.ApiHelper;
import developersancho.profilescreen.data.remote.api.IProfileService;
import io.reactivex.Observable;

public class DataManager implements IDataManager {

    private ApiHelper apiHelper;
    private DbHelper dbHelper;

    public DataManager(Application application) {
        apiHelper = new ApiHelper();
        dbHelper = new DbHelper(application);
    }

    @Override
    public IProfileService createProfileService() {
        return apiHelper.createProfileService();
    }

    @Override
    public void insertFollowerAll(List<FollowerEntity> followerEntities) {
        new InsertFollowerListAsyncTask(dbHelper).execute(followerEntities);
    }

    @Override
    public void deleteFollowerAll() {
        new DeleteAllFollowerAsyncTask(dbHelper).execute();
    }

    @Override
    public Observable<List<FollowerEntity>> getAllFollowers() {
        return dbHelper.getAllFollowers();
    }

    @Override
    public void insertMember(MemberEntity memberEntity) {
        new InsertMemberAsyncTask(dbHelper).execute(memberEntity);
    }

    @Override
    public void deleteMember() {
        new DeleteAllMemberAsyncTask(dbHelper).execute();
    }

    @Override
    public Observable<MemberEntity> getMember() {
        return dbHelper.getMember();
    }

    @Override
    public void insertMovieAll(List<MovieEntity> movieEntities) {
        new InsertMoviesListAsyncTask(dbHelper).execute(movieEntities);
    }

    @Override
    public void deleteMovieAll() {
        new DeleteAllMoviesAsyncTask(dbHelper).execute();
    }

    @Override
    public Observable<List<MovieEntity>> getAllMovies() {
        return dbHelper.getAllMovies();
    }

    private static class InsertFollowerListAsyncTask extends AsyncTask<List<FollowerEntity>, Void, Void> {
        private DbHelper dbHelper;

        public InsertFollowerListAsyncTask(DbHelper dbHelper) {
            this.dbHelper = dbHelper;
        }

        @Override
        protected Void doInBackground(List<FollowerEntity>... lists) {
            dbHelper.insertFollowerAll(lists[0]);
            return null;
        }
    }

    private static class DeleteAllFollowerAsyncTask extends AsyncTask<Void, Void, Void> {

        private DbHelper dbHelper;

        public DeleteAllFollowerAsyncTask(DbHelper dbHelper) {
            this.dbHelper = dbHelper;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dbHelper.deleteFollowerAll();
            return null;
        }
    }

    private static class InsertMoviesListAsyncTask extends AsyncTask<List<MovieEntity>, Void, Void> {
        private DbHelper dbHelper;

        public InsertMoviesListAsyncTask(DbHelper dbHelper) {
            this.dbHelper = dbHelper;
        }

        @Override
        protected Void doInBackground(List<MovieEntity>... lists) {
            dbHelper.insertMovieAll(lists[0]);
            return null;
        }
    }

    private static class DeleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void> {

        private DbHelper dbHelper;

        public DeleteAllMoviesAsyncTask(DbHelper dbHelper) {
            this.dbHelper = dbHelper;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dbHelper.deleteMovieAll();
            return null;
        }
    }

    private static class InsertMemberAsyncTask extends AsyncTask<MemberEntity, Void, Void> {

        private DbHelper dbHelper;

        public InsertMemberAsyncTask(DbHelper dbHelper) {
            this.dbHelper = dbHelper;
        }

        @Override
        protected Void doInBackground(MemberEntity... carWashEntities) {
            dbHelper.insertMember(carWashEntities[0]);
            return null;
        }
    }

    private static class DeleteAllMemberAsyncTask extends AsyncTask<Void, Void, Void> {

        private DbHelper dbHelper;

        public DeleteAllMemberAsyncTask(DbHelper dbHelper) {
            this.dbHelper = dbHelper;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dbHelper.deleteMember();
            return null;
        }
    }
}
