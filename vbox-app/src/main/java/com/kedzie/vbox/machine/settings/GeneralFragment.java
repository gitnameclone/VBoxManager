package com.kedzie.vbox.machine.settings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.kedzie.vbox.R;
import com.kedzie.vbox.app.FragmentElement;
import com.kedzie.vbox.app.PagerTabHost;
import roboguice.fragment.RoboFragment;

public class GeneralFragment extends RoboFragment {
    private PagerTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabHost = new PagerTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(new FragmentElement("Basic", GeneralBasicFragment.class, getArguments()));
        mTabHost.addTab(new FragmentElement("Description", GeneralDescriptionFragment.class, getArguments()));
        if(savedInstanceState!=null)
            mTabHost.setCurrentTab(savedInstanceState.getInt("tab", 0));
        return mTabHost;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", mTabHost.getCurrentTab());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}

