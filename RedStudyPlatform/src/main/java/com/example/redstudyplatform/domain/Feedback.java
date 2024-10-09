package com.example.redstudyplatform.domain;

public class Feedback {
    private int commentID;//反馈ID
    private Study study;//发表反馈的用户
    private SpecialActivity specialActivity;//对应活动
    private String commentContent;//内容
    private double score;//用户打分

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public SpecialActivity getSpecialActivity() {
        return specialActivity;
    }

    public void setSpecialActivity(SpecialActivity specialActivity) {
        this.specialActivity = specialActivity;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "commentID=" + commentID +
                ", study=" + study +
                ", specialActivity=" + specialActivity +
                ", commentContent='" + commentContent + '\'' +
                ", score=" + score +
                '}';
    }
}