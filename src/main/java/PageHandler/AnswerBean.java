package PageHandler;

import java.io.Serializable;
import java.util.List;

public class AnswerBean implements Serializable {
    private String question;
    private List<String> option;
    private String answer;
    private String no;
    private String type;
    private String image;


    public AnswerBean() {
        this.question = question;
        this.option = option;
        this.answer = answer;
    }
    public AnswerBean(String question, List<String> option, String answer) {
        this.question = question;
        this.option = option;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String>  getOption() {
        return option;
    }

    public void setOption(List<String>  option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
