package developersancho.profilescreen.ui.movie.popular;

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
import developersancho.profilescreen.databinding.FragmentPopularMoviesBinding;
import developersancho.profilescreen.ui.base.BaseFragment;
import developersancho.profilescreen.ui.movie.MoviesAdapter;
import developersancho.profilescreen.utils.NetworkUtils;


public class PopularMoviesFragment extends BaseFragment<FragmentPopularMoviesBinding, PopularMoviesViewModel>
        implements IPopulerMoviesNavigator, MoviesAdapter.MoviesAdapterListener {

    private FragmentPopularMoviesBinding binding;
    private MoviesAdapter adapter;
    private PopularMoviesViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_popular_movies;
    }

    @Override
    public PopularMoviesViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
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
        binding.recyclerviewPopular.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerviewPopular.setHasFixedSize(true);
        binding.recyclerviewPopular.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerviewPopular.setAdapter(adapter);
    }

    private void subscribeToMovieList() {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            viewModel.fetchPopularMovies().observe(this, movies -> adapter.setMovies(movies));
        } else {
            viewModel.fetchPopularMoviesRoom().observe(this, movies -> adapter.setMovies(movies));
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
