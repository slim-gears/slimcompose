package com.slimgears.slimcompose.example;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimbus.Subscribe;
import com.slimgears.slimcompose.injection.Components;
import com.slimgears.slimcompose.injection.HasComponent;
import com.slimgears.slimcompose.injection.InjectFrom;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@InjectFrom(MainActivityComponent.class)
public class MainActivity extends AppCompatActivity implements HasComponent<MainActivityComponent> {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.hello_text) TextView mHelloText;
    @Inject EventBus mEventBus;

    private MainActivityComponent mComponent;
    private EventBus.Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mComponent = DaggerMainActivityComponent.builder()
                .appComponent(Components.getAppComponent(getApplication(), AppComponent.class))
                .mainActivityModule(new MainActivityModule(this))
                .build();

        DaggerMainActivity_Injector.builder()
                .mainActivityComponent(mComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSubscription = mEventBus.subscribe(this);
    }

    @Override
    protected void onPause() {
        mSubscription.unsubscribe();
        super.onPause();
    }

    @OnClick(R.id.fab) void onFabClicked(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Subscribe void onSayHello(SayHelloEvent event) {
        mHelloText.setText(String.format("Hello, World from %s", event.from));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public MainActivityComponent getComponent() {
        return mComponent;
    }
}
