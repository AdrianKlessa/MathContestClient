package entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refAwardId")

public class Award {


    private int id;


    private String name;


    public Award(){}

    public Award(String name){
        this.name=name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }



    public void setEvent(Event event) {
        this.event = event;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    private Event event;


    private Member member;


    private void removeFromOthers(){
        if(this.getEvent()!=null){
            this.getEvent().getAwards().remove(this);
        }

        if(this.getMember()!=null){
            this.getMember().getAwards().remove(this);
        }

    }
}
