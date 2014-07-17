package anil.myntratinder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Anil on 7/17/2014.
 *
 * helper class that helps in downloading from a given url, and raw post data to a local file
 * http://stackoverflow.com/questions/15802977/how-to-send-raw-data-from-android-http-post-request
 * TODO: check if the above method of sending post request with raw data works or not.
 */
public class Downloader {
    private static String myTag = "downloader";

    public static void downloadFromUrl(String url, String postData, FileOutputStream fos){
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new StringEntity(postData));
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            HttpResponse result = httpClient.execute(httpPost);
            HttpEntity resultEntity = result.getEntity();

            InputStream is = resultEntity.getContent();
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024];
            int count;
            while ((count = bis.read(data)) != -1){
                bos.write(data, 0, count);
            }

            bos.flush();
            bos.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}