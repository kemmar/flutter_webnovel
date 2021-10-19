import 'package:pigeon/pigeon.dart';

@HostApi()
abstract class NovelApi {
  List<NovelInfo> listNovels();

  List<NovelInfo> findNovels(String search);

  ChapterInfo listNovelChapters(String novelName);

  Chapter readNovelChapter(String novelName, String chapterName);
}

class NovelInfo {
  String name;
  String imgUrl;
  String url;
}

class ChapterElm {
  String name;
  String urlPath;
}

class ChapterInfo {
  String name;
  String imgUrl;
  List<ChapterElm> chapters;
}

class Chapter {
  String title;
  String chapter;
  String nextPage;
  String prevPage;
}