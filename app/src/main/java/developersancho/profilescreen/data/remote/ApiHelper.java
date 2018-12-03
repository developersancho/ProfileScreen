package developersancho.profilescreen.data.remote;

import developersancho.profilescreen.data.remote.api.IProfileService;
import developersancho.profilescreen.data.remote.api.NetworkManager;
import developersancho.profilescreen.utils.AppConstant;

public class ApiHelper implements IApiHelper {
    @Override
    public IProfileService createProfileService() {
        return NetworkManager.getClient(AppConstant.BASE_URL).create(IProfileService.class);
    }
}
