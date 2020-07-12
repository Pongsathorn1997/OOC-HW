import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class WebDownload {

    private static final int BUFFER_SIZE = 4096;

    // using 3 unique method
    // this one using copyURLToFile in order to download and write
    public void downloadurl1(String link, String savePlace) throws IOException {
        String saveFile = savePlace + " : " + "save by method1";
        URL url = new URL(link);
        // using built-in function from apache commons io
        FileUtils.copyURLToFile(url, new File(saveFile));
        System.out.println("Download is done, File is at " + saveFile);
    }

    public void downloadurl2(String link, String savePlace) throws IOException {
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        int respondCode = httpURLConnection.getResponseCode();
        // checking the HTTP response
        if (respondCode == HttpURLConnection.HTTP_OK){
            // opends input stream from the HTTP connection
            InputStream inputStream = httpURLConnection.getInputStream();
            String savePath = savePlace + " : " + "Save by method2";
            // opens an output stream to save into file
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            int byteRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((byteRead = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, byteRead);
            }
            fileOutputStream.close();
            inputStream.close();

            System.out.println("Download is done, File is at " + savePath);
        }else {
            System.out.println("NO file to download. Server replied HTTP code: " + respondCode);
        }
        httpURLConnection.disconnect();
    }

    // using http client
    public void downloadurl3(String link, String savePlace) throws IOException {
        // create client
        CloseableHttpClient client = HttpClientBuilder.create().build();
        // get request
        HttpGet request = new HttpGet(link);
        // get response from link
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            Header contentType = entity.getContentType();
            String[] ext = (contentType.toString()).split("/");
            String savefile = savePlace + " : " + "Save By method3." + ext[1];
            FileOutputStream fileOutputStream = new FileOutputStream(savefile);
            System.out.println("Download is done, File is at " + savefile);
            entity.writeTo(fileOutputStream);
            fileOutputStream.close();
        }else {
            System.out.println("something is wrong!");
        }

    }

}
