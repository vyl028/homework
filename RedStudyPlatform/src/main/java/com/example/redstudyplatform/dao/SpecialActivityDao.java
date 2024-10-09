package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.SpecialActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SpecialActivityDao {
    @Select("SELECT * from specialactivity where activityID = #{activityID}")
    public SpecialActivity getByActivityID(int activityID);

    // 统计总数量
    @Select("select count(*) from specialActivity")
    public int countAll();
}
