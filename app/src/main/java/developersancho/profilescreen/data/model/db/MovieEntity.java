package developersancho.profilescreen.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class MovieEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int Id;
    private String name;
    private String profilePictureUrl;
    private boolean isLiked;
    private int likeCount;
    private String createDate;

    public MovieEntity(String name, String profilePictureUrl, boolean isLiked, int likeCount, String createDate) {
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.isLiked = isLiked;
        this.likeCount = likeCount;
        this.createDate = createDate;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
}
