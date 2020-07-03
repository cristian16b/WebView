package com.example.webviewapp.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.webviewapp.R;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    WebView miVisorWeb;

    String url = "http://localhost";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Bloque 1
        miVisorWeb = (WebView) root.findViewById(R.id.webViewLocal);
        final WebSettings ajustesVisorWeb = miVisorWeb.getSettings();
        miVisorWeb.setWebViewClient(new WebViewClient());
        ajustesVisorWeb.setJavaScriptEnabled(true);
//        miVisorWeb.loadUrl(getUrl());
        miVisorWeb.loadUrl(url.toString());
        // Bloque 2
        miVisorWeb.setWebViewClient( new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                saveUrl(url);
                view.loadUrl(url);
                return false;
            }
        });

        miVisorWeb.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });


        return root;
    }


    public void saveUrl(String url){
        SharedPreferences sp = getActivity().getSharedPreferences("SP_WEBVIEW_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SAVED_URL_LOCALHOST", url);
        editor.commit();
    }

    public String getUrl(){

        SharedPreferences sp = getActivity().getSharedPreferences("SP_WEBVIEW_PREFS", MODE_PRIVATE);
        return sp.getString("SAVED_URL_LOCALHOST", url);
    }
}