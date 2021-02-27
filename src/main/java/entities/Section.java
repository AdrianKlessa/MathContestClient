package entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import java.util.HashSet;
import java.util.Set;



@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="refSectionId")

public class Section {


    public Section(){

    }

    public Section(String name){
        this.name=name;
    }

    private int sectionId;


    private String name;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    private Set<Member> members = new HashSet<>();

    private void removeFromOthers(){
        for (Member member: this.getMembers()){
            member.setSection(null);
        }


    }
}
