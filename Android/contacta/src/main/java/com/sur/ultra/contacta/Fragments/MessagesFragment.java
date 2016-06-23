package com.sur.ultra.contacta.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sur.ultra.contacta.Adapters.MessageAdapter;
import com.sur.ultra.contacta.Models.Message;
import com.sur.ultra.contacta.R;
import com.sur.ultra.contacta.Util.API_URIS;
import com.sur.ultra.contacta.Util.DecoracionLineaDivisoria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 6/2/16.
 */
public class MessagesFragment extends android.support.v4.app.Fragment {

    private static final String MESSAGE_TYPE = "";
    OnMessageSelectedListener mCallback;
    private ProgressDialog dialog;
    private RecyclerView lMessages;
    private List<Message> messages = new ArrayList<Message>();

    private static final String TAG = "MessagesFragment";

    // Container Activity must implement this interface
    public interface OnMessageSelectedListener {
        void onMessageSelected(int position, String type);
    }

    public MessagesFragment() {

    }

    public static MessagesFragment newInstance(int type) {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        args.putInt(MESSAGE_TYPE, type);

        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnMessageSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnMessageSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_recycler_view, container, false);

        RecyclerView reciclador = (RecyclerView)view.findViewById(R.id.recicler);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);

        dialog = new ProgressDialog(getContext());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Cargando noticias");

        lMessages = (RecyclerView) view.findViewById(R.id.recicler);

        int messageType = getArguments().getInt(MESSAGE_TYPE);

//        TODO: implementar un metodo para refrescar los mensajes y rehabilitar este condicional
//        if(messages.size() == 0){
            if(messageType == 0){
                new GetAllMessages().execute(API_URIS.allNews());
            } else{
                new GetAllMessages().execute(API_URIS.allMessages());
            }
//        } else {
//            populateAdapter(messages);
//        }

        return view;
    }

    public void populateAdapter(List<Message> messages){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lMessages.setLayoutManager(layoutManager);

        MessageAdapter adaptador;

        adaptador = new MessageAdapter(messages, getActivity(), mCallback);

        lMessages.setAdapter(adaptador);
        lMessages.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
    }

    public class GetAllMessages extends AsyncTask<String,String, List<Message>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<Message> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray;
                int messageType = getArguments().getInt(MESSAGE_TYPE);

                if(messageType == 0) {
                    parentArray = parentObject.getJSONArray("news");
                } else{
                    parentArray = parentObject.getJSONArray("messages");
                }

                List<Message> movieModelList = new ArrayList<>();

                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    /**
                     * below single line of code from Gson saves you from writing the json parsing yourself which is commented below
                     */
                    Message movieModel = gson.fromJson(finalObject.toString(), Message.class);

                    movieModelList.add(movieModel);
                }
                return movieModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;
        }

        @Override
        protected void onPostExecute(final List<Message> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if(result != null) {
                messages.addAll(result);
                populateAdapter(result);
            } else {
                Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}