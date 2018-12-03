package developersancho.profilescreen.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "follower_table")
public class FollowerEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int Id;
    private String profilePictureUrl;

    public FollowerEntity(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
