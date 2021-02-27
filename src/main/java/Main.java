import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

import static javafx.application.Application.launch;

public class Main extends Application {


    public static Stage primaryStage;
    public static ScrollPane root;
    public static void main(String[] args) throws Exception {
           launch(args);

        /*

            Connection connection = new Connection();


        System.out.println(connection.GET("http://localhost:6161/query1.json"));
        System.out.println(connection.POSTjson("http://localhost:6161/api/member.json", Files.readString(Path.of("test1.json"))));
        System.out.println(connection.POSTxml("http://localhost:6161/api/member.xml", Files.readString(Path.of("test1.xml"))));


        System.out.println(connection.GETAuthenticated("http://localhost:6161/events/events.json","asdasads","błędne hasło"));
        System.out.println(connection.GET("http://localhost:6161/events/events.json"));
        System.out.println(connection.GETAuthenticated("http://localhost:6161/events/events.json","admin","secret"));
        System.out.println(connection.DELETE("http://localhost:6161/api/deleteMember15"));
            */


        }

    public void start(Stage mainStage) throws Exception {
        Scene menuScene;

        primaryStage=mainStage;
        root = new Menu();
        menuScene = new Scene(root, 1500, 800);


        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Math client");
        primaryStage.show();



    }
    }
