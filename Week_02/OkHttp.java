package Week_02;

import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class OkHttp {
    public static void main(String[] args) {

        // JSON.toJSON()
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8088/api/hello").build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
