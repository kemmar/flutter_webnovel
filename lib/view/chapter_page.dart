// @dart = 2.11
import 'package:flutter/material.dart';
import 'package:wuxia_app_java/main.dart';
import 'package:wuxia_app_java/pigeon.dart';

class NovelChapterPage extends StatefulWidget {
  const NovelChapterPage({Key key, this.chapter, this.chapterIndex, this.chapterList}) : super(key: key);

  final ChapterElm chapter;
  final int chapterIndex;
  final List<ChapterElm> chapterList;

  @override
  State<NovelChapterPage> createState() => _NovelChapterPageState();
}

class _NovelChapterPageState extends State<NovelChapterPage> {
  Chapter _chapter;

  Future<void> _listChapters() async {
    Chapter chapter = await MyApis.novelAPI
        .readNovelChapter(widget.chapter.name, widget.chapter.urlPath);

    setState(() {
      _chapter = chapter;
    });
  }

  changePage(int index) {
    if(index >= 0 && index < widget.chapterList.length) {
      ChapterElm chapterElm = widget.chapterList[index];

      Navigator.pushReplacement(
        context,
        MaterialPageRoute(
            builder: (context) =>
                NovelChapterPage(chapter: chapterElm,
                    chapterIndex: widget.chapterList.indexOf(chapterElm),
                  chapterList: widget.chapterList,)),
      );
    }
  }

  @override
  initState() {
    super.initState();
    _listChapters();
  }

  @override
  Widget build(BuildContext context) {
    List<Text> lst = _chapter.chapter
        .takeWhile((value) => !value.contains("Next Chapter"))
        .map((e) => Text(e))
        .toList();

    return Scaffold(
        appBar: AppBar(
          title: Text(widget.chapter.name),
          actions: [
            IconButton(onPressed: () => changePage(widget.chapterIndex - 1), icon: const Icon(Icons.arrow_back)),
            IconButton(onPressed: () => changePage(widget.chapterIndex + 1), icon: const Icon(Icons.arrow_forward)),
          ],
        ),
        body: Center(
          child: ListView(children: _chapter == null ? [] : lst),
        ));
  }
}
