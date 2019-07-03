package com.example.myapplication.okhttp.domain;

import java.util.List;

public class DataBean {

    private List<ItemData> trailers;

    public List<ItemData> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<ItemData> trailers) {
        this.trailers = trailers;
    }

    public  static  class ItemData {
        /**
         * id : 75270
         * movieName : 小K领衔新版《霹雳娇娃》帅酷预告
         * coverImg : http://img5.mtime.cn/mg/2019/06/27/224744.68512147_120X90X4.jpg
         * movieId : 228417
         * url : http://vfx.mtime.cn/Video/2019/06/28/mp4/190628075308350550.mp4
         * hightUrl : http://vfx.mtime.cn/Video/2019/06/28/mp4/190628075308350550.mp4
         * videoTitle : 霹雳娇娃 首款预告片
         * videoLength : 157
         * rating : 0
         * type : ["动作","冒险","喜剧"]
         * summary :
         */

        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private float rating;
        private String summary;
        private List<String> type;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public int getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}
