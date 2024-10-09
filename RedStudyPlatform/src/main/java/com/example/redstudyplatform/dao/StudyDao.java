package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.Study;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudyDao {
    // 添加用户
    @Insert("insert into study (stusername, stpassword, sttelephone, stcompany, stname, stlocation, strole) " +
            "values(#{username}, #{password}, #{telephone}, #{company}, #{name}, #{location}, #{role})")
    public void add(Study study);

    // 查询根据名字用户
    @Select("select stuid as uid, stusername as username, stpassword as password, sttelephone as telephone, " +
            "stcompany as company, stname as name, stlocation as location, strole as role " +
            "from study where stusername = #{username}")
    public Study getByUsername(String username);

    // 更新用户信息
    @Update("update study set stusername = #{username}, stpassword = #{password}, sttelephone = #{telephone}, " +
            "stcompany = #{company}, stname = #{name}, stlocation = #{location}, strole = #{role} " +
            "where stuid = #{uid}")
    public void update(Study study);

    // 统计总用户数量
    @Select("select count(*) from study")
    public int countAll();
}