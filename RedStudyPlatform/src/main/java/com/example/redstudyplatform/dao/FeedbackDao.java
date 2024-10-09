package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.Feedback;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedbackDao {

    // 查询指定活动ID的所有评论信息
    @Select("SELECT f.commentID AS commentID, f.commentContent AS commentContent, f.score AS score, " +
            "s.stusername AS stusername, s.stname AS stname, " +
            "sa.activityName AS activityName, sa.activityID AS activityID " +
            "FROM feedback f " +
            "JOIN study s ON f.stuid = s.stuid " +
            "JOIN specialactivity sa ON f.activityID = sa.activityID " +
            "WHERE f.activityID = #{activityID}")
    @Results({
            @Result(property = "commentID", column = "commentID"),
            @Result(property = "commentContent", column = "commentContent"),
            @Result(property = "score", column = "score"),
            @Result(property = "study.username", column = "stusername"),
            @Result(property = "study.name", column = "stname"),
            @Result(property = "specialActivity.activityName", column = "activityName"),
            @Result(property = "specialActivity.activityID", column = "activityID")
    })
    public List<Feedback> findAllFeedbackByActivityID(int activityID);

    // 用户评价
    @Insert("INSERT INTO feedback (stuid, commentContent, score, activityID) " +
            "VALUES (#{study.stuid}, #{commentContent}, #{score}, #{specialActivity.activityID})")
    @Options(useGeneratedKeys = true, keyProperty = "commentID")
    void addFeedback(Feedback feedback);
}

