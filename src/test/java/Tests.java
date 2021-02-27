import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Tests {

    Connection connection= new Connection();



    @Test
    public void getMembersTest() throws Exception {
        String output = connection.GET("http://localhost:6161/api/members.json");
        boolean contains = (output.contains("Adrian")&&(output.contains("Klessa")));
        Assert.assertTrue(contains);


    }

    @Test
    public void getTopicsTest() throws Exception {
        String output = connection.GET("http://localhost:6161/api/topics.json");
        boolean contains =output.contains("Differential equations");
        Assert.assertTrue(contains);
            }

    @Test
    public void getAwardsTest() throws Exception {
        String output = connection.GET("http://localhost:6161/api/awards.json");
        boolean contains = (output.contains("Szymon")&&(output.contains("Riemanna")));
        Assert.assertTrue(contains);


    }

    @Test
    public void getEventsTest() throws Exception {
        String output = connection.GET("http://localhost:6161/api/events.json");
        boolean contains = output.contains("World math tournament")&&output.contains("Deutsche Mathematische Kompetition");
        Assert.assertTrue(contains);


    }

    @Test
    public void getSectionsTest() throws Exception {
        String output = connection.GET("http://localhost:6161/api/sections.json");
        boolean contains = output.contains("Polska sekcja matematyczna");
        Assert.assertTrue(contains);


    }


    @Test
    public void test1xml() throws Exception{
        String outputBefore = connection.GET("http://localhost:6161/api/members.json");

        connection.POSTxml("http://localhost:6161/api/member.xml", Files.readString(Path.of("test1.xml")));
        String outputAfter = connection.GET("http://localhost:6161/api/members.json");

        Assert.assertTrue(!outputBefore.contains("Tester")&&outputAfter.contains("Tester"));

    }

    @Test
    public void test1json() throws Exception{
        String outputBefore = connection.GET("http://localhost:6161/api/members.xml");

        connection.POSTjson("http://localhost:6161/api/member.json", Files.readString(Path.of("test1.json")));
        String outputAfter = connection.GET("http://localhost:6161/api/members.xml");

        Assert.assertTrue(!outputBefore.contains("Testing2")&&outputAfter.contains("Testing2"));

    }

    @Test
    public void query1() throws Exception{
        String output = connection.GET("http://localhost:6161/query1.json");
        boolean contains = (output.contains("Gromada"));
        Assert.assertTrue(contains);

    }

    @Test
    public void query2() throws Exception{
        String output = connection.GET("http://localhost:6161/query2.xml");
        boolean contains = (output.contains("Nagroda Riemanna"));
        Assert.assertTrue(contains);

    }

    @Test
    public void query3Page1() throws Exception{
        String output = connection.GET("http://localhost:6161/query3/0.json");
        boolean contains = (output.contains("Gromada")&&!output.contains("Zorro"));
        Assert.assertTrue(contains);

    }

    @Test
    public void query3Page2() throws Exception{
        String output = connection.GET("http://localhost:6161/query3/1.json");
        boolean contains = (output.contains("Adam"));
        Assert.assertTrue(contains);

    }

    @Test
    public void query4() throws Exception{
        String output = connection.GET("http://localhost:6161/query4.xml");


        //Gotta remove non-numbers and whitespaces
        output = output.replaceAll("[^0-9]+", " ");
        output= output.replaceAll("\\s+","");
        int number = Integer.parseInt(output);
        boolean contains = (number>=1);
        Assert.assertTrue(contains);

    }

    @Test
    public void query5() throws Exception{
        String output = connection.GET("http://localhost:6161/query5.json");

        boolean contains = (output.contains("Berlin"));
        Assert.assertTrue(contains);

    }

    @Test
    public void writingTest() throws Exception{
        String output = connection.GET("http://localhost:6161/query5.json");
        connection.toFile(output,"testFile.txt");
        Scanner scanner = new Scanner(new File("testFile.txt"));

        boolean contains = false;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if(data.contains("Berlin")){
                contains=true;
            }
        }


        Assert.assertTrue(contains);
    }


}
