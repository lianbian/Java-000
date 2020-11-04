# Week02 作业题目（周四）：
根据案例做了压测，
gc有待持续加强，准备入手《深入理解java虚拟机》，gc的这个作业，回头补上。

# Week02 作业题目（周六）：
``` 
public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8088/api/hello").build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
