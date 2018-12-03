package developersancho.profilescreen.data.model.api.movies;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class Movie implements Comparable<Movie> {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profilePictureUrl")
    @Expose
    private String profilePictureUrl;
    @SerializedName("isLiked")
    @Expose
    private boolean isLiked;
    @SerializedName("likeCount")
    @Expose
    private int likeCount;
    @SerializedName("createDate")
    @Expose
    private String createDate;

    public Movie() {
    }

    public Movie(String name, String profilePictureUrl, boolean isLiked, int likeCount, String createDate) {
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.isLiked = isLiked;
        this.likeCount = likeCount;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public int compareTo(@NonNull Movie t) {
        return getName().compareTo(t.getName());
    }

    public static class LikeCountComparator implements Comparator<Movie> {
        public int compare(Movie p1, Movie p2) {
            double dist1 = Integer.valueOf(p1.getLikeCount());
            double dist2 = Integer.valueOf(p2.getLikeCount());

            if (dist1 == dist2)
                return 0;
            else if (dist2 > dist1)
                return 1;
            else
                return -1;
        }
    }

}
