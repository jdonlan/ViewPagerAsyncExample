package com.mobiquity.demo.viewpagerasyncdemo.objects;

import android.os.AsyncTask;
import android.util.Log;

import com.mobiquity.demo.viewpagerasyncdemo.fragments.CategoryFragment;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jdonlan on 7/23/15.
 */
public class NumberTask extends AsyncTask<String,Void,String> {

    public static final String TAG = "NumberTask";

    private CategoryFragment mCategoryFragment;

    public NumberTask(CategoryFragment _categoryFragment){
        mCategoryFragment = _categoryFragment;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            String baseURL = "http://numbersapi.com/" + params[0] + "/" + params[1];
            URL url = new URL(baseURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            String results = IOUtils.toString(inputStream);
            inputStream.close();
            connection.disconnect();
            return results;
        } catch (MalformedURLException e) {
            Log.e(TAG,"Malformed URL.");
        } catch (IOException e) {
            Log.e(TAG,"Error connecting to internet.");
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(mCategoryFragment instanceof NumberTaskListener){
            ((NumberTaskListener) mCategoryFragment).updateInformation(s);
        }
    }
}
