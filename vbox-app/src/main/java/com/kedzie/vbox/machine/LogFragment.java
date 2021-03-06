package com.kedzie.vbox.machine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kedzie.vbox.R;
import com.kedzie.vbox.api.IMachine;
import com.kedzie.vbox.app.BundleBuilder;
import com.kedzie.vbox.task.BaseTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 
 * @author Marek Kędzierski
 * @apiviz.stereotype fragment
 */
public class LogFragment extends Fragment {
    private static final String TAG = "LogFragment";
	private static final int MAX_LOG_SIZE=409600; //400 Kbps
	
	class LoadLogTask extends BaseTask<IMachine, String> {
		public LoadLogTask() {
			super((AppCompatActivity)getActivity(), null);
		}

		@Override 
		protected String work(IMachine... m) throws Exception {
		    try {
		        return new String(m[0].readLog(0, 0, MAX_LOG_SIZE));
		    } catch(Exception e) {
		        return "";
		    }
		}

		@Override
		protected void onSuccess(String result) {
			if(result.length()==MAX_LOG_SIZE)	
			    Log.w(TAG,"Didn't get entire log file.  Log size: " + result.length());
			_logText.setText(_log=result);
		}
	}

	@BindView(R.id.logText)
	 TextView _logText;
	private IMachine _machine;
	private String _log;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_machine = BundleBuilder.getProxy(getArguments(), IMachine.BUNDLE, IMachine.class);
		if(savedInstanceState!=null)
			_log = savedInstanceState.getString("log");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.machine_log, null);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
	}

	@Override
	public void onStart() {
		super.onStart();
		if(_log!=null) 
			_logText.setText(_log);
		else
			new LoadLogTask().execute(_machine);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("log", _log);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.option_menu_refresh:
		    Log.i(TAG, "Refreshing...");
			new LoadLogTask().execute(_machine);
			return true;
		}
		return false;
	}
}
