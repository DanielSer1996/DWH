package i5b5.wajaty.hd.projekt.model.sources.tv;

import i5b5.wajaty.hd.projekt.model.sources.SubjectActionClass;

public class TvChannel extends SubjectActionClass {
    private int tvChannelId;
    private String name;
    private String language;
    private String audio;
    private String video;
    private String group;

    public int getTvChannelId() {
        return tvChannelId;
    }

    public void setTvChannelId(int tvChannelId) {
        this.tvChannelId = tvChannelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
