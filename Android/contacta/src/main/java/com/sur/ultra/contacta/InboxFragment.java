package com.sur.ultra.contacta;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment {
    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    public InboxFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);

            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);
            pestanas.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(pestanas);
    }

    private void poblarViewPager(ViewPager viewPager) {
        TabsAdapter adapter = new TabsAdapter(getFragmentManager());
        adapter.addFragment(new FragmentoNoticias(), getString(R.string.title_activity_news));
        adapter.addFragment(new FragmentoMensajes(), getString(R.string.title_activity_messages));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(pestanas);
    }

}
