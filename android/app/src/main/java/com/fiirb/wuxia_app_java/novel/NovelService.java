package com.fiirb.wuxia_app_java.novel;

import com.fiirb.wuxia_app_java.models.WuxiaModels.Chapter;
import com.fiirb.wuxia_app_java.models.WuxiaModels.ChapterElm;
import com.fiirb.wuxia_app_java.models.WuxiaModels.ChapterInfo;
import com.fiirb.wuxia_app_java.models.WuxiaModels.NovelInfo;
import com.fiirb.wuxia_app_java.utils.AppConfig;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NovelService {

    private Elements homePageElements() throws IOException, InterruptedException {
        class RunnableApiCalls implements Runnable {

            private Elements homePageElements;

            @Override
            public void run() {
                try {
                    homePageElements = Jsoup
                            .connect(AppConfig.NOVEL_SERVICE_URL)
                            .get()
                            .select(".section-item a");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Elements getHomePageElements() throws IOException {
                if (homePageElements == null) {
                    throw new IOException("unable to resolve home page call");
                } else {
                    return homePageElements;
                }
            }
        }
        RunnableApiCalls runnable = new RunnableApiCalls();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        return runnable.getHomePageElements();
    }

    public ChapterInfo getChapters(String novelPath) throws IOException, InterruptedException {
        class RunnableApiCalls implements Runnable {
            private Document chapterElements;

            @Override
            public void run() {
                try {
                    chapterElements = Jsoup
                            .connect(String.format("%s/%s/", AppConfig.NOVEL_SERVICE_URL, novelPath))
                            .get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private List<ChapterElm> getChapterElements() {
                return chapterElements.select(".chapter-list a").stream().map(elm -> {
                    String link = elm.select(".chapter-item").attr("href");
                    String name = elm.select("a.chapter-item > div.chapter-info > p.chapter-name").text();

                    ChapterElm chapterElm = new ChapterElm();

                    chapterElm.setName(name);
                    chapterElm.setUrlPath(link);
                    return chapterElm;
                }).collect(Collectors.toList());
            }

            ChapterInfo getChapterInfo() {
                return chapterElements
                        .select(".book-wrapper")
                        .stream()
                        .map(e -> {
                            String imgUrl = e.select("div.book-img > img").attr("src");
                            String name = e.select(".book-name").text();

                            ChapterInfo chapterInfo = new ChapterInfo();
                            chapterInfo.setName(name);
                            chapterInfo.setImgUrl(imgUrl);
                            chapterInfo.setChapters(getChapterElements());

                            return chapterInfo;
                        }).filter(e -> !e.getName().trim().isEmpty() && !e.getImgUrl().trim().isEmpty())
                        .collect(Collectors.toList())
                        .iterator()
                        .next();
            }
        }

        RunnableApiCalls runnable = new RunnableApiCalls();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        return runnable.getChapterInfo();
    }

    public Chapter getChapter(String chapterPath) throws InterruptedException {
        class RunnableApiCalls implements Runnable {
            private Document chapterElement;

            @Override
            public void run() {
                try {
                    chapterElement = Jsoup
                            .connect(String.format("%s/%s", AppConfig.NOVEL_SERVICE_URL, chapterPath))
                            .get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private Chapter getChapter() {

                Chapter chapter = new Chapter();

                String title = chapterElement.select(".chapter-title").text();
                String chapterStr = chapterElement.select(".chapter-entity").html();

                chapter.setTitle(title);
                chapter.setChapter(chapterStr);
                return chapter;
            }
        }

        RunnableApiCalls runnable = new RunnableApiCalls();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        return runnable.getChapter();
    }


    public List<NovelInfo> getNovelsHome() throws IOException, InterruptedException {

        List<NovelInfo> result = homePageElements().stream().map(e -> {

            NovelInfo novel = new NovelInfo();

            novel.setName(e.select(".img").attr("alt"));

            novel.setUrl(e.select(".item-img").attr("href").replace("/", ""));

            novel.setImgUrl(e.select(".img").attr("src"));

            return novel;
        }).collect(Collectors.toList());

        return result;
    }

    public List<NovelInfo> findNovels(String search) throws InterruptedException {
        class RunnableApiCalls implements Runnable {
            private List<NovelInfo> pages =
                    new ArrayList<>();

            private List<NovelInfo> docToNovelInfoList(Document doc) {
                List<NovelInfo> result = doc.select(".list-item a").stream().map(e -> {
                    String link = e.attr("class", "item-img").attr("href");
                    String name = e.select("item-img").attr("alt");
                    String image = e.select("item-img").attr("src");

                    NovelInfo novelInfo = new NovelInfo();

                    novelInfo.setName(name);
                    novelInfo.setUrl(link);
                    novelInfo.setImgUrl(image);

                    return novelInfo;
                }).collect(Collectors.toList());

                return result;
            }

            private List<NovelInfo> pagination(String page) {
                try {
                    Document doc = Jsoup
                            .connect(String.format("%s/search/%s/%s", AppConfig.NOVEL_SERVICE_URL, search, page))
                            .get();

                    return docToNovelInfoList(doc);

                } catch (IOException e) {
                    e.printStackTrace();

                    return new ArrayList<>();
                }
            }

            @Override
            public void run() {
                try {
                    Document document = Jsoup
                            .connect(String.format("%s/search/%s/%s", AppConfig.NOVEL_SERVICE_URL, search, 1))
                            .get();

                    pages.addAll(docToNovelInfoList(document));

                    for (Element element : document
                            .select(".pages a")) {
                        String e = element.text();
                        if (e.equals("1")) {
                            List<NovelInfo> pagination = pagination(e);
                            pages.addAll(pagination);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public List<NovelInfo> getPages() {
                return pages
                        .stream()
                        .filter(novel -> !novel.getName().trim().isEmpty() && !novel.getUrl().trim().isEmpty())
                        .collect(Collectors.toList());
            }

        }

        RunnableApiCalls runnable = new RunnableApiCalls();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        return runnable.getPages();
    }
}