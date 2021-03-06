// Autogenerated from Pigeon (v1.0.7), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.fiirb.wuxia_app_java.models;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class WuxiaModels {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class NovelInfo {
    private String name;
    public String getName() { return name; }
    public void setName(String setterArg) { this.name = setterArg; }

    private String imgUrl;
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String setterArg) { this.imgUrl = setterArg; }

    private String url;
    public String getUrl() { return url; }
    public void setUrl(String setterArg) { this.url = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("name", name);
      toMapResult.put("imgUrl", imgUrl);
      toMapResult.put("url", url);
      return toMapResult;
    }
    static NovelInfo fromMap(Map<String, Object> map) {
      NovelInfo fromMapResult = new NovelInfo();
      Object name = map.get("name");
      fromMapResult.name = (String)name;
      Object imgUrl = map.get("imgUrl");
      fromMapResult.imgUrl = (String)imgUrl;
      Object url = map.get("url");
      fromMapResult.url = (String)url;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class ChapterElm {
    private String name;
    public String getName() { return name; }
    public void setName(String setterArg) { this.name = setterArg; }

    private String urlPath;
    public String getUrlPath() { return urlPath; }
    public void setUrlPath(String setterArg) { this.urlPath = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("name", name);
      toMapResult.put("urlPath", urlPath);
      return toMapResult;
    }
    static ChapterElm fromMap(Map<String, Object> map) {
      ChapterElm fromMapResult = new ChapterElm();
      Object name = map.get("name");
      fromMapResult.name = (String)name;
      Object urlPath = map.get("urlPath");
      fromMapResult.urlPath = (String)urlPath;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class ChapterInfo {
    private String name;
    public String getName() { return name; }
    public void setName(String setterArg) { this.name = setterArg; }

    private String imgUrl;
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String setterArg) { this.imgUrl = setterArg; }

    private List<ChapterElm> chapters;
    public List<ChapterElm> getChapters() { return chapters; }
    public void setChapters(List<ChapterElm> setterArg) { this.chapters = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("name", name);
      toMapResult.put("imgUrl", imgUrl);
      toMapResult.put("chapters", chapters);
      return toMapResult;
    }
    static ChapterInfo fromMap(Map<String, Object> map) {
      ChapterInfo fromMapResult = new ChapterInfo();
      Object name = map.get("name");
      fromMapResult.name = (String)name;
      Object imgUrl = map.get("imgUrl");
      fromMapResult.imgUrl = (String)imgUrl;
      Object chapters = map.get("chapters");
      fromMapResult.chapters = (List<ChapterElm>)chapters;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class Chapter {
    private String title;
    public String getTitle() { return title; }
    public void setTitle(String setterArg) { this.title = setterArg; }

    private List<String> chapter;
    public List<String> getChapter() { return chapter; }
    public void setChapter(List<String> setterArg) { this.chapter = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("title", title);
      toMapResult.put("chapter", chapter);
      return toMapResult;
    }
    static Chapter fromMap(Map<String, Object> map) {
      Chapter fromMapResult = new Chapter();
      Object title = map.get("title");
      fromMapResult.title = (String)title;
      Object chapter = map.get("chapter");
      fromMapResult.chapter = (List<String>)chapter;
      return fromMapResult;
    }
  }
  private static class NovelApiCodec extends StandardMessageCodec {
    public static final NovelApiCodec INSTANCE = new NovelApiCodec();
    private NovelApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return Chapter.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)129:         
          return ChapterElm.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)130:         
          return ChapterInfo.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)131:         
          return ChapterInfo.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)132:         
          return NovelInfo.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)133:         
          return NovelInfo.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)134:         
          return NovelInfo.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof Chapter) {
        stream.write(128);
        writeValue(stream, ((Chapter) value).toMap());
      } else 
      if (value instanceof ChapterElm) {
        stream.write(129);
        writeValue(stream, ((ChapterElm) value).toMap());
      } else 
      if (value instanceof ChapterInfo) {
        stream.write(130);
        writeValue(stream, ((ChapterInfo) value).toMap());
      } else 
      if (value instanceof ChapterInfo) {
        stream.write(131);
        writeValue(stream, ((ChapterInfo) value).toMap());
      } else 
      if (value instanceof NovelInfo) {
        stream.write(132);
        writeValue(stream, ((NovelInfo) value).toMap());
      } else 
      if (value instanceof NovelInfo) {
        stream.write(133);
        writeValue(stream, ((NovelInfo) value).toMap());
      } else 
      if (value instanceof NovelInfo) {
        stream.write(134);
        writeValue(stream, ((NovelInfo) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface NovelApi {
    List<NovelInfo> listNovels();
    List<NovelInfo> findNovels(String search);
    ChapterInfo listNovelChapters(String novelName);
    Chapter readNovelChapter(String novelName, String chapterName);
    void downloadChapters(NovelInfo novelInfo, ChapterInfo chapters);

    /** The codec used by NovelApi. */
    static MessageCodec<Object> getCodec() {
      return NovelApiCodec.INSTANCE;
    }

    /** Sets up an instance of `NovelApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, NovelApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NovelApi.listNovels", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              List<NovelInfo> output = api.listNovels();
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NovelApi.findNovels", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String searchArg = (String)args.get(0);
              if (searchArg == null) {
                throw new NullPointerException("searchArg unexpectedly null.");
              }
              List<NovelInfo> output = api.findNovels(searchArg);
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NovelApi.listNovelChapters", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String novelNameArg = (String)args.get(0);
              if (novelNameArg == null) {
                throw new NullPointerException("novelNameArg unexpectedly null.");
              }
              ChapterInfo output = api.listNovelChapters(novelNameArg);
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NovelApi.readNovelChapter", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String novelNameArg = (String)args.get(0);
              if (novelNameArg == null) {
                throw new NullPointerException("novelNameArg unexpectedly null.");
              }
              String chapterNameArg = (String)args.get(1);
              if (chapterNameArg == null) {
                throw new NullPointerException("chapterNameArg unexpectedly null.");
              }
              Chapter output = api.readNovelChapter(novelNameArg, chapterNameArg);
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NovelApi.downloadChapters", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              NovelInfo novelInfoArg = (NovelInfo)args.get(0);
              if (novelInfoArg == null) {
                throw new NullPointerException("novelInfoArg unexpectedly null.");
              }
              ChapterInfo chaptersArg = (ChapterInfo)args.get(1);
              if (chaptersArg == null) {
                throw new NullPointerException("chaptersArg unexpectedly null.");
              }
              api.downloadChapters(novelInfoArg, chaptersArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", null);
    return errorMap;
  }
}
