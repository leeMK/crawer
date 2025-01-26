package PageHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import qz.bigdata.crawler.browser.Browser;
import qz.bigdata.crawler.core.IPageHandler;
import qz.bigdata.crawler.core.PageHandler;
import qz.bigdata.crawler.core.SessionHandler;
import qz.bigdata.crawler.core.UrlInfo;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fys on 2015/1/12.
 */
public class ScctForumPostListPageHandler extends PageHandler {
    private String name;
//    private PostList postList;
    public ScctForumPostListPageHandler()
    {
        this.onInit();
    }
    public ScctForumPostListPageHandler(SessionHandler sessionHandler)
    {
        super(sessionHandler);
        this.onInit();
    }
    @Override
    protected void onInit()
    {
        name = "Scct Forum suining.Post List";
//        this.postList = new PostList();
    }
    @Override
    public List<List<UrlInfo>> getPublicLinks(Browser browser)
    {
        return null;
    }
    @Override
    public List<UrlInfo> getPrivateLinks(Browser browser)
    {
        List<UrlInfo> listAndContentUrls = new LinkedList<UrlInfo>();
        System.out.println(this.name + " get Content Links");
//        listAndContentUrls.add();
        listAndContentUrls = getPostListByNormal(browser,listAndContentUrls);
//        System.out.println(this.name + " get next Link");
//        try {
//            browser.getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//            String nextUrl = browser.getEleAttribute("linktext", "下一页", "href");
//            if(nextUrl != null) {
//                System.out.println("List nextUrl :"+nextUrl);
//                listAndContentUrls.add(new UrlInfo(new URL(nextUrl),this.urlInfo.source,this.urlInfo.countType,this.urlInfo.area,null,null,null,"ScctForumPostListPageHandler"));
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        return listAndContentUrls;
    }
    @Override
    /**
     * 是否符合 页面处理规则
     */
    public Boolean isMatch(UrlInfo bu)
    {
        if (bu.url.toString().toLowerCase().contains("tskspx")) {
            return true;
        }
//        if (bu.url.toString().toLowerCase().contains("tskspx")) {
//            return true;
//        }
        return false;
    }
    @Override
    public void afterLoadPage(Browser browser) {
    }
    @Override
    /**
     * 存储数据
     */
    public boolean savePageData(Browser browser)
    {
        return false;
    }

    @Override
    public IPageHandler cloneInstance(SessionHandler sessionHandler)
    {
        IPageHandler ph = new ScctForumPostListPageHandler(sessionHandler);
        return ph;
    }
    //通过精准提取 标题和时间  PS:暂未使用，有待优化
    public  List<UrlInfo> getPostList (Browser browser, List<UrlInfo> listAndContentUrls){
        try {
            List<WebElement> elementList = browser.finds("xpath", "//table[@cellpadding=\"12\"]/tbody/tr/td/table/tbody/tr[*]/td[@valign=\"middle\"]");
            if(elementList.isEmpty()){
                System.out.println("未找到List Url ,请检查是否页面改版！");
                return null;
            }
            String urlNow = browser.getUrl();
            for (WebElement temp : elementList){
                try {
                    WebElement urlEle = temp.findElement(By.tagName("a"));
                    String url = urlEle.getAttribute("href");
                    String title = urlEle.getText();
                    String time = urlEle.findElement(By.xpath("//td[@class=\"heis12x\"]")).getText().replace("\\[|\\]","");
                    listAndContentUrls.add(new UrlInfo(new URL(url), this.urlInfo.source,this.urlInfo.countType,this.urlInfo.area,title, null,null, "ScctForumPostContentPageHandler"));
                }catch (Exception e){
                    continue;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error: " + ex.getMessage());
        }
        return  listAndContentUrls;
    }
    //精准提取失败后 通过常规方式提取 标题，通过内容解析提取时间
    public List<UrlInfo> getPostListByNormal (Browser browser,List<UrlInfo> listAndContentUrls){
        try {
            List<WebElement> elementList = browser.finds("classname", "nod1");
            System.out.println("当前URL : " + browser.getUrl());
            String urlNow = browser.getUrl();
            for (WebElement temp : elementList){
                System.out.println("title : " + temp.getText());
                String url = browser.getUrl();
                String title =  temp.getText();
//                if (url.contains("无障碍浏览")){
//                    System.out.println("需要过滤的导航链接");
//                    continue;
//                }
//                if (!url.contains(urlNow)) {
                    listAndContentUrls.add(new UrlInfo(new URL(url),this.urlInfo.source,this.urlInfo.countType,this.urlInfo.area,title,null,null,"ScctForumPostContentPageHandler"));
//                }
                break;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error: " + ex.getMessage());
        }
        return listAndContentUrls;
    }
}
