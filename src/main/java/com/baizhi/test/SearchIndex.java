package com.baizhi.test;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

public class SearchIndex {
    //标准分词器  八种基本类型+String类型不分词
    //标准分词器单字分词
    public static void main(String[] args) throws IOException {
        //指定索引搜索器索引库的位置
        Directory dir = FSDirectory.open(new File("E:/资料（全部拷贝）/后期项目/lucene/index"));

        IndexReader reader = DirectoryReader.open(dir);
        //创建索引搜素器对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        //指定查询的条件与内容
        Query query = new TermQuery(new Term("content","香"));
        TopDocs topDocs = indexSearcher.search(query, 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i = 0; i < scoreDocs.length; i++) {
            ScoreDoc scoreDoc = scoreDocs[i];
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            System.out.println("分数"+scoreDoc.score);
            System.out.println("编号"+document.get("id"));
            System.out.println("标题"+document.get("title"));
            System.out.println("作者"+document.get("author"));
            System.out.println("内容"+document.get("content"));
            System.out.println("时间"+document.get("date"));
            System.out.println("时间"+document.get("date"));
        }
    }

}
