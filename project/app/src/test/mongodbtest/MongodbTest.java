package mongodbtest;

import base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class MongodbTest{

    private MongoTemplate mongoTemplate;


    @Before
    public void testBefore() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mongodb.xml");
        mongoTemplate = (MongoTemplate) context.getBean("mongoTemplate");
    }

    /**
     * 插入用户信息
     */
    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("wwy");
        user.setAge(28);
        /*List<String> interests = new ArrayList<String>();
        interests.add("stuty");
        interests.add("hadoop");
        zhanggc.setInterest(interests);
        Home home = new Home("henan");
        zhanggc.setHome(home);*/

        // 插入数据
        mongoTemplate.insert(user);
    }

    /**
     * 查询用户信息
     */
    @Test
    public void testQueryUser() {
        // 查询主要用到Query和Criteria两个对象
        Query query = new Query();
        Criteria criteria = where("age").gt(22);    // 大于

        // criteria.and("name").is("cuichongfei");等于
        // List<String> interests = new ArrayList<String>();
        // interests.add("study");
        // interests.add("linux");
        // criteria.and("interest").in(interests); in查询
        // criteria.and("home.address").is("henan"); 内嵌文档查询
        // criteria.and("").exists(false); 列存在
        // criteria.and("").lte(); 小于等于
        // criteria.and("").regex(""); 正则表达式
        // criteria.and("").ne(""); 不等于
        // 多条件查询
        // criteria.orOperator(Criteria.where("key1").is("0"),Criteria.where("key1").is(null));

        /*query.addCriteria(criteria);
        List<User> userList1 = mongoTemplate.find(query, User.class);
        printList(userList1);*/

        // 排序查询sort方法,按照age降序排列
         /*query.sort().on("age", Order.DESCENDING);
         List<User> userList2 = mongoTemplate.find(query, User.class);
         printList(userList2);*/

   // 排序　
        Query query1 = new Query();
         query1.with(new Sort(Sort.Direction.ASC, "age").and(new Sort(Sort.Direction.ASC, "create_time")));


        // 指定字段查询,只查询age和name两个字段
        // query.fields().include("age").include("name");
        // List<User> userList3 = mongoTemplate.find(query, User.class);
        // printList(userList3);

        // 分页查询
         //query1.skip(1).limit(3);
        query1.limit(5);
         List<User> userList4 = mongoTemplate.find(query1, User.class);
         printList(userList4);

        // 查询所有
        // printList(mongoTemplate.findAll(User.class));

        // 统计数据量
        // System.out.println(mongoTemplate.count(query, User.class));

    }

    public void printList(List<User> userList) {
        for (User user : userList) {
            System.out.println(user);
        }

    }
        /**
         * 更新用户数据
         */
/*    @Test
    public void testUpdateUser() {
        // update(query,update,class)
        // Query query:需要更新哪些用户,查询参数
        // Update update:操作符,需要对数据做什么更新
        // Class class:实体类

        // 更新age大于24的用户信息
        Query query = new Query();
        query.addCriteria(where("age").gt(24));

        Update update = new Update();
        // age值加2
        update.inc("age", 2);
        // update.set("name", "zhangsan"); 直接赋值
        // update.unset("name"); 删去字段
        // update.push("interest", "java"); 把java追加到interest里面,interest一定得是数组
        // update.pushAll("interest", new String[]{".net","mq"})
        // 用法同push,只是pushAll一定可以追加多个值到一个数组字段内
        // update.pull("interest", "study"); 作用和push相反,从interest字段中删除一个等于value的值
        // update.pullAll("interest", new String[]{"sing","dota"})作用和pushAll相反
        // update.addToSet("interest", "study") 把一个值添加到数组字段中,而且只有当这个值不在数组内的时候才增加
        // update.rename("oldName", "newName") 字段重命名

        // 只更新第一条记录,age加2,name值更新为zhangsan
        mongoTemplate.updateFirst(query, new Update().inc("age", 2).set("name", "zhangsan"), User.class);

        // 批量更新,更新所有查询到的数据
        mongoTemplate.updateMulti(query, update, User.class);

    }*/

    /**
     * 测试删除数据
     */
/*    @Ignore
    @Test
    public void testRemoveUser() {
        Query query = new Query();
        // query.addCriteria(where("age").gt(22));
        Criteria criteria = where("age").gt(22);
        // 删除年龄大于22岁的用户
        query.addCriteria(criteria);
        mongoTemplate.remove(query, User.class);
    }

}*/

    /**
     * 数组文档指定元素更新
     */
 //   @Test
/*    public void testUpdateDocument() {
        // 只更新一个内嵌文档
//        Update update = new Update();
//        update.set("sonModelList.$.state", 2);
//        mongoTemplate.updateMulti(new Query(Criteria.where("sonModelList.geoNum").is("532568248122605568")), update, CatchThirteenthModel.class);

        // 批量更新所有的内嵌文档
//        update.set("sonModelList.$.state", 2);
//        mongoTemplate.updateMulti(new Query(Criteria.where("sessionId").is("cf23e870-2c5a-4d1f-9652-6fa1793dc8be")),
//                new Update().set("state", 2), CatchThirteenthModel.class);
//        mongoTemplate.updateMulti(new Query(Criteria.where("sonModelList.parentGeoNum").is("532568523000512512")),
//                new Update().set("state", 2), CatchFifteenthModel.class);
        List<CatchFifteenthModel> find = mongoTemplate.find(new Query(where("sonModelList.parentGeoNum").is("532568523000512512")), CatchFifteenthModel.class);
        System.out.println("");
    }*/

    /**
     * 测试findAndModify方法
     */
 /*   @Test
    public void testFindAndModify() {

        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("geoNum").is("526547322448904192"), Criteria.where("geoLevel").is(14));
        CatchModel model = mongoTemplate.findAndModify(new Query(criteria), new Update().set("state", 2).set("endTime", new Date()), new FindAndModifyOptions().returnNew(true), CatchModel.class);

        System.out.println(model);
    }*/

}