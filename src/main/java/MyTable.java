import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import entities.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MyTable extends GridPane {

    String title;
    String username;
    String password;
    String json;
    Connection connection = new Connection();
    Button back;
    Button update;
    public MyTable(String title){
        super();
        this.title=title;
        settings();
        update();
    }

    public MyTable(String title, String username, String password ){
        super();
        this.title=title;
        this.username=username;
        this.password=password;
        settings();
        update();
    }

    public void settings(){
        this.setVgap(30);
        this.setHgap(30);
        this.setPadding(new Insets(15, 30, 15, 30));

    }
    public void update(){
        this.getChildren().clear();
        back = new Button("back");
        update = new Button("update");

        ObjectMapper objectMapper = new ObjectMapper();
        this.add(back,0,0);
        this.add(update,1,0);

        update.setOnAction(e->{
            update();
        });
        back.setOnAction(e->{
            Main.root.setContent(Menu.pane);
        });

        //Members case
        if(title.compareTo("Members")==0){
            try {
                this.add(new Text("id"),0,1);
                this.add(new Text("name"),1,1);
                this.add(new Text("surname"),2,1);
                this.add(new Text("sectionId"),3,1);
                json= connection.GET("http://localhost:6161/api/members.json");

                objectMapper.registerModule(new JodaModule());
                List<Member> members = objectMapper.readValue(json, new TypeReference<List<Member>>(){});
                int i =0;
                for(Member member : members){
                    i++;
                    String id = String.valueOf(member.getId());
                    String name = member.getName();
                    String surname = member.getSurname();
                    String sectionId = "";
                    Button buttonDelete = new Button("Delete");
                    buttonDelete.setOnAction(e->{
                        try {
                            connection.DELETE("http://localhost:6161/api/deleteMember"+id);
                            this.update();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                    if(member.getSection()!=null){
                        sectionId= String.valueOf(member.getSection().getSectionId());
                    }

                    this.add(new Text(id),0,1+i);
                    this.add(new Text(name),1,1+i);
                    this.add(new Text(surname),2,1+i);
                    this.add(new Text(sectionId),3,1+i);
                    this.add(buttonDelete,4,1+i);
                }
                i++;
                //Text fields for user input

                TextField fieldName = new TextField();
                fieldName.setPromptText("name");

                TextField fieldSurname = new TextField();
                fieldSurname.setPromptText("Surname");

                Button sendButton = new Button("Send");
                sendButton.setOnAction(e->{
                    String upName = fieldName.getText();
                    String upSurname = fieldSurname.getText();


                    String jsonUp ="{"+ "\"name\": \""+upName+"\", \"surname\": \""+upSurname+"\"}";
                    try {
                        connection.POSTjson("http://localhost:6161/api/member.json",jsonUp);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    update();
                });

                this.add(fieldName,1,i+2);
                this.add(fieldSurname,2,i+2);
                this.add(sendButton,3,i+2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(title.compareTo("Events")==0){
                try {
                    this.add(new Text("id"),0,1);
                    this.add(new Text("name"),1,1);
                    this.add(new Text("country"),2,1);
                    this.add(new Text("city"),3,1);
                    this.add(new Text("date"),4,1);
                    json= connection.GETAuthenticated("http://localhost:6161/events/events.json",username,password);

                    objectMapper.registerModule(new JodaModule());
                    List<Event> events = objectMapper.readValue(json, new TypeReference<List<Event>>(){});
                    int i =0;
                    for(Event event: events){
                        i++;
                        String id = String.valueOf(event.getId());
                        String name = event.getName();
                        String country = event.getCountry();
                        String city = event.getCity();
                        String date = event.getDate().toString();
                        Button buttonDelete = new Button("Delete");
                        buttonDelete.setOnAction(e->{
                            try {
                                connection.DELETEAuthenticated("http://localhost:6161/events/deleteEvent"+id,username,password);
                                this.update();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        });

                        this.add(new Text(id),0,1+i);
                        this.add(new Text(name),1,1+i);
                        this.add(new Text(country),2,1+i);
                        this.add(new Text(city),3,1+i);
                        this.add(new Text(date),4,1+i);
                        this.add(buttonDelete,5,1+i);
                    }
                    i++;
                    //Text fields for user input

                    TextField fieldName = new TextField();
                    fieldName.setPromptText("name");

                    TextField fieldCountry = new TextField();
                    fieldCountry.setPromptText("country");

                    TextField fieldCity = new TextField();
                    fieldCity.setPromptText("city");

                    TextField fieldDate = new TextField();
                    fieldDate.setPromptText("Date");

                    Button sendButton = new Button("Send");
                    sendButton.setOnAction(e->{
                        String upName = fieldName.getText();
                        String upCountry = fieldCountry.getText();
                        String upCity = fieldCity.getText();
                        String upDate = fieldDate.getText();


                        String jsonUp ="{"+ "\"name\": \""+upName+"\", \"country\": \""+upCountry+"\", \"city\": \""+upCity+"\", \"date\": \""+upDate+"\" }";
                        try {
                            connection.POSTjsonAuthenticated("http://localhost:6161/events/event.json",jsonUp,username,password);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        update();
                    });

                    this.add(fieldName,1,i+2);
                    this.add(fieldCountry,2,i+2);
                    this.add(fieldCity,3,i+2);
                    this.add(fieldDate,4,i+2);
                    this.add(sendButton,5,i+2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(title.compareTo("Topics")==0){
            try {
                this.add(new Text("id"),0,1);
                this.add(new Text("name"),1,1);
                json= connection.GET("http://localhost:6161/api/topics.json");

                objectMapper.registerModule(new JodaModule());
                List<Topic> topics = objectMapper.readValue(json, new TypeReference<List<Topic>>(){});
                int i =0;
                for(Topic topic : topics){
                    i++;
                    String id = String.valueOf(topic.getId());
                    String name = topic.getName();
                    Button buttonDelete = new Button("Delete");
                    buttonDelete.setOnAction(e->{
                        try {
                            connection.DELETE("http://localhost:6161/api/deleteTopic"+id);
                            this.update();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });

                    this.add(new Text(id),0,1+i);
                    this.add(new Text(name),1,1+i);
                    this.add(buttonDelete,2,1+i);
                }
                i++;
                //Text fields for user input

                TextField fieldName = new TextField();
                fieldName.setPromptText("name");

                Button sendButton = new Button("Send");
                sendButton.setOnAction(e->{
                    String upName = fieldName.getText();


                    String jsonUp ="{"+ "\"name\": \""+upName+"\"}";
                    try {
                        connection.POSTjson("http://localhost:6161/api/topic.json",jsonUp);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    update();
                });

                this.add(fieldName,1,i+2);
                this.add(sendButton,2,i+2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(title.compareTo("Awards")==0){
            try {
                this.add(new Text("id"),0,1);
                this.add(new Text("name"),1,1);
                this.add(new Text("event Id"),2,1);
                this.add(new Text("member Id"),3,1);
                json= connection.GET("http://localhost:6161/api/awards.json");

                objectMapper.registerModule(new JodaModule());
                List<Award> awards = objectMapper.readValue(json, new TypeReference<List<Award>>(){});
                int i =0;
                for(Award award : awards){
                    i++;
                    String id = String.valueOf(award.getId());
                    String name = award.getName();
                    String eventId="";
                    String memberId="";

                    Button buttonDelete = new Button("Delete");
                    buttonDelete.setOnAction(e->{
                        try {
                            connection.DELETE("http://localhost:6161/api/deleteAward"+id);
                            this.update();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                    if(award.getEvent()!=null){
                        eventId= String.valueOf(award.getEvent().getId());
                    }
                    if(award.getMember()!=null){
                        memberId=String.valueOf(award.getMember().getId());
                    }

                    this.add(new Text(id),0,1+i);
                    this.add(new Text(name),1,1+i);
                    this.add(new Text(eventId),2,1+i);
                    this.add(new Text(memberId),3,1+i);
                    this.add(buttonDelete,4,1+i);
                }
                i++;
                //Text fields for user input

                TextField fieldName = new TextField();
                fieldName.setPromptText("name");

                Button sendButton = new Button("Send");
                sendButton.setOnAction(e->{
                    String upName = fieldName.getText();


                    String jsonUp ="{"+ "\"name\": \""+upName+"\"}";
                    try {
                        connection.POSTjson("http://localhost:6161/api/award.json",jsonUp);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    update();
                });

                this.add(fieldName,1,i+2);
                this.add(sendButton,2,i+2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(title.compareTo("Sections")==0){
            try {
                this.add(new Text("id"),0,1);
                this.add(new Text("name"),1,1);
                json= connection.GET("http://localhost:6161/api/sections.json");

                objectMapper.registerModule(new JodaModule());
                List<Section> sections = objectMapper.readValue(json, new TypeReference<List<Section>>(){});
                int i =0;
                for(Section section : sections){
                    i++;
                    String id = String.valueOf(section.getSectionId());
                    String name = section.getName();
                    Button buttonDelete = new Button("Delete");
                    buttonDelete.setOnAction(e->{
                        try {
                            connection.DELETE("http://localhost:6161/api/deleteSection"+id);
                            this.update();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });

                    this.add(new Text(id),0,1+i);
                    this.add(new Text(name),1,1+i);
                    this.add(buttonDelete,2,1+i);
                }
                i++;
                //Text fields for user input

                TextField fieldName = new TextField();
                fieldName.setPromptText("name");

                Button sendButton = new Button("Send");
                sendButton.setOnAction(e->{
                    String upName = fieldName.getText();


                    String jsonUp ="{"+ "\"name\": \""+upName+"\"}";
                    try {
                        connection.POSTjson("http://localhost:6161/api/section.json",jsonUp);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    update();
                });

                this.add(fieldName,1,i+2);
                this.add(sendButton,2,i+2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    public String JSONremoveLayers(String json){
        String output;
        int layerNumber=0;
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<json.length()-1;i++){
            char current= json.charAt(i);
            if(Character.compare('[',current)==0){
                layerNumber++;
            }
            if(Character.compare(']',current)==0){
                layerNumber--;
            }
            if(layerNumber<=1){
                builder.append(current);
            }
        }
        output=builder.toString();
        return output;
    }
}
