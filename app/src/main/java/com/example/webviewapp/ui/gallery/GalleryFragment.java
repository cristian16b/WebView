package com.example.webviewapp.ui.gallery;

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

public class GalleryFragment extends Fragment {

    WebView miVisorWeb;

    String url = "https://www.google.com/";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container,false);

        // Bloque 1
        miVisorWeb = (WebView) root.findViewById(R.id.webViewGoogle);
        WebSettings webSettings = miVisorWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        miVisorWeb.loadUrl(getUrl());
        // Bloque 2
        miVisorWeb.setWebViewClient( new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                saveUrl(url);
                view.loadUrl(url);
                return false;
            }
        });


        return root;
    }


    public void saveUrl(String url){
        SharedPreferences sp = getActivity().getSharedPreferences("SP_WEBVIEW_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SAVED_URL", url);
        editor.commit();
    }

    public String getUrl(){

        SharedPreferences sp = getActivity().getSharedPreferences("SP_WEBVIEW_PREFS", MODE_PRIVATE);
        return sp.getString("SAVED_URL", "http://google.com");
    }
}