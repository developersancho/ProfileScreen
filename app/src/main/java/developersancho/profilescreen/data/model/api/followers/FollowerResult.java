package developersancho.profilescreen.data.model.api.followers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowerResult {
    @SerializedName("ItemCount")
    @Expose
    private int itemCount;
    @SerializedName("Items")
    @Expose
    private List<Follower> items = null;

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<Follower> getItems() {
        return items;
    }

    public void setItems(List<Follower> items) {
        this.items = items;
    }
}
