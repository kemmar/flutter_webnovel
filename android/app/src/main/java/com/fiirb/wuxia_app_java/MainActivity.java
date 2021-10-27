package com.fiirb.wuxia_app_java;

import android.os.Bundle;

import com.fiirb.wuxia_app_java.api.NovelApiImp;
import com.fiirb.wuxia_app_java.models.WuxiaModels;
import com.fiirb.wuxia_app_java.novel.NovelService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends FlutterActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NovelService novelService = new NovelService(this);

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            }, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        WuxiaModels
                .NovelApi
                .setup(getFlutterEngine().getDartExecutor().getBinaryMessenger(), new NovelApiImp(novelService));
    }
}
