import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PasswordAlert {

    public static void display(){
        Stage window = new Stage();

        Connection connection = new Connection();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Please provide your credentials");
        window.setMinWidth(250);
        window.setMinHeight(250);
        Label label = new Label("Please input your password");
        TextField fieldUsername = new TextField();
        fieldUsername.setPromptText("username");
        TextField fieldPassword = new TextField();
        fieldPassword.setPromptText("password");
        Button buttonLogin = new Button("Log in");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,fieldUsername,fieldPassword,buttonLogin);
        layout.setAlignment(Pos.CENTER);
        buttonLogin.setOnAction(e->{
            String username = fieldUsername.getText();
            String password = fieldPassword.getText();
            String response="";
            try {
                response = connection.GETAuthenticated("http://localhost:6161/events/events.json",username,password);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            if(response.contains("\"status\":401")){
              label.setText("Incorrect password. Please try again");
            }else{
              Main.root.setContent(new MyTable("Events",username,password));
              window.close();
            };

        });
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
}
