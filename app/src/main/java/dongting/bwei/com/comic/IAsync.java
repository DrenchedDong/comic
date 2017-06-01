package dongting.bwei.com.comic;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import dongting.bwei.com.comic.listener.ResponseListener;

/**
 * Created by lenovo on 2017/4/25.
 */


public class IAsync extends AsyncTask<String, Void, Object> {
    ResponseListener responseListener;

    public IAsync(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    String result = "";

    private InputStream inputStream;

    @Override
    protected Object doInBackground(String... strings) {


        String path = strings[0];
        if (TextUtils.isEmpty(path)) {
            return result;
        }


        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                result = StringUtils.inputStreamToString(inputStream);


            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (result != null) {
            if (TextUtils.isEmpty(result)) {
                responseListener.onFail();
            } else {
                responseListener.onSuccess(result);

            }
        } else {
            responseListener.onFail();
        }
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
      if(responseListener!=null){
            responseListener.onFail();

      }



    }
}

