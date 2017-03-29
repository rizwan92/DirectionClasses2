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

public class SubjectLoader extends AsyncTaskLoader {
    private static final String LOG_TAG = SubjectLoader.class.getName();
    private String mUrl;
    private String class_id=null;
    public SubjectLoader(Context context, String url, String class_id1 ) {
        super(context);
        mUrl = url;
        class_id=class_id1;

    }
    @Override
    public Object loadInBackground() {
        String data = null;
        try {
            data = URLEncoder.encode("class_id", "UTF-8") + "=" + URLEncoder.encode(class_id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ArrayList<Subject> boards1 = new ArrayList<Subject>();
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


    private ArrayList<Subject> featuresExtractFromJson(String JsonResponse) {


        ArrayList<Subject> results = new ArrayList<Subject>();


        try {
            JSONArray root = new JSONArray(JsonResponse);

            for (int i = 0; i < root.length(); i++) {

                JSONObject jsonObject = root.getJSONObject(i);
                String subject_id = jsonObject.getString("subject_id");
                int parse_subject_id = Integer.parseInt(subject_id);
                String subject_name = jsonObject.getString("subject_name");
                String class_id = jsonObject.getString("class_id");
                int parse_class_id = Integer.parseInt(class_id);
                results.add(new Subject(parse_subject_id, subject_name, parse_class_id));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Json execption");
        }

        return results;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
