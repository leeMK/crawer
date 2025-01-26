
import qz.bigdata.crawler.core.*;
import  PageHandler.*;
import java.net.URL;


/**
 * Created by fys on 2015/1/12.
 */
public class ScctEntry {
    public static void main(String[] args) throws Exception {

//        try
//        {
//            List<String> value = FileUtil.readColumnsFile(args[0]);
//            List<UrlInfo> UrlInfos = new LinkedList<UrlInfo>();
//            try {
//              for(String tempString : value){
//                    String[] tempVal = tempString.split(" ");
//                    UrlInfo urlInfo = new UrlInfo(new URL(tempVal[1])
//                            ,tempVal[2],tempVal[3],tempVal[4],null,null,null,"ScctForumPostListPageHandler");
//                  UrlInfos.add(urlInfo);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            for (UrlInfo temp:UrlInfos){
//                BrowserManager.insertUrlInfo(temp);
//            }
//        }
//        catch (InterruptedException ex)
//        {
//            System.out.print(ex.getMessage());
//            return;
//        }
//        catch (Exception ex)
//        {
//            System.out.print(ex.getMessage());
//            return;
//        }
        URL url = new URL("http://tjzj.tskspx.com/tjzj/zj/index.html");
        UrlInfo urlInfo = new UrlInfo(url);
        BrowserManager.insertUrlInfo(urlInfo);
        BrowserManager bm = new BrowserManager("ScctTest");
        SessionHandler sessionHandler = new ScctForumSessionHandler();
        BrowserController.registerSessionHandler(sessionHandler);
        IPageHandler postList = new ScctForumPostListPageHandler(sessionHandler);
        IPageHandler postContent = new ScctForumPostContentPageHandler(sessionHandler);
        BrowserController.registerPageHandler(postList);
        BrowserController.registerPageHandler(postContent);
        bm.start();
    }
}
