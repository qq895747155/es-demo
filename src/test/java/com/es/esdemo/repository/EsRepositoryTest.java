package com.es.esdemo.repository;

import com.es.esdemo.EsdemoApplication;
import com.es.esdemo.document.Article;
import com.es.esdemo.document.Author;
import com.es.esdemo.document.Tutorial;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsdemoApplication.class)
public class EsRepositoryTest {

    @Autowired
    private EsRepository esRepository;

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void ceratIndex(){
        Author author=new Author(1L,"handl","da niu");

        Tutorial tutorial=new Tutorial(1L,"elastic");

        Article article =new Article(1L,"springboot integreate elasticsearch",
                "springboot integreate elasticsearch is very easy",
                "elasticsearch based on lucene,spring-data-elastichsearch based on elaticsearch,this tutorial tell you how to integrete springboot with spring-data-elasticsearch",
                new Date(),1L,author,tutorial);

        //创建保存索引
        esRepository.save(article);
    }

    @Test
    public void testSearch(){
        Iterator<Article> iterator;
        //使用elasticsearchTemplate方式查询
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery("springboot")).build();
//        List<Article> list = elasticsearchTemplate.queryForList(searchQuery,Article.class);
//        iterator = list.iterator();

        //使用elasticsearchRespository方式查询
        String queryString="springboot";//搜索关键字
        QueryStringQueryBuilder builder=new QueryStringQueryBuilder(queryString);
        Iterable<Article> searchResult = esRepository.search(builder);
        iterator = searchResult.iterator();

        //打印查询结果
        while(iterator.hasNext()){
            Article article = iterator.next();
            System.out.println("-----------"+article);
            System.out.println("-----------"+article.getAuthor());
            System.out.println("-----------"+article.getTutorial());
        }
    }
}
