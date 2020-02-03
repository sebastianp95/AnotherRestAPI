package com.example;

public class Greeting implements Comparable<Greeting> {

    private  long id;
    private   String content;

    public Greeting() {
        this.id = 0;
        this.content = "";
    }
public Greeting(long id, String content){
    this.id = id;
    this.content = content;
}
    public long getId() {
        return id;
    }
    public String getContent(){
        return content;
    }
    public void setId() {
        this.id=id;
    }
    public void setContent( String content){
      this.content= content;
    }

    @Override
    public int compareTo(Greeting o) {
        return (int) (this.id -o.getId());
    }


}
