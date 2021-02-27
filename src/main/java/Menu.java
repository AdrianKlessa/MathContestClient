import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Menu extends ScrollPane {


    Connection connection = new Connection();
    public static GridPane pane = new GridPane();
    public static Text statisticText = new Text("Please wait a moment");
    public static boolean inMenu = true;

    public Menu(){
        super();
        boolean wrongPassword= false;
        //Pane properties setup
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(15, 30, 15, 30));
        //Timer for updating event count setup
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> updateEventCount()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //Setting up elements
        Button buttonMembers = new Button("Members");
        Button buttonTopics = new Button("Topics");
        Button buttonAwards = new Button("Awards");
        Button buttonSections = new Button("Sections");
        Button buttonEvents = new Button("Events");
        buttonEvents.setStyle("-fx-background-color: ff0000;");


        //Adding elements to the pane
        pane.add(statisticText,1,0);
        pane.add(buttonMembers,2,0);
        pane.add(buttonAwards,3,0);
        pane.add(buttonSections,4,0);
        pane.add(buttonTopics,5,0);
        pane.add(buttonEvents,2,2);

        buttonMembers.setOnAction(
                e->{
                    this.setContent(new MyTable("Members"));
                });

        buttonTopics.setOnAction(e->{
            this.setContent(new MyTable("Topics"));
        });

        buttonAwards.setOnAction(e->{
            this.setContent(new MyTable("Awards"));
        });

        buttonSections.setOnAction(e->{
            this.setContent(new MyTable("Sections"));
        });

        buttonEvents.setOnAction(
                e->{
                    PasswordAlert.display();
                }
        );



        //Finalization
        this.setContent(pane);


    }

    private void updateEventCount(){
        try{
            if(inMenu){
                String text = connection.GET("http://localhost:6161/numberOfEvents");
                statisticText.setText("Current number of events: "+ text);
            }


    } catch (Exception e) {
        e.printStackTrace();
    }
    }


}
