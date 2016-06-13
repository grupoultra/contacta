package com.sur.ultra.contacta.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sur.ultra.contacta.ChatActivity;
import com.sur.ultra.contacta.Models.Message;
import com.sur.ultra.contacta.Models.Provider;
import com.sur.ultra.contacta.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProviderDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProviderDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private int id;

    public ProviderDetailFragment() {
        // Required empty public constructor
    }

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
        args.putInt(ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Contacta");

        View view = inflater.inflate(R.layout.fragment_provider_detail, container, false);

        Provider provider = Provider.PROVIDERS.get(id);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(provider.name);

        TextView authorView = (TextView) view.findViewById(R.id.provider_info);
        authorView.setText(provider.info);


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

}
