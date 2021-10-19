package com.fiirb.wuxia_app_java;

import android.os.Bundle;

import com.fiirb.wuxia_app_java.api.NovelApiImp;
import com.fiirb.wuxia_app_java.models.WuxiaModels;
import com.fiirb.wuxia_app_java.novel.NovelService;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends FlutterActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NovelService novelService = new NovelService();

        WuxiaModels
                .NovelApi
                .setup(getFlutterEngine().getDartExecutor().getBinaryMessenger(), new NovelApiImp(novelService));
    }
}
