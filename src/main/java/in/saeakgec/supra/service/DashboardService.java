package in.saeakgec.supra.service;

import in.saeakgec.supra.App;
import in.saeakgec.supra.model.Race;
import in.saeakgec.supra.util.Constants;
import in.saeakgec.supra.util.HeaderRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

public class DashboardService {
    List<ClientHttpRequestInterceptor> interceptors;
    RestTemplate restTemplate;
    String DOMAIN;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    public DashboardService(){
        interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new HeaderRequestInterceptor("Authorization",getToken()));
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(interceptors);
        Constants constants = new Constants();
        DOMAIN = constants.Domain;
    }

    public  String getToken(){
        Preferences prefs = Preferences.userNodeForPackage(App.class);
        return prefs.get("token",null);
    }

    public List<Race> getAdminRace(){

        String url = DOMAIN + "/api/v1/races/admin";
        Race[] forNow = restTemplate.getForObject(url, Race[].class);
        List<Race> races = new ArrayList<Race>(Arrays.asList(forNow));
        return races;
    }

    public Race getRace(long id){

        String url = DOMAIN + "/api/v1/races/admin/" + id;
        Race updated = restTemplate.getForObject(url, Race.class);
        return updated;
    }

    public boolean addAdminRace(Race race){
        String url = DOMAIN + "/api/v1/races/admin";
        try {
            restTemplate.postForObject(url, race, Race.class);
            return true;

        }catch (HttpClientErrorException exception){
            return false;
        }
    }

    public boolean updateAdminRace(Race race){
        String url = DOMAIN + "/api/v1/races/admin/update/" + race.getId();
        try {
            restTemplate.getForObject(url, Object.class);
            return true;

        }catch (HttpClientErrorException exception){
            return false;
        }
    }


}
