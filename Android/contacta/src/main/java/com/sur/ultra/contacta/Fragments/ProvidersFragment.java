package com.sur.ultra.contacta.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sur.ultra.contacta.Adapters.ProvidersAdapter;
import com.sur.ultra.contacta.Models.Provider;
import com.sur.ultra.contacta.R;
import com.sur.ultra.contacta.Util.API_URIS;

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
 */
public class ProvidersFragment extends Fragment {

    OnProviderSelectedListener mCallback;
    private ProgressDialog dialog;
    private RecyclerView lProviders;

    // Container Activity must implement this interface
    public interface OnProviderSelectedListener {
        void onProviderSelected(int position);
    }

    public ProvidersFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container context has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnProviderSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnProviderSelectedListener");
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

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Proveedores");

        dialog = new ProgressDialog(getContext());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Cargando proveedores");


        lProviders = (RecyclerView) view.findViewById(R.id.recicler);

        new GetAllProviders().execute(API_URIS.allProviders());
        return view;
    }

    public class GetAllProviders extends AsyncTask<String,String, List<Provider>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<Provider> doInBackground(String... params) {
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
                JSONArray parentArray = parentObject.getJSONArray("providers");

                List<Provider> movieModelList = new ArrayList<>();

                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    /**
                     * below single line of code from Gson saves you from writing the json parsing yourself which is commented below
                     */
                    Provider movieModel = gson.fromJson(finalObject.toString(), Provider.class);

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
        protected void onPostExecute(final List<Provider> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if(result != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                lProviders.setLayoutManager(layoutManager);

                ProvidersAdapter adaptador = new ProvidersAdapter(result, getActivity(), mCallback);
                lProviders.setAdapter(adaptador);
            } else {
                Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
