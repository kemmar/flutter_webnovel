// Autogenerated from Pigeon (v1.0.7), do not edit directly.
// See also: https://pub.dev/packages/pigeon
// ignore_for_file: public_member_api_docs, non_constant_identifier_names, avoid_as, unused_import, unnecessary_parenthesis, prefer_null_aware_operators, omit_local_variable_types, unused_shown_name
// @dart = 2.12
import 'dart:async';
import 'dart:typed_data' show Uint8List, Int32List, Int64List, Float64List;

import 'package:flutter/foundation.dart' show WriteBuffer, ReadBuffer;
import 'package:flutter/services.dart';

class NovelInfo {
  String? name;
  String? imgUrl;
  String? url;

  Object encode() {
    final Map<Object?, Object?> pigeonMap = <Object?, Object?>{};
    pigeonMap['name'] = name;
    pigeonMap['imgUrl'] = imgUrl;
    pigeonMap['url'] = url;
    return pigeonMap;
  }

  static NovelInfo decode(Object message) {
    final Map<Object?, Object?> pigeonMap = message as Map<Object?, Object?>;
    return NovelInfo()
      ..name = pigeonMap['name'] as String?
      ..imgUrl = pigeonMap['imgUrl'] as String?
      ..url = pigeonMap['url'] as String?;
  }
}

class ChapterElm {
  String? name;
  String? urlPath;
  List<ChapterElm?>? chapters;

  Object encode() {
    final Map<Object?, Object?> pigeonMap = <Object?, Object?>{};
    pigeonMap['name'] = name;
    pigeonMap['urlPath'] = urlPath;
    pigeonMap['chapters'] = chapters;
    return pigeonMap;
  }

  static ChapterElm decode(Object message) {
    final Map<Object?, Object?> pigeonMap = message as Map<Object?, Object?>;
    return ChapterElm()
      ..name = pigeonMap['name'] as String?
      ..urlPath = pigeonMap['urlPath'] as String?
      ..chapters = (pigeonMap['chapters'] as List<Object?>?)?.cast<ChapterElm?>();
  }
}

class ChapterInfo {
  String? name;
  String? imgUrl;
  List<ChapterElm?>? chapters;

  Object encode() {
    final Map<Object?, Object?> pigeonMap = <Object?, Object?>{};
    pigeonMap['name'] = name;
    pigeonMap['imgUrl'] = imgUrl;
    pigeonMap['chapters'] = chapters;
    return pigeonMap;
  }

  static ChapterInfo decode(Object message) {
    final Map<Object?, Object?> pigeonMap = message as Map<Object?, Object?>;
    return ChapterInfo()
      ..name = pigeonMap['name'] as String?
      ..imgUrl = pigeonMap['imgUrl'] as String?
      ..chapters = (pigeonMap['chapters'] as List<Object?>?)?.cast<ChapterElm?>();
  }
}

class Chapter {
  String? title;
  List<String?>? chapter;
  List<ChapterElm?>? chapters;

  Object encode() {
    final Map<Object?, Object?> pigeonMap = <Object?, Object?>{};
    pigeonMap['title'] = title;
    pigeonMap['chapter'] = chapter;
    pigeonMap['chapters'] = chapters;
    return pigeonMap;
  }

  static Chapter decode(Object message) {
    final Map<Object?, Object?> pigeonMap = message as Map<Object?, Object?>;
    return Chapter()
      ..title = pigeonMap['title'] as String?
      ..chapter = (pigeonMap['chapter'] as List<Object?>?)?.cast<String?>()
      ..chapters = (pigeonMap['chapters'] as List<Object?>?)?.cast<ChapterElm?>();
  }
}

class _NovelApiCodec extends StandardMessageCodec {
  const _NovelApiCodec();
  @override
  void writeValue(WriteBuffer buffer, Object? value) {
    if (value is Chapter) {
      buffer.putUint8(128);
      writeValue(buffer, value.encode());
    } else 
    if (value is ChapterElm) {
      buffer.putUint8(129);
      writeValue(buffer, value.encode());
    } else 
    if (value is ChapterElm) {
      buffer.putUint8(130);
      writeValue(buffer, value.encode());
    } else 
    if (value is ChapterInfo) {
      buffer.putUint8(131);
      writeValue(buffer, value.encode());
    } else 
    if (value is NovelInfo) {
      buffer.putUint8(132);
      writeValue(buffer, value.encode());
    } else 
    if (value is NovelInfo) {
      buffer.putUint8(133);
      writeValue(buffer, value.encode());
    } else 
{
      super.writeValue(buffer, value);
    }
  }
  @override
  Object? readValueOfType(int type, ReadBuffer buffer) {
    switch (type) {
      case 128:       
        return Chapter.decode(readValue(buffer)!);
      
      case 129:       
        return ChapterElm.decode(readValue(buffer)!);
      
      case 130:       
        return ChapterElm.decode(readValue(buffer)!);
      
      case 131:       
        return ChapterInfo.decode(readValue(buffer)!);
      
      case 132:       
        return NovelInfo.decode(readValue(buffer)!);
      
      case 133:       
        return NovelInfo.decode(readValue(buffer)!);
      
      default:      
        return super.readValueOfType(type, buffer);
      
    }
  }
}

class NovelApi {
  /// Constructor for [NovelApi].  The [binaryMessenger] named argument is
  /// available for dependency injection.  If it is left null, the default
  /// BinaryMessenger will be used which routes to the host platform.
  NovelApi({BinaryMessenger? binaryMessenger}) : _binaryMessenger = binaryMessenger;

  final BinaryMessenger? _binaryMessenger;

  static const MessageCodec<Object?> codec = _NovelApiCodec();

  Future<List<NovelInfo?>> listNovels() async {
    final BasicMessageChannel<Object?> channel = BasicMessageChannel<Object?>(
        'dev.flutter.pigeon.NovelApi.listNovels', codec, binaryMessenger: _binaryMessenger);
    final Map<Object?, Object?>? replyMap =
        await channel.send(null) as Map<Object?, Object?>?;
    if (replyMap == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
        details: null,
      );
    } else if (replyMap['error'] != null) {
      final Map<Object?, Object?> error = (replyMap['error'] as Map<Object?, Object?>?)!;
      throw PlatformException(
        code: (error['code'] as String?)!,
        message: error['message'] as String?,
        details: error['details'],
      );
    } else {
      return (replyMap['result'] as List<Object?>?)!.cast<NovelInfo?>();
    }
  }

  Future<List<NovelInfo?>> findNovels(String arg_search) async {
    final BasicMessageChannel<Object?> channel = BasicMessageChannel<Object?>(
        'dev.flutter.pigeon.NovelApi.findNovels', codec, binaryMessenger: _binaryMessenger);
    final Map<Object?, Object?>? replyMap =
        await channel.send(<Object>[arg_search]) as Map<Object?, Object?>?;
    if (replyMap == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
        details: null,
      );
    } else if (replyMap['error'] != null) {
      final Map<Object?, Object?> error = (replyMap['error'] as Map<Object?, Object?>?)!;
      throw PlatformException(
        code: (error['code'] as String?)!,
        message: error['message'] as String?,
        details: error['details'],
      );
    } else {
      return (replyMap['result'] as List<Object?>?)!.cast<NovelInfo?>();
    }
  }

  Future<ChapterInfo> listNovelChapters(String arg_novelName) async {
    final BasicMessageChannel<Object?> channel = BasicMessageChannel<Object?>(
        'dev.flutter.pigeon.NovelApi.listNovelChapters', codec, binaryMessenger: _binaryMessenger);
    final Map<Object?, Object?>? replyMap =
        await channel.send(<Object>[arg_novelName]) as Map<Object?, Object?>?;
    if (replyMap == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
        details: null,
      );
    } else if (replyMap['error'] != null) {
      final Map<Object?, Object?> error = (replyMap['error'] as Map<Object?, Object?>?)!;
      throw PlatformException(
        code: (error['code'] as String?)!,
        message: error['message'] as String?,
        details: error['details'],
      );
    } else {
      return (replyMap['result'] as ChapterInfo?)!;
    }
  }

  Future<Chapter> readNovelChapter(String arg_novelName, String arg_chapterName) async {
    final BasicMessageChannel<Object?> channel = BasicMessageChannel<Object?>(
        'dev.flutter.pigeon.NovelApi.readNovelChapter', codec, binaryMessenger: _binaryMessenger);
    final Map<Object?, Object?>? replyMap =
        await channel.send(<Object>[arg_novelName, arg_chapterName]) as Map<Object?, Object?>?;
    if (replyMap == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
        details: null,
      );
    } else if (replyMap['error'] != null) {
      final Map<Object?, Object?> error = (replyMap['error'] as Map<Object?, Object?>?)!;
      throw PlatformException(
        code: (error['code'] as String?)!,
        message: error['message'] as String?,
        details: error['details'],
      );
    } else {
      return (replyMap['result'] as Chapter?)!;
    }
  }
}
