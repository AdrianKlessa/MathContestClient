package entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import java.util.HashSet;
import java.util.Set;


@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refMemberId")
public class Member {



    public Member(){

    }

    public Member(String name, String surname){
        this.name=name;
        this.surname=surname;
    }

    private int id;


    private String name;


    private String surname;


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }


    private Set<Award> awards = new HashSet<>();


    Set<Event> events = new HashSet<>();


    private Section section ;

    private void removeFromOthers(){
        for (Event event : this.getEvents()){
            event.getMembers().remove(this);
        }

        if(this.getSection()!=null){
           this.getSection().getMembers().remove(this);
        }

        if(this.getAwards()!=null){
            for(Award award : this.getAwards()){
                award.setMember(null);
            }
        }



    }
}
