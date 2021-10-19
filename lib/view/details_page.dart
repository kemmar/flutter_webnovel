import 'package:flutter/material.dart';
import 'package:wuxia_app_java/main.dart';
import 'package:wuxia_app_java/pigeon.dart';
import 'package:wuxia_app_java/view/chapter_page.dart';

class NovelDetailsPage extends StatefulWidget {
  const NovelDetailsPage({Key key, this.novel}) : super(key: key);

  final NovelInfo novel;

  @override
  State<NovelDetailsPage> createState() => _NovelDetailsPageState();
}

class _NovelDetailsPageState extends State<NovelDetailsPage> {
  List<ChapterElm> _chapters = List.empty(growable: true);
  bool _reverse = true;

  Future<void> _listChapters() async {
    List<ChapterElm> chapters = List.empty(growable: true);

    for (var element
        in (await MyApis.novelAPI.listNovelChapters(widget.novel.url))
            .chapters) {
      if (element != null) {
        chapters.add(element);
      }
    }

    setState(() {
      _chapters = chapters;
    });
  }

  void flipList() {

    setState(() {
      _reverse = !(_reverse);
      _chapters = _chapters.reversed.toList();
    });

  }

  @override
  initState() {
    super.initState();
    _listChapters();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.novel.name),
          actions: [
            IconButton(
              icon: Icon(_reverse ? Icons.arrow_drop_up : Icons.arrow_drop_down),
              onPressed: flipList,
            ),
          ],
        ),
        body: Center(
          child: Container(child:
            ListView(
              children:
                  _chapters
                      .map((e) => ListTile(title: Text(e.name), onTap: () => Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => NovelChapterPage(chapter: e)),
                  ),))
                      .toList(),
            )
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () => null,
          tooltip: 'Increment',
          child: const Icon(Icons.save_alt),
        ));
  }
}
