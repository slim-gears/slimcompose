package com.slimgears.slimcompose.example;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.slimgears.slimbus.Subscribe;
import com.slimgears.slimcompose.activity.AbstractActivity;
import com.slimgears.slimcompose.injection.Components;
import com.slimgears.slimcompose.injection.DependencyInjector;
import com.slimgears.slimcompose.injection.InjectFrom;
import com.slimgears.slimprefs.BindPreference;

import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

@InjectFrom(MainActivityComponent.class)
public class MainActivity extends AbstractActivity<MainActivityComponent, MainActivity> {
    @BindPreference Date mFirstRunDate = new Date();
    @BindPreference(twoWay = true) int mRunCount = 0;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.hello_text) TextView mHelloText;

    @Override
    protected MainActivityComponent createComponent() {
        return DaggerMainActivityComponent.builder()
                .appComponent(Components.getAppComponent(getApplication(), AppComponent.class))
                .mainActivityModule(new MainActivityModule(this))
                .build();
    }

    @Override
    protected DependencyInjector<MainActivity> createInjector(MainActivityComponent component) {
        return DaggerMainActivity_Injector.builder()
                .mainActivityComponent(component)
                .build();
    }

    @Override
    protected void onSetContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        mRunCount++;
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
}
