// @dart = 2.11
import 'package:flutter/material.dart';
import 'package:wuxia_app_java/pigeon.dart';

import '../main.dart';
import 'package:flutter_search_bar/flutter_search_bar.dart';
import 'details_page.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<NovelInfo> _novelInfo = List.empty();
  SearchBar searchBar;

  _MyHomePageState() {
    searchBar = SearchBar(
        inBar: false,
        setState: setState,
        onSubmitted: findNovels,
        buildDefaultAppBar: appBar);
    _listHomePage();
  }

  List<NovelInfo> _filterNullNovels(List<NovelInfo> novels) {
    List<NovelInfo> book = List.empty(growable: true);

    for (var e in novels) {
      if (e != null &&
          e.name != null &&
          e.imgUrl != null &&
          e.imgUrl.isNotEmpty &&
          e.name.isNotEmpty) {
        book.add(e);
      }
    }

    return book;
  }

  void findNovels(String name) async {
    List<NovelInfo> novels = await MyApis.novelAPI.findNovels(name);

    setState(() {
      _novelInfo = _filterNullNovels(novels);
    });
  }

  AppBar appBar(BuildContext context) {
    return AppBar(
      title: Text(widget.title),
      actions: [searchBar.getSearchAction(context)],
    );
  }

  Future<void> _listHomePage() async {
    List<NovelInfo> novels = await MyApis.novelAPI.listNovels();

    setState(() {
      _novelInfo = _filterNullNovels(novels);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: searchBar.build(context),
      body: Center(
          child: ListView(
              padding: const EdgeInsets.all(15),
              children: _novelInfo
                  .map(
                    (e) => ListTile(
                      leading: Image.network(e.imgUrl),
                      title: Text(
                        e.name,
                      ),
                      onTap: () => Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => NovelDetailsPage(novel: e)),
                      ),
                    ),
                  )
                  .toList())), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
