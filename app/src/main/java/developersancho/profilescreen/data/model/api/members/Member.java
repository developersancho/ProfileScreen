package developersancho.profilescreen.data.model.api.members;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("profilePictureUrl")
    @Expose
    private String profilePictureUrl;
    @SerializedName("profileBackgroundPictureUrl")
    @Expose
    private String profileBackgroundPictureUrl;

    public Member() {
    }

    public Member(int userId, String username, String profilePictureUrl, String profileBackgroundPictureUrl) {
        this.userId = userId;
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.profileBackgroundPictureUrl = profileBackgroundPictureUrl;
    }


    public Member(String username, String profilePictureUrl, String profileBackgroundPictureUrl) {
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.profileBackgroundPictureUrl = profileBackgroundPictureUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
