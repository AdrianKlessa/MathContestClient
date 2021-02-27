package entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import java.util.HashSet;
import java.util.Set;
import org.joda.time.LocalDate;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refEventId")
public class Event {


    private int id;


    private String name;


    private String country;


    private String city;


    private LocalDate date;


    Set<Topic> topics = new HashSet<>();


    private Set<Award> awards = new HashSet<>();

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> membgers) {
        this.members = membgers;
    }


    private Set<Member> members = new HashSet<>();

    public Event(String name, String country, String city, String date){
        this.name=name;
        this.country=country;
        this.city=city;
        this.date=new LocalDate(date);
    }

    public Event(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    private void removeFromOthers(){
        for (Member member : this.getMembers()){
            member.getEvents().remove(this);
        }

        for(Topic topic : this.getTopics()){
            topic.getEvents().remove(this);
        }

        for(Award award : this.getAwards()){
            award.setEvent(null);
        }
    }
}
