package com.baizhi.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class CreateIndex {
    public static void main(String[] args) throws IOException {
        //创建标准分词器对象
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
        //声明一个文件夹为索引库
        Directory dir = FSDirectory.open(new File("E:/资料（全部拷贝）/后期项目/lucene/index"));
        //索引写入器的相关配置
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44, analyzer);
        //参数1：索引库的目录     参数2：  索引写入器的相关配置
        IndexWriter indexWriter = new IndexWriter(dir,conf);
        for (int i = 0; i < 10; i++) {
            Document document = new Document();
            document.add(new StringField("id",i+"", Field.Store.YES));
            document.add(new StringField("title","背影", Field.Store.YES));
            document.add(new StringField("author","朱自清", Field.Store.YES));
            document.add(new TextField("content","你在此地不要动，我去给你买几个橘子", Field.Store.YES));
            document.add(new StringField("date","2019-04-11", Field.Store.YES));
            indexWriter.addDocument(document);
        }

        indexWriter.commit();
        indexWriter.close();

    }
}
