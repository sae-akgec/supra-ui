package in.saeakgec.supra.service;

import in.saeakgec.supra.App;
import in.saeakgec.supra.model.Token;
import in.saeakgec.supra.model.User;
import in.saeakgec.supra.util.Constants;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.prefs.Preferences;

public class AuthService {
    public boolean signin(User user){
        Constants constants = new Constants();
        final String url = constants.Domain + "/auth/signin";
        RestTemplate restTemplate = new RestTemplate();
        try{
            Token token = restTemplate.postForObject(url, user, Token.class);
            saveToken(token.getToken());
            System.out.println(getToken());
            return true;
        }catch (HttpClientErrorException exception){
            return false;
        }
    }

    public void saveToken(String token){
        Preferences prefs = Preferences.userNodeForPackage(App.class);
        prefs.put("token", "Bearer " + token);
    }

    public  String getToken(){
        Preferences prefs = Preferences.userNodeForPackage(App.class);
        return prefs.get("token",null);
    }
}
