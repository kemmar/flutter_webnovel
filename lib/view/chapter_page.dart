import 'package:flutter/material.dart';
import 'package:wuxia_app_java/main.dart';
import 'package:wuxia_app_java/pigeon.dart';
import 'package:flutter_html/flutter_html.dart';

class NovelChapterPage extends StatefulWidget {
  const NovelChapterPage({Key key, this.chapter}) : super(key: key);

  final ChapterElm chapter;

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

  @override
  initState() {
    super.initState();
    _listChapters();
  }

  @override
  Widget build(BuildContext context) {
    List<Html> lst = Html(data: _chapter.chapter)
        .data
        .split("\n")
        .map((e) => e.replaceAll("<br>", ""))
        .takeWhile((value) => !value.contains("Next Chapter"))
        .map((e) => Html(data: e))
        .toList();

    return Scaffold(
        appBar: AppBar(
          title: Text(widget.chapter.name),
        ),
        body: Center(
          child: ListView(children: _chapter == null ? [] : lst),
        ));
  }
}
