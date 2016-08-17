package hello;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by alexvanlint on 10/08/2016.
 */
@Component
public class Status {

    private String applicationName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss a z")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss a z")
    private Date continuousDate;

    private int numRequests;

    private String ip;
    private String hostname;
    private String JRE;
    private long cpuLoadTime;
    private long memUsed;
    private double cpuLoad;


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getNumRequests() {
        return numRequests;
    }

    public void setNumRequests(int numRequests) {
        this.numRequests = numRequests;
    }

    public String getIp(){
        return ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname){
        this.hostname = hostname;
    }

    public Date getContinuousDate(){
        return continuousDate;
    }

    public void setContinuousDate(Date continuousDate){
        this.continuousDate = continuousDate;
    }

    public String getJRE(){
        return JRE;
    }

    public void setJRE(String JRE){
        this.JRE = JRE;
    }

    public void setMemUsed(long memUsed){
        this.memUsed = memUsed;
    }
    public long getMemUsed(){
        return memUsed;
    }
    public double getCpuLoad(){
        return cpuLoad;
    }
    public void setCpuLoad(double cpuLoad){
        this.cpuLoad = cpuLoad;
    }
    public void setCpuLoadTime(long cpuLoadTime){
        this.cpuLoadTime = cpuLoadTime;
    }
    public long getCpuLoadTime() {
        return cpuLoadTime;
    }
}
