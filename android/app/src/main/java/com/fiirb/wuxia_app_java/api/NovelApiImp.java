package com.fiirb.wuxia_app_java.api;

import com.fiirb.wuxia_app_java.models.WuxiaModels;
import com.fiirb.wuxia_app_java.models.WuxiaModels.Chapter;
import com.fiirb.wuxia_app_java.models.WuxiaModels.ChapterElm;
import com.fiirb.wuxia_app_java.models.WuxiaModels.ChapterInfo;
import com.fiirb.wuxia_app_java.models.WuxiaModels.NovelInfo;
import com.fiirb.wuxia_app_java.novel.NovelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NovelApiImp implements WuxiaModels.NovelApi {

    NovelService novelService;

    public NovelApiImp(NovelService service) {
        novelService = service;
    }

    @Override
    public List<NovelInfo> listNovels() {
        try {
            return novelService.getNovelsHome();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<NovelInfo> findNovels(String search) {
        try {
            return novelService.findNovels(search);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public ChapterInfo listNovelChapters(String novelUrlPath) {

        try {
            return novelService.getChapters(novelUrlPath);
        } catch (Exception e) {
            e.printStackTrace();
            ChapterInfo chapterInfo = new ChapterInfo();

            chapterInfo.setName("not found");
            chapterInfo.setChapters(new ArrayList<>());
            return new ChapterInfo();
        }
    }

    @Override
    public Chapter readNovelChapter(String novelName, String chapterName) {
        try {
            return novelService.getChapter(chapterName);
        } catch (Exception e) {
           Chapter blankChapter = new Chapter();
           blankChapter.setChapter(new ArrayList<>());
           blankChapter.setTitle("not found");
            return blankChapter;
        }
    }

    @Override
    public void downloadChapters(ChapterInfo chapters) {

        Book book = new Book();
        Metadata metadata = book.getMetadata();

        // Set the title
        metadata.addTitle("Epublib test book 1");

        // Add an Author
        metadata.addAuthor(new Author("Joe", "Tester"));

        // Set cover image
        book.setCoverImage(
                getResource("/book1/test_cover.png", "cover.png") );

        List<Chapter> allChapters = chapters.getChapters()
                .stream()
                .map(chapterElm -> readNovelChapter("", chapterElm.getUrlPath()))
                .collect(Collectors.toList());

        // Create EpubWriter
        EpubWriter epubWriter = new EpubWriter();

        // Write the Book as Epub
        epubWriter.write(book, new FileOutputStream("test1_book1.epub"));

    }
}