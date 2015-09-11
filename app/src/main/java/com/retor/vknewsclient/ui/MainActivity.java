package com.retor.vknewsclient.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.retor.vknewsclient.application.BaseApplication;
import com.retor.vknewsclient.R;
import com.retor.vknewsclient.di.modules.DaggerPresentersComponent;
import com.retor.vknewsclient.di.modules.InteractorsModule;
import com.retor.vknewsclient.di.modules.PresenterModules;
import com.retor.vknewsclient.presenter.LoginPresenter;
import com.retor.vknewsclient.view.ProgressView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ProgressView{
    @Inject
    protected LoginPresenter presenter;
    private ProgressDialog progressDialog;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inject();
        presenter.setView(this);
        presenter.onCreate(this, savedInstanceState);
        initProgress();
/*        floatingActionButton.setBackgroundTintList(new ColorStateList().valueOf(getResources().getColor(R.color.fab_n)));
        floatingActionButton.setRippleColor(581999);*/
    }

    private void inject() {
        DaggerPresentersComponent.builder()
                .applicationComponent(((BaseApplication) getApplication()).getComponent())
                .interactorsModule(new InteractorsModule())
                .presenterModules(new PresenterModules())
                .build().inject(this);
    }

    @OnClick(R.id.fab)
    protected void onClick(){
        presenter.logout();
    }

    private void initProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.onResume(this);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onResult(this, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter.onRestoreState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        ButterKnife.unbind(this);
        setContentView(new ProgressFrame(this));
/*        if (progressDialog!=null && !progressDialog.isShowing())
            progressDialog.show();*/
    }

    @Override
    public void closeProrgess() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
/*        if (progressDialog!=null && progressDialog.isShowing())
            progressDialog.dismiss();*/
    }

    @Override
    public void onError(final Throwable throwable) {
        new AlertDialog.Builder(this).setMessage(throwable.getLocalizedMessage()).create().show();
    }

    private static class ProgressFrame extends RelativeLayout {
        public ProgressFrame(Context context) {
            super(context);
            final ProgressBar progress = new ProgressBar(context);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT, 1);
            progress.setLayoutParams(lp);
            addView(progress);

            TextView textView = new TextView(context);
            lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            textView.setLayoutParams(lp);
            textView.setText("Authorisation...");
            addView(textView);
        }
    }
}
