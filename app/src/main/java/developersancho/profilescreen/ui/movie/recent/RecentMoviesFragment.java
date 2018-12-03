package developersancho.profilescreen.ui.movie.recent;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import developersancho.profilescreen.BR;
import developersancho.profilescreen.R;
import developersancho.profilescreen.data.model.api.movies.Movie;
import developersancho.profilescreen.databinding.FragmentRecentMoviesBinding;
import developersancho.profilescreen.ui.base.BaseFragment;
import developersancho.profilescreen.ui.movie.MoviesAdapter;
import developersancho.profilescreen.utils.NetworkUtils;


public class RecentMoviesFragment extends BaseFragment<FragmentRecentMoviesBinding, RecentMoviesViewModel>
        implements IRecentMoviesNavigator, MoviesAdapter.MoviesAdapterListener {

    private FragmentRecentMoviesBinding binding;
    private MoviesAdapter adapter;
    private RecentMoviesViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recent_movies;
    }

    @Override
    public RecentMoviesViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(RecentMoviesViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        adapter = new MoviesAdapter(getContext());
        adapter.setMoviesAdapterListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        setUp();
        subscribeToMovieList();
    }

    private void setUp() {
        binding.recyclerviewRecent.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerviewRecent.setHasFixedSize(true);
        binding.recyclerviewRecent.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerviewRecent.setAdapter(adapter);
    }

    private void subscribeToMovieList() {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            viewModel.fetchRecentMovies().observe(this, movies -> adapter.setMovies(movies));
        } else {
            viewModel.fetchRecentMoviesRoom().observe(this, movies -> adapter.setMovies(movies));
        }

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void showNotFoundMessage() {

    }

    @Override
    public void onMovieClicked(Movie movie) {

    }
}
