package hu.brbrt.healthcheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api/healthcheck")
public class HealthCheckController {

    @RequestMapping(method = GET)
    public String healthcheck() {
        return "Sfinapp service is available!";
    }

}
