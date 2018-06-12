package in.saeakgec.supra.service;

import in.saeakgec.supra.model.Token;
import in.saeakgec.supra.model.User;
import in.saeakgec.supra.util.Constants;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class AuthService {
    public boolean signin(User user){
        Constants constants = new Constants();
        final String url = constants.Domain + "/auth/signin";
        RestTemplate restTemplate = new RestTemplate();
        try{
            Token token = restTemplate.postForObject(url, user, Token.class);
            return true;
        }catch (HttpClientErrorException exception){
            return false;
        }
    }
}
