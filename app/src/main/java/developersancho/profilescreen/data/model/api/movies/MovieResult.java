package developersancho.profilescreen.data.model.api.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult {

    @SerializedName("ItemCount")
    @Expose
    private int itemCount;
    @SerializedName("Items")
    @Expose
    private List<Movie> items = null;

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;
    }
}
