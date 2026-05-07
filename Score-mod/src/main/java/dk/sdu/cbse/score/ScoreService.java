package dk.sdu.cbse.score;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoreService
{
    private final RestTemplate restTemplate = new RestTemplate();

    public int incrementAndGetScore(int id, int increment) {
        String url = "http://localhost:8080/score/" + id + "?increment=" + increment;
        try {
            ResponseEntity<Integer> resp = restTemplate.exchange(url, org.springframework.http.HttpMethod.POST, null, Integer.class);
            if(resp.getBody() == null) {
                System.out.println("incrementAndGetScore returned null");
                return -1;
            } else {
                return resp.getBody();
            }
        } catch (Exception e) {
            System.out.println("incrementAndGetScore failed: " + e.getMessage());
            return -1;
        }
    }

    public int getScore(int id) {
        String url = "http://localhost:8080/score/" + id;
        try {
            ResponseEntity<Integer> resp = restTemplate.getForEntity(url, Integer.class);
            if (resp.getStatusCode() != HttpStatus.OK || resp.getBody() == null) {
                return -1;
            }
            return resp.getBody();
        } catch (Exception e) {
            return -1;
        }
    }

}
