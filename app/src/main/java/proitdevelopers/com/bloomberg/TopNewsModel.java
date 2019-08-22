package proitdevelopers.com.bloomberg;

public class TopNewsModel {
    private String img,topic,title;

    public TopNewsModel() {
    }

    public TopNewsModel(String img, String topic, String title) {
        this.img = img;
        this.topic = topic;
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
