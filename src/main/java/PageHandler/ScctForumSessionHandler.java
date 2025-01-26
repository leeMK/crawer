package PageHandler;

import PageHandler.*;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import qz.bigdata.crawler.browser.Browser;
import qz.bigdata.crawler.core.SessionHandler;

/**
 * Created by fys on 2015/1/19.
 */
public class ScctForumSessionHandler extends SessionHandler {
    public Boolean login = false;
     static String Sname = "金属";

    @Override
    public void beforeStartSession(Browser browser) {
        browser.visit("http://tjzj.tskspx.com/tjzj/zj/index.html");
//        System.out.println(browser.getPage());

        if (browser.find("linktext", "Submit") == null) {

            try {
//                browser.visit("http://tjzj.tskspx.com/tjzj/zj/index.html");
//                Thread.sleep(1000);
                //System.out.println(browser.getPage());
                if (browser.find("name", "Submit") != null) {
                    browser.visit("http://tjzj.tskspx.com/tjzj/zj/index.html");
//                    browser.waitEle("name", "email", 6000);
                    browser.input("name", "userid", "15922071851");
                    Thread.sleep(1000);
                    browser.inputSubmit("id", "password", "123456");

                    Thread.sleep(1000);
                    browser.click("classname", "nav1");
                    Thread.sleep(1000);
//                    browser.visit("http://tjzj.tskspx.com/user/1724639/getCurrentQuestion.do?currNum=1&zsdDg=0195");
//                    System.out.println(browser.find("linktext","查看答案"));
//                    System.out.println(browser.find("xpath","/html/body/table[2]/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td[1]/a[1]"));
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            this.login = true;
        }
    }
}
