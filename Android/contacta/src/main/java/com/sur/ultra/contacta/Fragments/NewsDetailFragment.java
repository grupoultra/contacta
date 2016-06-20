package com.sur.ultra.contacta.Fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.sur.ultra.contacta.Models.Message;
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
 * Activities that contain this fragment must implement the
 * {@link NewsDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsDetailFragment extends Fragment {
    // The container Activity must implement this interface so the frag can deliver inbox

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ID = "param1";
    private static final String TAG = "NewsDetailFragment";
    private ProgressDialog dialog;
    public TextView authorView ;
    public TextView titleView ;
    public TextView bodyView ;
    public ProgressBar progressBar;
    public ImageView avatar;

    // TODO: Rename and change types of parameters
    private int id;

    private OnFragmentInteractionListener mListener;

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.
     * @return A new instance of fragment NewsDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsDetailFragment newInstance(int id) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        dialog = new ProgressDialog(getContext());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Cargando proveedor");

        new JSONTask().execute(API_URIS.oneNews(getArguments().getInt(ID)));

        authorView = (TextView) view.findViewById(R.id.authorView);
        titleView = (TextView) view.findViewById(R.id.newsTitle);
        bodyView = (TextView) view.findViewById(R.id.newsBody);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        avatar = (ImageView) view.findViewById(R.id.avatar);


        ImageButton buttonLike = (ImageButton) view.findViewById(R.id.buttonLike);
        buttonLike.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Esta noticia habla bien del tema", Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton buttonDislike = (ImageButton) view.findViewById(R.id.buttonDislike);
        buttonDislike.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Esta noticia habla mal del tema", Toast.LENGTH_SHORT).show();
            }
        });
        Button buttonInformation = (Button) view.findViewById(R.id.buttonInformation);
        buttonInformation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Link externo", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class JSONTask extends AsyncTask<String,String, Message> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Message doInBackground(String... params) {
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
                JSONArray parentArray = parentObject.getJSONArray("news");

                List<Message> movieModelList = new ArrayList<>();

                Gson gson = new Gson();
                JSONObject finalObject = parentArray.getJSONObject(0);
                Message movieModel = gson.fromJson(finalObject.toString(), Message.class);

                movieModelList.add(movieModel);
                return movieModel;

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
        protected void onPostExecute(final Message result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if(result != null) {
                authorView.setText(result.name);
                titleView.setText(result.title);
                bodyView.setText(result.body);

                ImageLoadingListener imageLoadingListener = new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        progressBar.setVisibility(View.GONE);
                    }
                };


                // Then later, when you want to display image
                ImageLoader.getInstance().displayImage(result.getAvatar(), avatar, imageLoadingListener);

            } else {
                Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
