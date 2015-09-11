package com.retor.vklib.authorizator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.retor.vklib.R;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VKOpenAuthView(this));
        initWebView();
    }

    private void initWebView() {
        WebView webView = (WebView) findViewById(android.R.id.copyUrl);
        webView.setWebViewClient(new SimpleClient());
        webView.loadUrl(Constants.AUTH_URL);
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


    private class SimpleClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (url.startsWith(Constants.URL_REDIRECT)){
                clearCache(view);
                returnResult(url.substring(url.indexOf('#') + 1));
            }
        }

        private void clearCache(WebView view) {
            view.stopLoading();
            view.clearCache(true);
            view.clearHistory();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
        }

        private void returnResult(String url) {
            Intent intent = new Intent();
            intent.putExtra(Constants.KEY_INTENT, url);
            setResult(Constants.REQUEST_CODE_OK, intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    private static class VKOpenAuthView extends RelativeLayout {
        public VKOpenAuthView(Context context) {
            super(context);
            ProgressBar progress = new ProgressBar(context);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT, 1);
            progress.setLayoutParams(lp);
            addView(progress);

            WebView webView = new WebView(context);
            lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            webView.setLayoutParams(lp);
            addView(webView);
            webView.setId(android.R.id.copyUrl);
        }
    }
}
