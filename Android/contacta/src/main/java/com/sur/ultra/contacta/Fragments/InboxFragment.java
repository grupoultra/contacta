package com.sur.ultra.contacta.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sur.ultra.contacta.R;
import com.sur.ultra.contacta.Adapters.TabsAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment {
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;

    private static final String TAG = "InboxFragment";

    public InboxFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Contacta");

        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        insertTabs(container);
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        populateViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.inbox, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void insertTabs(ViewGroup container) {
        View parent = (View) container.getParent();
        appBar = (AppBarLayout) parent.findViewById(R.id.appbar);
        tabs = new TabLayout(getActivity());
        tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(tabs);
    }

    private void populateViewPager(ViewPager viewPager) {
        TabsAdapter adapter = new TabsAdapter(getFragmentManager());
        adapter.addFragment(MessagesFragment.newInstance(0), getString(R.string.title_activity_news));
        adapter.addFragment(MessagesFragment.newInstance(1), getString(R.string.title_activity_messages));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(tabs != null){
            appBar.removeView(tabs);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
