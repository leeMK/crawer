package PageHandler;


//import com.baidu.aip.AnswerBean;
//import com.baidu.aip.ocr.AipOcr;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import qz.bigdata.crawler.browser.Browser;
import qz.bigdata.crawler.core.IPageHandler;
import qz.bigdata.crawler.core.PageHandler;
import qz.bigdata.crawler.core.SessionHandler;
import qz.bigdata.crawler.core.UrlInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fys on 2015/1/12.
 */
public class ContentPageHandler extends PageHandler {
    private String name = "";
//    private PostList postList;

    public ContentPageHandler() {
        this.onInit();
    }
    public ContentPageHandler(SessionHandler sessionHandler) {
        super(sessionHandler);
        this.onInit();
    }

    @Override
    public List<List<UrlInfo>> getPublicLinks(Browser browser)
    {
        //System.out.println(this.name + " getPublicLinks");
        return null;//new LinkedList<BrowseURL>();
    }
    @Override
    public List<UrlInfo> getPrivateLinks(Browser browser)
    {

//        System.out.println(this.name + "章节");
//        List<UrlInfo> list = new LinkedList<UrlInfo>();
//        if(list!=null) {
//            String nextUrl = "";
//            try {
//                //String nextUrl = this.browser.getEleAttribute("xpath", "//div[@class='pagebox'//a[@class='next']", "href");
//                //设置元素定位超时时间。1、全局设置为什么未生效。2、优化超时时间。
//                browser.getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//                //暂不开启 内容页中的 下一页 Url 提取，存在干扰连接~
////                if(BrowserUtil.Finds(browser.getWebDriver(), null, "linktext", "下一页").size()!=0) {
////                    nextUrl = browser.getEleAttribute("linktext", "下一页", "href");
////                    //nextUrl = browser.getEleAttribute("xpath", "//a[@class='next']", "href");
////                    if (nextUrl != null) {
////                        list.add(new UrlInfo(new URL(nextUrl),"suining.PageHandler.ScctForumPostContentPageHandler"));
////                        System.out.println("Content nextUrl :"+nextUrl);
////                    }
////                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return list;
        return null;
    }

    @Override
    public Boolean isMatch(UrlInfo bu)
    {
        //http://news.hexun.com/加发布日期 为match标准！ 以主站测试
        if(bu.url.toString().toLowerCase().contains("x")){
            return true;
        }
        if (bu.url.toString().toLowerCase().contains("x")) {
            return true;
        }
        return false;
    }
    @Override
    protected void onInit()
    {
        this.name = "Scct";
//        this.postList = new PostList();
    }
    @Override
    public void afterLoadPage(Browser browser)
    {
//        String title = browser.getEleText("classname", "art_title");
//        System.out.println(title);
    }
    @Override
    public boolean savePageData(Browser browser)
    {
//        AipOcr ocrclient = new AipOcr("16666419","Ppnuf2i5KGC0kQcQxbMZaKWE","iby0LPZUObsmYVVP7agOBp2E5GCuTZTb");

        if(this.urlInfo != null)
        {
            if (this.urlInfo.title != null);
            {
                browser.visit(String.valueOf(this.urlInfo.url));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(this.urlInfo.title);
                String title = this.urlInfo.title;
                browser.click("linktext",title);
                List<AnswerBean> result = new ArrayList<AnswerBean>();
                List<AnswerBean> mlsit = new ArrayList<AnswerBean>();
                //获取内容
                try {
                    boolean flag = true;
                    int pointer = 1;

                    while (flag) {
                        AnswerBean as = new AnswerBean();
                        System.out.println(pointer);
                        //切花使用
//                        browser.click("linktext", "查看答案");
                        browser.click("xpath","/html/body/table[2]/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td[1]/a[1]");
//                        String filename = System.getProperty("user.dir") + "/tmp/input/" + pointer + ".png";
                        Thread.sleep(1000);
                        String filename = "/Users/lee/Desktop/ocr/chache/input/" + pointer + ".png";
                        snapshotAndSave(browser, browser.find("classname", "pctmid"),filename);
                        File mFile = new File(filename);
                        if(!mFile.exists()){
                            snapshotAndSave(browser, browser.find("classname", "pctmid"),filename);
                        }
//                        JSONObject res = ocrclient.basicGeneral(mFile.getAbsolutePath(), new HashMap<String, String>());
//                        AnswerBean tmp = getABean(res);

                        String an = browser.getEleText("classname", "ks_st");
                        as.setNo(pointer+"");
                        as.setQuestion(an.split("\\n")[0]);

//                        as.setAnswer(tmp.getAnswer());
                        if (as.getQuestion().contains("") ||as.getQuestion().contains("")||as.getQuestion().contains(""))
                        {
//                            as.setQuestion(tmp.getQuestion());
                        }
                       //此方法未生效，禁用
//                        if (!checkNameChese(as.getQuestion()))
//                        {
//
//                            as.setQuestion(tmp.getQuestion());
//                        }
                        List<String> oplist = new ArrayList<String>();
                        for(int i = 1 ; i < an.split("\\n").length ;i++)
                        {
//                            System.out.println(an);
//                            System.out.println(an.split("\\n")[i]);
                            oplist.add(an.split("\\n")[i]);
                        }
                        as.setOption(oplist);
//                        System.out.println(browser.getEleText("classname", "plxanswer2"));
//                        browser.click("linktext", "下一题");
                        browser.click("xpath","/html/body/table[2]/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td[1]/a[4]");
                        Thread.sleep(500);
//                        if (pointer%5==0)
//                        {
//                            Thread.sleep(2000);
//                        }
//                        Thread.sleep(400);
                        //未生效，暂时关闭
//                        if (browser.find("linktext", "查看答案") == null) {
//                            flag = false;
//                        }
                        pointer++;
                        result.add(as);
//                        mlsit.add(tmp);


                    }
//                    writeObjectToFile(result,"/Users/lee/Desktop/ocr/result.txt");
//                    writeObjectToFile(mlsit,"/Users/lee/Desktop/ocr/mlsit.txt");
//                    saveData( result,"/Users/lee/Desktop/ocr/result.xls");
                }catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }finally {
                    writeObjectToFile(result,"/Users/lee/Desktop/ocr/chache/output/result.txt");
                    writeObjectToFile(mlsit,"/Users/lee/Desktop/ocr/chache/output/mlsit.txt");
                    saveData( result,"/Users/lee/Desktop/ocr/chache/output/result.xls");
                }


            }
        }
//        this.urlInfo.data = postList;
        return true;
    }
//    public boolean savePageData(Browser browser)
//    {
//        if(this.urlInfo != null)
//        {
//            if(this.urlInfo.data != null)//非第一页，
//            {
////                Post post = (Post)this.urlInfo.data;
//                //todo:取出回帖内容，加入到post中
//            }
//            else//帖子第一页
//            {
//                String title = this.urlInfo.title;
//                String source = this.urlInfo.source;
//                String area = this.urlInfo.area;
//                String countType = this.urlInfo.countType;
//                String page =  browser.getEleText("xpath","//div[@align='center']");
//                String  pubtime = publishTimeExtrator(page);
//                if (pubtime != null){
//                    page = page.substring(page.indexOf(pubtime)+pubtime.length());
//                    pubtime = pubtime.replaceAll("[\"[\\u4E00-\\u9FA5]\"]", "-");
//                }else{
//                    pubtime = "1970-01-01";
//                }
//
//                String contime = null;
//                try {
//                    contime = contentTimeExtrator(page);
//                }catch (Exception e){
//                    System.out.println("*************************************");
//                    System.out.println("        *********************        ");
//                    System.out.println("        *********************       ");
//                    System.out.println("*************************************");
//                }
//                int timeflag = 0;
//                if (pubtime != null && contime != null){
//                   timeflag = calcTime(pubtime,contime);
//                }
////                Post post=new Post(this.urlInfo.parentUrl==null?"":this.urlInfo.parentUrl.toString()
////                        ,this.urlInfo.url.toString(),null);
////                postList.addPost(post);
//                Date date = new Date();
//                String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
//                Timestamp time = Timestamp.valueOf(nowTime);
//                //自定义Mysql存储
//                if (title!=null && page!=null) {
//                    //todo 数据库存储
//                    MysqlForData dataStore = new MysqlForData();
//                    dataStore.storeData(source,time, title, this.urlInfo.url.toString(), page, pubtime
//                            ,contime,countType , area, timeflag);
//                }
//            }
//        }
////        this.urlInfo.data = postList;
//        return true;
//    }


    @Override
    public IPageHandler cloneInstance(SessionHandler sessionHandler)
    {
        IPageHandler ph = new ContentPageHandler(sessionHandler);
        return ph;
    }
    public File snapshotAndSave(Browser browser,WebElement element, String filePath) {
        File scrFile = ((TakesScreenshot) browser.getWebDriver()).getScreenshotAs(OutputType.FILE);//截图整个页面
        try {
            BufferedImage img = ImageIO.read(scrFile);
            // for all
//            FileUtils.copyFile(scrFile,new File(filePath));
            // 获得元素的高度和宽度
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight() ;
            // 创建一个矩形使用上面的高度，和宽度
            Rectangle rect = new Rectangle(width, height);
            // 得到元素的起始坐标
            int x = element.getLocation().getX();
            int y = element.getLocation().getY();
            //for mac
//            int width = 2000;
//            int height = 800 ;
//            // 创建一个矩形使用上面的高度，和宽度
//            Rectangle rect = new Rectangle(width, height);
//            // 得到元素的起始坐标
//            int x = 210;
//            int y = 210;
            //开始按坐标和区域截图
            BufferedImage dest = img.getSubimage(x, y, rect.width, rect.height);
            //存为png格式
            ImageIO.write(dest, "png", scrFile);

            System.out.println(filePath);
            File file = new File(filePath);
            FileUtils.copyFile(scrFile, file);
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static AnswerBean getABean(JSONObject jsonobject)throws  Exception{
        AnswerBean ab = new AnswerBean();
        List<String>  options =  new ArrayList<String>(); ;
        JSONArray partIndex = jsonobject.getJSONArray("words_result");
        int length = partIndex.length()-1;
        if (!partIndex.getJSONObject(1).getString("words").contains("○")){
            ab.setQuestion(partIndex.getJSONObject(0).getString("words")+partIndex.getJSONObject(1).getString("words"));

        }
        else {
            ab.setQuestion(partIndex.getJSONObject(0).getString("words"));
        }
//        System.out.println("quse:" + ab.getQuestion());
        ab.setAnswer(partIndex.getJSONObject(length).getString("words"));
//        System.out.println("answer:" + ab.getAnswer());

        for(int i=1 ;i<length; i++)
        {
            JSONObject ck =  partIndex.getJSONObject(i);
            String content  = ck.getString("words");

//            if (content.contains("已做") || content.contains("下一题最后") || content.contains("题上题下") ||content.contains("题总共:2490")
//                    ||content.contains("查看答案()"))
//            {
//                break;
//            }
            if ( content.contains("○") ||content.contains("A")||content.contains("B")||content.contains("C")||
                    content.contains("D")||content.contains("E")||content.contains("F")||content.contains("G")||content.contains("H")) {
                content = content.replace("○", "");
                options.add(content);
            }
        }
        ab.setOption(options);
        return ab;
    }
    public static <T> void writeObjectToFile(List<T> list,String filename) {
        File file = new File(filename);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(list);
            objOut.flush();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> readObjectFromFile(String filename) {
        File file = new File(filename);
        FileInputStream in;
        List<T> object = null;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            object = (List<T>) objIn.readObject();
            objIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (List<T>) object;
    }
    public static void saveData(List<AnswerBean> as,String path)  {
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        int label = 1;

        for (int row = 0; row < as.size(); row++) {
//                System.out.println(label);
            int point = 0;
            try {
//                    System.out.println(as.get(row).getQuestion());
                HSSFRow rows = sheet.createRow(row);
                rows.createCell(0).setCellValue(as.get(row).getNo());
                rows.createCell(1).setCellValue(as.get(row).getQuestion());
                if (as.get(row).getQuestion().contains("判断题")){
                    rows.createCell(2).setCellValue("判断题");
                    rows.createCell(3).setCellValue(as.get(row).getQuestion().trim().split("判断题")[1]);
                }
                if (as.get(row).getQuestion().contains("判斷题")){
                    rows.createCell(2).setCellValue("判断题");
                    rows.createCell(3).setCellValue(as.get(row).getQuestion().trim().split("判斷题")[1]);
                }
                if (as.get(row).getQuestion().contains("单选题")){
                    rows.createCell(2).setCellValue("单选题");
                    rows.createCell(3).setCellValue(as.get(row).getQuestion().trim().split("单选题")[1]);
                }
                if (as.get(row).getQuestion().contains("多选题")){
                    rows.createCell(2).setCellValue("多选题");
                    rows.createCell(3).setCellValue(as.get(row).getQuestion().trim().split("多选题")[1]);
                }
                if (as.get(row).getQuestion().contains("案例题")){
                    rows.createCell(2).setCellValue("案例题");
                    rows.createCell(3).setCellValue(as.get(row).getQuestion().trim().split("案例题")[1]);
                }

                for (int col = 1; col <= as.get(row).getOption().size(); col++) {
                    // 向工作表中添加数据
//                    System.out.println(as.get(row).getOption().get(col - 1));
                    rows.createCell(col + 3).setCellValue(as.get(row).getOption().get(col - 1));
                    point = col + 5;
                }
                rows.createCell(point).setCellValue(as.get(row).getAnswer());
                label++;
            }catch (Exception e){
                System.out.println("ERROR CODE: " + e.getMessage() + " Question is : " + as.get(row).getQuestion() );
            }
        }
        try {
            File xlsFile = new File(path);
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 校验String是否全是中文
     *
     * @param name
     *            被校验的字符串
     * @return true代表全是汉字
     */
    public static boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }
}