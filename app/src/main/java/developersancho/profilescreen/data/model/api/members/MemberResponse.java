package developersancho.profilescreen.data.model.api.members;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberResponse {
    @SerializedName("Result")
    @Expose
    private Member result;
    @SerializedName("IsSuccess")
    @Expose
    private boolean isSuccess;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;

    public Member getResult() {
        return result;
    }

    public void setResult(Member result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
