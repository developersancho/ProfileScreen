package developersancho.profilescreen.ui.profile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import developersancho.profilescreen.R;
import developersancho.profilescreen.data.model.api.followers.Follower;
import developersancho.profilescreen.databinding.ItemRowFollowerBinding;
import developersancho.profilescreen.ui.base.BaseViewHolder;

public class ProfileAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Follower> followerList = new ArrayList<>();
    private Context context;
    private ProfileAdapterListener listener;
    private LayoutInflater layoutInflater;

    public ProfileAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemRowFollowerBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_row_follower, parent, false);
        return new ProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return followerList.size();
    }

    public void setFollowers(List<Follower> followers) {
        this.followerList = followers;
        notifyDataSetChanged();
    }

    public void setProfileAdapterListener(ProfileAdapterListener listener) {
        this.listener = listener;
    }


    public interface ProfileAdapterListener {
        void onFollowerClicked(Follower follower);
    }

    public class ProfileViewHolder extends BaseViewHolder {
        private ItemRowFollowerBinding binding;

        public ProfileViewHolder(ItemRowFollowerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(final int position) {
            binding.setFollower(followerList.get(position));
            binding.executePendingBindings();
            /*Glide.with(context).
                    load(followerList.get(position).getProfilePictureUrl()).
                    apply(RequestOptions.placeholderOf(R.drawable.load)).
                    into(binding.imgFollower);*/

            binding.cardFollower.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFollowerClicked(followerList.get(position));
                }
            });
        }
    }
}
