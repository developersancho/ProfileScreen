package developersancho.profilescreen.ui.movie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import developersancho.profilescreen.R;
import developersancho.profilescreen.data.model.api.movies.Movie;
import developersancho.profilescreen.databinding.ItemRowMovieBinding;
import developersancho.profilescreen.ui.base.BaseViewHolder;

public class MoviesAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Movie> movieList = new ArrayList<>();
    private Context context;
    private MoviesAdapterListener listener;
    private LayoutInflater layoutInflater;

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemRowMovieBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_row_movie, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public void setMovies(List<Movie> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    public void setMoviesAdapterListener(MoviesAdapterListener listener) {
        this.listener = listener;
    }


    public interface MoviesAdapterListener {
        void onMovieClicked(Movie movie);
    }

    public class MovieViewHolder extends BaseViewHolder {
        private ItemRowMovieBinding binding;

        public MovieViewHolder(ItemRowMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(final int position) {
            binding.setMovie(movieList.get(position));
            binding.executePendingBindings();
            binding.movieLikeButton.setLiked(movieList.get(position).isLiked());
            binding.cardMovie.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMovieClicked(movieList.get(position));
                }
            });
        }
    }
}
