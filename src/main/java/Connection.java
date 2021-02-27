import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Connection {


    CloseableHttpClient httpClient = HttpClients.createDefault();


    public String GET(String url) throws Exception {
        HttpGet request = new HttpGet(url);


        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                return result;
            }
        }
        return null;
    }

    public String POSTjson(String url, String body) throws Exception{
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(body);
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity rEntity = response.getEntity();
            if (rEntity != null) {
                // return it as a String
                String result = EntityUtils.toString(rEntity);
                return result;
            }
        }

        return null;
    }



    public String DELETE(String url) throws Exception{
        HttpDelete delete = new HttpDelete(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(delete)) {

            HttpEntity rEntity = response.getEntity();
            if (rEntity != null) {
                // return it as a String
                String result = EntityUtils.toString(rEntity);
                return result;
            }
        }

        return null;
    }



    public String DELETEAuthenticated(String url, String username, String password) throws Exception{

        HttpDelete delete = new HttpDelete(url);
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        delete.setHeader(HttpHeaders.AUTHORIZATION,authHeader);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(delete)) {

            HttpEntity rEntity = response.getEntity();
            if (rEntity != null) {
                // return it as a String
                String result = EntityUtils.toString(rEntity);
                return result;
            }
        }

        return null;
    }


    public String GETAuthenticated(String url, String username, String password) throws Exception{
        HttpGet request = new HttpGet(url);
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION,authHeader);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                return result;
            }
        }
        return null;
    }


    public String POSTjsonAuthenticated(String url, String body, String username, String password) throws Exception{
        HttpPost post = new HttpPost(url);

        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        post.setHeader(HttpHeaders.AUTHORIZATION,authHeader);
        StringEntity entity = new StringEntity(body);
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity rEntity = response.getEntity();
            if (rEntity != null) {
                // return it as a String
                String result = EntityUtils.toString(rEntity);
                return result;
            }
        }

        return null;
    }


    public String POSTxml(String url, String body) throws Exception{
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(body);
        post.setEntity(entity);
        post.setHeader("Accept", "text/xml");
        post.setHeader("Content-type", "text/xml");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity rEntity = response.getEntity();
            if (rEntity != null) {
                // return it as a String
                String result = EntityUtils.toString(rEntity);
                return result;
            }
        }

        return null;
    }



    public void toFile(String input, String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(input);
        writer.flush();
        writer.close();
    }
}
