package developersancho.profilescreen.ui.profile;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import developersancho.profilescreen.BR;
import developersancho.profilescreen.R;
import developersancho.profilescreen.data.model.api.members.Member;
import developersancho.profilescreen.databinding.ActivityProfileBinding;
import developersancho.profilescreen.ui.movie.popular.PopularMoviesFragment;
import developersancho.profilescreen.ui.movie.recent.RecentMoviesFragment;
import developersancho.profilescreen.ui.base.BaseActivity;
import developersancho.profilescreen.utils.NetworkUtils;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding, ProfileViewModel> implements IProfileNavigator {
    private ProfileViewModel viewModel;
    private ActivityProfileBinding binding;
    private ProfileAdapter adapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    public ProfileViewModel getViewModel() {
        return viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel.setNavigator(this);
        DebugDB.getAddressLog();
        setUI();
        subscribeToMember();
        subscribeToFollower();
    }


    private void setUI() {
        setupViewPager(binding.viewPagerProfile);
        binding.tabDachshundProfile.setupWithViewPager(binding.viewPagerProfile);
        setupTabText();

        adapter = new ProfileAdapter(ProfileActivity.this);
        //binding.recyclerviewFollowers.addItemDecoration(new DividerItemDecoration(ProfileActivity.this, LinearLayoutManager.HORIZONTAL));
        binding.recyclerviewFollowers.setLayoutManager(new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerviewFollowers.setHasFixedSize(true);
        binding.recyclerviewFollowers.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerviewFollowers.setAdapter(adapter);
    }

    private void subscribeToMember() {

        if (NetworkUtils.isNetworkConnected(ProfileActivity.this)) {
            viewModel.fetchMember().observe(this, member -> {
                setMember(member);
            });
        } else {
            viewModel.fetchMemberRoom().observe(this, member -> {
                setMember(member);
            });
        }


        viewModel.fetchFollowers().observe(this, followers -> {
            adapter.setFollowers(followers);
        });
    }

    private void subscribeToFollower() {
        if (NetworkUtils.isNetworkConnected(ProfileActivity.this)) {
            viewModel.fetchFollowers().observe(this, followers -> {
                adapter.setFollowers(followers);
            });
        } else {
            viewModel.fetchFollowersRoom().observe(this, followers -> {
                adapter.setFollowers(followers);
            });
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new PopularMoviesFragment(), getString(R.string.popular));
        adapter.addFrag(new RecentMoviesFragment(), getString(R.string.recent));
        viewPager.setAdapter(adapter);
    }

    private void setupTabText() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_text, null);
        tabOne.setText(getString(R.string.popular));
        binding.tabDachshundProfile.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_text, null);
        tabTwo.setText(getString(R.string.recent));
        binding.tabDachshundProfile.getTabAt(1).setCustomView(tabTwo);

    }

    private void setMember(Member member) {
        binding.textProfileName.setText(member.getUsername());
        Glide.with(ProfileActivity.this)
                .load(member.getProfilePictureUrl())
                .apply(RequestOptions.placeholderOf(R.drawable.load))
                .into(binding.imgProfile);

        Glide.with(ProfileActivity.this)
                .load(member.getProfileBackgroundPictureUrl())
                .apply(RequestOptions.placeholderOf(R.drawable.load))
                .into(binding.imgProfileBackground);
    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void showNotFoundMessage() {

    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
