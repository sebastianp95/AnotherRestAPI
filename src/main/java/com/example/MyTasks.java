package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyTasks {

    private RestTemplate restTemplate = new RestTemplate();

    //    @Scheduled(fixedRate = 2000)
//    public void  getGreeting(){
//        String url = "http://localhost:8080/getGreeting";
//        Greeting g = restTemplate.getForObject(url, Greeting.class);
//        System.out.println(g.getContent());
//    }
//    @Scheduled(fixedRate = 5000)
//    public void  postGreeting(){
//        String url = "http://localhost:8080/createGreeting";
//        Greeting g = restTemplate.postForObject(url, " new message ",Greeting.class);
//        System.out.println(g.getContent());
//    }
//    @Scheduled(cron ="*/5 * * * * *")
//    public void  greeting(){
//        String url = "http://localhost:8080/getGreeting";
//        Greeting g = restTemplate.getForObject(url, Greeting.class);
//        System.out.println(g.getContent());
//    }
//    @Scheduled(cron = "*/5 * * * * *")
//    public Greeting putGreeting() {
//        String url = "http://localhost:8080/getGreeting";
//        Greeting g = restTemplate.getForObject(url, Greeting.class);
//        System.out.println(g.getContent());
//
//        if (g.getContent().equals("Hello world"))
//            g.setContent("Bye world");
//        else
//            g.setContent("Hello world");
//
//        String url2 = "http://localhost:8080/updateGreeting";
//        restTemplate.put(url2, g.getContent());
//
//
//        return g;
//    }
//
//
//
//    @Scheduled(fixedRate = 5000)
//    public void  periodicTask1(){
//        System.out.println("The time  is now" + new Date());
//    }
}
