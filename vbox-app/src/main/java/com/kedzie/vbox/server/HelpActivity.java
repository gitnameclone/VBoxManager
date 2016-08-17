package com.kedzie.vbox.server;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.kedzie.vbox.R;
import com.kedzie.vbox.app.BaseActivity;
import com.kedzie.vbox.app.Utils;
import roboguice.inject.InjectView;

/**
 * Detailed help information for launching <em>vboxwebsrv</em>
 */
public class HelpActivity extends BaseActivity {

    @InjectView(R.id.main_text)
    private TextView mainText;
    @InjectView(R.id.ssl_text)
    private TextView sslText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mainText.setText(Html.fromHtml(getResources().getString(R.string.help_main)));
        mainText.setMovementMethod(LinkMovementMethod.getInstance());
        
        sslText.setText(Html.fromHtml(getResources().getString(R.string.help_ssl)));
        sslText.setMovementMethod(LinkMovementMethod.getInstance());
    }
    
    @Override
    public void finish() {
        super.finish();
        Utils.overrideBackTransition(this);
    }
}
