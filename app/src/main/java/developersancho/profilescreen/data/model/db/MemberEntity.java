package developersancho.profilescreen.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "member_table")
public class MemberEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int Id;
    private String username;
    private String profilePictureUrl;
    private String profileBackgroundPictureUrl;

    public MemberEntity(String username, String profilePictureUrl, String profileBackgroundPictureUrl) {
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.profileBackgroundPictureUrl = profileBackgroundPictureUrl;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getProfileBackgroundPictureUrl() {
        return profileBackgroundPictureUrl;
    }

    public void setProfileBackgroundPictureUrl(String profileBackgroundPictureUrl) {
        this.profileBackgroundPictureUrl = profileBackgroundPictureUrl;
    }
}
