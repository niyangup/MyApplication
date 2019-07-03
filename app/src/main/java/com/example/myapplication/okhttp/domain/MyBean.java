package com.example.myapplication.okhttp.domain;

public class MyBean {

    /**
     * id : 74862
     * movieName : 《爱宠大机密2》乡村冒险预告
     * coverImg : http://img5.mtime.cn/mg/2019/05/24/102859.56156442_120X90X4.jpg
     * movieId : 236010
     */

    private int id;
    private String movieName;
    private String coverImg;
    private int movieId;

    public MyBean() {}

    public MyBean(int id, String movieName, String coverImg, int movieId) {
        this.id = id;
        this.movieName = movieName;
        this.coverImg = coverImg;
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", movieId=" + movieId +
                '}';
    }

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


}
