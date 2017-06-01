package dongting.bwei.com.comic;

import android.os.AsyncTask;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import dongting.bwei.com.comic.listener.ResponseListener;

/**
 * ${董婷}
 * 2017/4/25.
 */

public class MyAsyncTask extends AsyncTask<Object,Void,String> {

    ResponseListener responseListener;

    public  MyAsyncTask(ResponseListener responseListener){

        this.responseListener=responseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {
        String path=(String)params[0];

        try {
            URL url = new URL(path); //URL对象
            HttpURLConnection conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接
            conn.setDoInput(true); //允许输入流，即允许下载
            //conn.setDoOutput(true); //允许输出流，即允许上传

            conn.setRequestMethod("GET"); //使用get请求

            //System.out.println(conn.getResponseCode()+"");
            if(conn.getResponseCode()==200){
                InputStream inputStream =
                        conn.getInputStream();
                ByteArrayOutputStream bos=new ByteArrayOutputStream();

                int len=0;
                byte[] buffer=new byte[1024];
                while((len=inputStream.read(buffer))!=-1){
                    bos.write(buffer,0,len);
                }
                return bos.toString();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(responseListener!=null){
            responseListener.onSuccess(s);
        }else{
            responseListener.onFail();
        }
    }

    @Override
    protected void onCancelled(String s) {

        responseListener.onFail();
    }
}
