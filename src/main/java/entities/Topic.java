package entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import java.util.HashSet;
import java.util.Set;


@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refTopicId")
public class Topic {


    public Topic(String name){
        this.name=name;
    }

    public Topic(){

    }

    private int id;


    private String name;

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

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }


    private Set<Event> events = new HashSet<>();


    private void removeFromOthers(){
        for(Event event : this.getEvents()){
            event.getTopics().remove(this);
        }
    }
}
