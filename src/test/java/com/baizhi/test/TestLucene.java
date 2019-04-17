package com.baizhi.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestLucene {
    @Test
    public void testDelete() throws IOException {
        Directory dir = FSDirectory.open(new File("E:/资料（全部拷贝）/后期项目/lucene/index"));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
        IndexWriterConfig config =  new IndexWriterConfig(Version.LUCENE_44,analyzer);
        IndexWriter indexWriter = new IndexWriter(dir,config);
        indexWriter.deleteAll();
        //indexWriter.deleteDocuments(new TermQuery(new Term("content","橘")));
        indexWriter.commit();
        indexWriter.close();
    }
    @Test
    public void testUpdate() throws IOException {
        Directory dir = FSDirectory.open(new File("E:/资料（全部拷贝）/后期项目/lucene/index"));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44,analyzer);
        IndexWriter indexWriter = new IndexWriter(dir,config);
        Document document = new Document();
        document.add(new StringField("id","10", Field.Store.YES));
        document.add(new StringField("title","背影", Field.Store.YES));
        document.add(new StringField("author","朱自清", Field.Store.YES));
        document.add(new TextField("content","你在此地不要动，我去给你买几个香蕉", Field.Store.YES));
        document.add(new StringField("date","2019-04-11",Field.Store.YES));
        indexWriter.updateDocument(new Term("id","10"),new Document());
        indexWriter.commit();
        indexWriter.close();


    }
}
