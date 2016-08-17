package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.simple.JSONObject;


@RestController
public class HelloController {

    @Autowired
    private StatisticsCounter statisticsCounter;

    @Autowired
    private StatusPublisher statusPublisher;

    @RequestMapping("/")
    public String index() {
        statisticsCounter.increment();
        String hostname = statusPublisher.getHostname();
        String ip = statusPublisher.getIp();

        return "(Application Name: Web Service App)  (Hostname: " + hostname + ")  (IP Address: " + ip + ")";
    }
}
