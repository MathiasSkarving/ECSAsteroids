package dk.sdu.cbse.scoringservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ScoreController {
    private int scoreForPlayer1;
    private int scoreForPlayer2;

    public ScoreController() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ScoreController.class, args);
    }

    @GetMapping("/score/{id}")
    public int getScore(@PathVariable int id) {
        if(id == 1) {
            return scoreForPlayer1;
        } else if(id == 2) {
            return scoreForPlayer2;
        }
        return -1;
    }

    @PostMapping("/score/{id}")
    public int incrementScore(@RequestParam int increment, @PathVariable int id) {
        if(id == 1) {
            scoreForPlayer1 += increment;
            return scoreForPlayer1;
        } else if(id == 2) {
            scoreForPlayer2 += increment;
            return scoreForPlayer2;
        }
        return -1;
    }
}
