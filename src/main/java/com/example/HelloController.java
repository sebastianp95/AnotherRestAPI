package com.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


@RestController
public class HelloController {

    private static int id=0;


     @RequestMapping( value = "/newGreeting", method = RequestMethod.POST)
    public Greeting newGreeting(){
     Greeting greeting= new Greeting (1, " Hello");
        return  greeting;
    }



    @RequestMapping(value="/getHighestGreeting", method = RequestMethod.POST)
    public Greeting getHighestGreeting(@RequestBody List<Greeting> list){

     Greeting n= list.get(0);

        for (Greeting x : list) {
            if (x.getId() > n.getId())
                n = x;
        }

     return n;

    }
    @RequestMapping(value="/updateGreeting", method =RequestMethod.PUT)
    public Greeting updateGreeting(@RequestBody String newMessage) throws IOException {

            ObjectMapper mapper = new ObjectMapper();
            File file= new File("./message.txt");
            String message = org.apache.commons.io.FileUtils.readFileToString(file, Charset.forName("UTF-8"));

            Greeting greeting = mapper.readValue(message, Greeting.class);

            greeting.setContent(newMessage);

            mapper.writeValue(new File("./message.txt"), greeting);

            return greeting;

        }

    @RequestMapping(value="/deleteGreeting", method =RequestMethod.DELETE)
    public void deleteGreeting(@RequestBody int id) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        File file= new File("./message.txt");
        String message = org.apache.commons.io.FileUtils.readFileToString(new File ("./message.txt"), "UTF-8");

        Greeting greeting = mapper.readValue(message, Greeting.class);

       if(greeting.getId()== id){
           org.apache.commons.io.FileUtils.writeStringToFile( new File ("./message.txt"), "", "UTF-8");
       }

    }

    @RequestMapping( value = "/createGreeting2" , method = RequestMethod.POST)
    public Greeting createGreeting2(@RequestBody String name) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
      //  File file= new File("./message.txt");
        String message = org.apache.commons.io.FileUtils.readFileToString(new File("./message.txt"), "UTF-8");

        Greeting greeting = mapper.readValue(message, Greeting.class);
//        Greeting newGreeting = new Greeting (1, name);

        return greeting;
    }
    @RequestMapping( value = "/createGreeting" , method = RequestMethod.POST)
    public Greeting createGreeting(@RequestBody String name) throws IOException{

        //        File file= new File("./message.txt");
//        org.apache.commons.io.FileUtils.writeStringToFile(file , newGreeting.getContent());

        ObjectMapper mapper = new ObjectMapper();
        Greeting newGreeting = new Greeting (1, name);

       mapper.writeValue(new File("./message.txt"), newGreeting);

        return newGreeting;
    }
    @RequestMapping( value = "/getGreeting", method = RequestMethod.GET)
    public Greeting greeting() throws  IOException{
        ObjectMapper mapper = new ObjectMapper();
        File file= new File("./message.txt");
        String message = org.apache.commons.io.FileUtils.readFileToString(file, "UTF-8");
        Greeting greeting = mapper.readValue(message, Greeting.class);
        return greeting;

    }


    @RequestMapping(value= "/getHackerNews", method = RequestMethod.GET)
    public String getHackerNews(){
System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://news.ycombinator.com");
        List<WebElement> list = driver.findElements((By.className("storylink")));
        ArrayList<String> stories = new ArrayList<String>();
        for(WebElement e: list){
            stories.add(e.getText());
        }
        driver.close();
        return stories.toString();

    }
    @RequestMapping( value = "/createGreeting1" , method = RequestMethod.POST)
    public Greeting createGreeting1(@RequestBody String name){
        Greeting newGreeting = new Greeting (1, name);
        return newGreeting;
    }
    @RequestMapping( value = "/greeting1", method = RequestMethod.GET)
    public Greeting greeting1(){
        return new Greeting (id++, " Hello world");

    }
    @RequestMapping(value="/sameGreeting", method= RequestMethod.POST)
    public Greeting sameGreeting(@RequestBody Greeting greeting){

        return greeting;
    }
}
