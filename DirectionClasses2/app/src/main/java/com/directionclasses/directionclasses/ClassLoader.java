package com.directionclasses.directionclasses;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by rizwan on 27-Mar-17.
 */

public class ClassLoader extends AsyncTaskLoader {
    private static final String LOG_TAG = ClassLoader.class.getName();
    private String mUrl;
    private String borad_id=null;
    public ClassLoader(Context context, String url,String borad_id1 ) {
        super(context);
        mUrl = url;
        borad_id=borad_id1;

    }
    @Override
    public Object loadInBackground() {
        String data = null;
        try {
            data = URLEncoder.encode("board_id", "UTF-8") + "=" + URLEncoder.encode(borad_id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ArrayList<Class> boards1 = new ArrayList<Class>();
        URL url1 = null;
        try {
            url1 = new URL(mUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "this is url malformed exception");
        }

        BufferedReader reader = null;
        String text = null;
        try {
            URLConnection conn = url1.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            text = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boards1 = featuresExtractFromJson(text);

        return boards1;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    private ArrayList<Class> featuresExtractFromJson(String JsonResponse) {


        ArrayList<Class> results = new ArrayList<Class>();


        try {
            JSONArray root = new JSONArray(JsonResponse);

            for (int i = 0; i < root.length(); i++) {

                JSONObject jsonObject = root.getJSONObject(i);
                String calss_id = jsonObject.getString("calss_id");
                String class_name = jsonObject.getString("class_name");
                String board_id = jsonObject.getString("board_id");
                int parse_board_id = Integer.parseInt(board_id);
                int parse_class_id = Integer.parseInt(calss_id);
                results.add(new Class(parse_class_id, class_name, parse_board_id));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Json execption");
        }

        return results;
    }
}
