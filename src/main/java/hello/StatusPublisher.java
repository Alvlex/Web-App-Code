package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.*;
import java.util.Date;


@Component
public class StatusPublisher {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticsCounter statisticsCounter;

    OperatingSystemMXBean osBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private double cpuLoad;
    private long cpuLoadTime;
    private Date startDate;

    private ObjectMapper objectMapper = new ObjectMapper();
    private String applicationName = "Web Service App";

    private InetAddress ip;

    int port;
    private long memUsed;

    private String hostname;
    private String version;

    public String getHostname(){
        return hostname;
    }

    public String getIp(){
        return ip.getHostAddress();
    }

    @PostConstruct
    public void init() throws IOException {
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        long startTime = runtimeBean.getStartTime();
        startDate = new Date(startTime);
        port = 21320;
        ip = InetAddress.getLocalHost();
        hostname = ip.getHostName();
        version = System.getProperty("java.version");
        memUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        cpuLoadTime = osBean.getProcessCpuTime()/1000000000; //in secs
        cpuLoad = osBean.getSystemLoadAverage();
    }

    @Scheduled(fixedRate = 5000)
    public void publishStatus() {
        Date continuousDate = new Date();


        log.debug("--- About to publish a message ---");

        try {
            DatagramSocket clientSocket = new DatagramSocket();

            Status status = new Status();
            status.setCpuLoadTime(cpuLoadTime);
            status.setCpuLoad(cpuLoad);
            status.setMemUsed(memUsed);
            status.setApplicationName(applicationName);
            status.setStartDate(startDate);
            status.setNumRequests(statisticsCounter.getCount());
            status.setIp(ip.getHostAddress());
            status.setHostname(hostname);
            status.setContinuousDate(continuousDate);
            status.setJRE(version);
            String toPublish = objectMapper.writeValueAsString(status);
            log.info(toPublish);

            byte[] sendData = toPublish.getBytes();

            InetAddress address = InetAddress.getByName("Monitor");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            clientSocket.send(sendPacket);
            clientSocket.close();

            log.debug("--- Done publishing a message ---");
        }
        catch(Exception e){
            log.error("Error publishing status", e);
        }
    }

}
