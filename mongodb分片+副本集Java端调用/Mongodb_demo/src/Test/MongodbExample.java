package Test;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.BsonType;
import org.bson.Document;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class MongodbExample {

    public static void main(String[] args) throws ParseException {

        //20000�˿�Ϊ��Ƭ��Ⱥ��mongos�˿�
//        MongoClient mongoClient = new MongoClient("192.168.46.133", 20000);
        //�����֤ģʽ
        List<ServerAddress> seeds = new ArrayList<ServerAddress>();
        ServerAddress address1 = new ServerAddress("192.168.46.133" , 20000);
        seeds.add(address1);
        //��mongodb������֤
        MongoCredential credentials = MongoCredential.createScramSha1Credential("mongo", "mytest", "mongo".toCharArray());
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(credentials);
        MongoClient mongoClient = new MongoClient(seeds,credentialsList);
        MongoDatabase database = mongoClient.getDatabase("mytest");
        MongodbExample client = new MongodbExample(database);
        client.show();
        mongoClient.close();
    }

    private MongoDatabase database;

    public MongodbExample(MongoDatabase database) {
        this.database = database;
    }
    private static Gson gson = new Gson();
    public void show() {
        MongoCollection<Document> mc = database.getCollection("blog");
    /*    Foo foo;
        for(int i=1;i<=10000;i++) {
            foo = new Foo();
            foo.setId(i);
            foo.setName("name"+i);
            mc.insertOne(Document.parse(gson.toJson(foo)));
        }*/
        //ÿ��ִ��ǰ��ռ����Է����ظ�����
//        mc.drop();
 /*       for(int i=0;i<10000;i++) {
            //�������ڲ��Ե��ĵ�
          *//*  Document doc1 = new Document("title", "good day"+i).append("owner", "tom" + i).append("words", 300+i)
                    .append("comments", Arrays.asList(new Document("author", "joe" + i).append("score", 3).append("comment", "good" + i), new Document("author", "white" + i).append("score", 1).append("comment", "oh no" + i)));
            Document doc2 = new Document("title", "good"+i).append("owner", "john"+i).append("words", 400)
                    .append("comments", Arrays.asList(new Document("author", "william"+i).append("score", 4).append("comment", "good"+i), new Document("author", "white"+i).append("score", 6).append("comment", "very good"+i)));
            Document doc3 = new Document("title", "good night"+i).append("owner", "mike"+i).append("words", 200)
                    .append("tag", Arrays.asList(1, 2, 3, 4));
            Document doc4 = new Document("title", "happiness"+i).append("owner", "tom"+i).append("words", 1480)
                    .append("tag", Arrays.asList(2, 3, 4));
            Document doc5 = new Document("title", "a good thing"+i).append("owner", "tom"+i).append("words", 180)
                    .append("tag", Arrays.asList(1, 2, 3, 4, 5));
            mc.insertMany(Arrays.asList(doc1, doc2, doc3, doc4, doc5));*//*

            Document doc = new Document("userId",i).append("name","lihua").append("age",12+i);
              mc.insertOne(doc);
        }
*/
        //����: ��ѯȫ��
        FindIterable<Document> iterable = mc.find();
        printResult("find all", iterable);


//        toShow(mc);
        //TODO: ���������������ѯʾ��
    }

    //��ӡ��ѯ�Ľ����
    public void printResult(String doing, FindIterable<Document> iterable) {
        System.out.println(doing);
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
//                System.out.println(document.get("userId"));
            }
        });
        System.out.println("------------------------------------------------------");
        System.out.println();
    }


    public void toShow(MongoCollection<Document> mc) {
        //�������ֶ�����
        mc.createIndex(new Document("words", 1));
        //�����������(ͬ����ѭ����ǰ׺ԭ��)
        mc.createIndex(new Document("title", 1).append("owner", -1));
        //����ȫ������
        mc.createIndex(new Document("title", "text"));

        //��ѯȫ��
        FindIterable<Document> iterable = mc.find();
        printResult("find all", iterable);

        //��ѯtitle=good
        iterable = mc.find(new Document("title", "good"));
        printResult("find title=good", iterable);

        //��ѯtitle=good and owner=tom
        iterable = mc.find(new Document("title", "good").append("owner", "tom"));
        printResult("find title=good and owner=tom", iterable);

        //��ѯtitle like %good% and owner=tom
        iterable = mc.find(and(regex("title", "good"), eq("owner", "tom")));
        printResult("find title like %good% and owner=tom", iterable);

        //��ѯȫ����title����
        iterable = mc.find().sort(ascending("title"));
        printResult("find all and ascending title", iterable);

    //��ѯȫ����owner,title����
        iterable = mc.find().sort(ascending("owner", "title"));
        printResult("find all and ascending owner,title", iterable);

        //��ѯȫ����words��������
        iterable = mc.find().sort(descending("words"));
        printResult("find all and descending words", iterable);

       //��ѯowner=tom or words>350
        iterable = mc.find(new Document("$or", Arrays.asList(new Document("owner", "tom"), new Document("words", new Document("$gt", 350)))));
        printResult("find owner=tom or words>350", iterable);

        //����title��owner�ֶ�
        iterable = mc.find().projection(include("title", "owner"));
        printResult("find all include (title,owner)", iterable);

        //���س�title��������ֶ�
        iterable = mc.find().projection(exclude("title"));
        printResult("find all exclude title", iterable);

        //������_id�ֶ�
        iterable = mc.find().projection(excludeId());
        printResult("find all excludeId", iterable);

        //����title��owner�ֶ��Ҳ�����_id�ֶ�
        iterable = mc.find().projection(fields(include("title", "owner"), excludeId()));
        printResult("find all include (title,owner) and excludeId", iterable);

        //��Ƕ�ĵ�ƥ��
        iterable = mc.find(new Document("comments.author", "joe"));
        printResult("find comments.author=joe", iterable);

        //һ�������ʾ��, ���ѯ�����а���������white�ҷ�ֵ>2��, ���ؽ��������Ԥ��
        iterable = mc.find(new Document("comments.author", "white").append("comments.score", new Document("$gt", 2)));
        printResult("find comments.author=white and comments.score>2 (wrong)", iterable);

        //�����������ȷ��д��
        iterable = mc.find(Projections.elemMatch("comments", Filters.and(Filters.eq("author", "white"), Filters.gt("score", 2))));
        printResult("find comments.author=white and comments.score>2 using elemMatch", iterable);

        //����title��good��ͷ��, ����commentsֻ����һ��Ԫ��
        iterable = mc.find(Filters.regex("title", "^good")).projection(slice("comments", 1));
        printResult("find regex ^good and slice comments 1", iterable);

        //ȫ����������
        iterable = mc.find(text("good"));
        printResult("text good", iterable);

        //��Filters������title=good
        iterable = mc.find(eq("title", "good"));
        printResult("Filters: title eq good", iterable);

        //$in ��ͬ��sql��in
        iterable = mc.find(in("owner", "joe", "john", "william"));
        printResult("Filters: owner in joe,john,william", iterable);

        //$nin ��ͬ��sql��not in
        iterable = mc.find(nin("owner", "joe", "john", "tom"));
        printResult("Filters: owner nin joe,john,tom", iterable);

        //��ѯ��Ƕ�ĵ�
        iterable = mc.find(in("comments.author", "joe", "tom"));
        printResult("Filters: comments.author in joe,tom", iterable);

        //$ne ������
        iterable = mc.find(ne("words", 300));
        printResult("Filters: words ne 300", iterable);

        //$and �������
        iterable = mc.find(and(eq("owner", "tom"), gt("words", 300)));
        printResult("Filters: owner eq tom and words gt 300", iterable);

        //�ϸ��ӵ����
        iterable = mc.find(and(or(eq("words", 300), eq("words", 400)), or(eq("owner", "joe"), size("comments", 2))));
        printResult("Filters: (words=300 or words=400) and (owner=joe or size(comments)=2)", iterable);

        //��ѯ��2��Ԫ��ֵΪ2������
        iterable = mc.find(eq("tag.1", 2));
        printResult("Filters: tag.1 eq 2", iterable);

        //��ѯƥ��ȫ��ֵ������
        iterable = mc.find(all("tag", Arrays.asList(1, 2, 3, 4)));
        printResult("Filters: tag match all (1, 2, 3, 4)", iterable);

        //$exists
        iterable = mc.find(exists("tag"));
        printResult("Filters: exists tag", iterable);

        iterable = mc.find(type("words", BsonType.INT32));
        printResult("Filters: type words is int32", iterable);
    }

}
