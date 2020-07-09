package com.example.webviewapp.ui.gallery;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
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
import static android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK;

public class GalleryFragment extends Fragment {

    WebView miVisorWeb;

    String url = "https://www.google.com/";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inicializar variables, como la url que quieres cargar.
        // Algunas otras cosas que quieres que solo se llame una vez
        url = "https://www.google.com/";
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Aqui inicializar tu vistas y componentes
        View root = inflater.inflate(R.layout.fragment_gallery, container,false);

        miVisorWeb = (WebView) root.findViewById(R.id.webViewGoogle);

        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle     savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Aqui va todo el código restante
        miVisorWeb.requestFocus();
        WebSettings webSettings = miVisorWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);

        miVisorWeb.loadUrl(url.toString());
        CookieManager.getInstance().setAcceptCookie(true);
        // Bloque 2
        miVisorWeb.setWebViewClient( new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        miVisorWeb.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    WebView webView = (WebView) v;
                    switch(keyCode){
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack()){
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });

    }
}