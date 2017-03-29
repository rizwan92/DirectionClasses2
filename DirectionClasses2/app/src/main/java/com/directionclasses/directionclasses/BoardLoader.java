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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rizwan on 27-Mar-17.
 */

public class BoardLoader extends AsyncTaskLoader {

    private static final String LOG_TAG = BoardLoader.class.getName();
    private String mUrl;
    public BoardLoader(Context context, String url) {
        super(context);
        mUrl = url;

    }
    @Override
    public List<Board> loadInBackground() {


        ArrayList<Board> boards1 = new ArrayList<Board>();
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

    private ArrayList<Board> featuresExtractFromJson(String JsonResponse) {


        ArrayList<Board> results = new ArrayList<Board>();


        try {
            JSONArray root = new JSONArray(JsonResponse);

            for (int i = 0; i < root.length(); i++) {

                JSONObject jsonObject = root.getJSONObject(i);
                String board_id = jsonObject.getString("board_id");
                String board_name = jsonObject.getString("board_name");
                int parse_board_id = Integer.parseInt(board_id);
                results.add(new Board(parse_board_id, board_name));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Json execption");
        }

        return results;
    }
}
