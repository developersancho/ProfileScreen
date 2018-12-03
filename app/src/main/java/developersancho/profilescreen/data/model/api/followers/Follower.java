package developersancho.profilescreen.data.model.api.followers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Follower {
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("profilePictureUrl")
    @Expose
    private String profilePictureUrl;

    public Follower(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
