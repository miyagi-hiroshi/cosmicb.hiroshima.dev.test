package net.supportdoc.helloworld.action;
import net.supportdoc.helloworld.model.HelloModel;
import net.supportdoc.helloworld.model.GoodbyModel;
import net.supportdoc.helloworld.model.SqlModel;
import java.util.Objects;
import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -2568853932910163422L;
    private HelloModel hello;
    private GoodbyModel goodby;
    private SqlModel sql;
    
    public String create() throws ClassNotFoundException {
        hello = new HelloModel();
        sql = new SqlModel();
        if (Objects.equals(goodby, null)){
            goodby = new GoodbyModel();            
            // goodby.setCompany("aaa");
            // goodby.setName("fff");
            // goodby.setNum("2");
        }
        System.out.println("【HelloAction:create success】");
 
        return "success";
    }

    public HelloModel getHello() {
        System.out.println("【HelloAction:getHello】" + hello);
        return hello;
    }

    public void setHello(HelloModel hello) {
        System.out.println("HelloAction:setHello】" + hello);
        this.hello = hello;
    }

    public GoodbyModel getGoodby() {
        System.out.println("【HelloAction:getGoodby】" + goodby);
        return goodby;
    }

    public void setGoodby(GoodbyModel goodby) {
        System.out.println("【HelloAction:setGoodby】" + goodby);
        this.goodby = goodby;
    }

    public SqlModel getSql() {
        System.out.println("【HelloAction:getSql】" + sql);
        return sql;
    }

    public void setSql(SqlModel sql) {
        System.out.println("【HelloAction:setSql】" + sql);
        this.sql = sql;
    } 

}
