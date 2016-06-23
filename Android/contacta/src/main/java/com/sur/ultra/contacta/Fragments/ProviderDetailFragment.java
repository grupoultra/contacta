package com.sur.ultra.contacta.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sur.ultra.contacta.Adapters.MessageAdapter;
import com.sur.ultra.contacta.ChatActivity;
import com.sur.ultra.contacta.Interfaces.OnMessageSelectedListener;
import com.sur.ultra.contacta.Models.Message;
import com.sur.ultra.contacta.Models.Provider;
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
 * A simple {@link Fragment} subclass.
 * Use the {@link ProviderDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProviderDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    OnMessageSelectedListener mCallback;

    private static final String ID = "providersID";
    private ProgressDialog dialog;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView authorView;
    private RecyclerView lMessages;
    private RecyclerView lNews;
    private List<Message> messages = new ArrayList<Message>();
    private final static String TAG = "ProviderDetailFragment";

    public ProviderDetailFragment() {
        // Required empty public constructor
    }
//
//    // Container Activity must implement this interface
//    public interface OnMessageSelectedListener {
//        void onMessageSelected(int position, String type);
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.
     * @return A new instance of fragment ProviderDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProviderDetailFragment newInstance(int id) {
        ProviderDetailFragment fragment = new ProviderDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Contacta");

        View view = inflater.inflate(R.layout.fragment_provider_detail, container, false);
        lNews = (RecyclerView) view.findViewById(R.id.reciclerNews);
        lMessages = (RecyclerView) view.findViewById(R.id.reciclerMessages);

        dialog = new ProgressDialog(getContext());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Cargando proveedores");

        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        authorView = (TextView) view.findViewById(R.id.provider_info);

        new JSONTask().execute(API_URIS.oneProvider(getArguments().getInt(ID)));

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), ChatActivity.class));
                }
            });
        }

        return view;
    }

    public void populateAdapter(List<Message> messages, RecyclerView list){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(layoutManager);

        MessageAdapter adaptador;

        adaptador = new MessageAdapter(messages, getActivity(), mCallback);

        list.setAdapter(adaptador);
        list.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
    }

    public class JSONTask extends AsyncTask<String,String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
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

                return parentObject;
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
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            dialog.dismiss();

            /**/
            try {
                JSONArray providerInfo = result.getJSONArray("provider");
                JSONArray providerNews = result.getJSONArray("news");
                JSONArray providerMessages = result.getJSONArray("messages");

                Gson gson = new Gson();
                JSONObject finalObject = providerInfo.getJSONObject(0);
                Provider movieModel = gson.fromJson(finalObject.toString(), Provider.class);

                List<Message> newsList = new ArrayList<>();
                List<Message> messagesList = new ArrayList<>();

                for(int i = 0; i < providerNews.length(); i++){
                    Message message = gson.fromJson(providerNews.getJSONObject(i).toString(), Message.class);

                    newsList.add(message);
                }
                for(int i = 0; i < providerMessages.length(); i++){
                    Message message = gson.fromJson(providerMessages.getJSONObject(i).toString(), Message.class);

                    messagesList.add(message);
                }
                populateAdapter(newsList, lNews);
                populateAdapter(messagesList, lMessages);

                collapsingToolbarLayout.setTitle(movieModel.name);
                authorView.setText(movieModel.info);
            } catch (JSONException e) {
                e.printStackTrace();
//            } catch (NullPointerException e){
//                Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
