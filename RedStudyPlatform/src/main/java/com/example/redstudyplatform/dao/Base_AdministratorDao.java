package com.example.redstudyplatform.dao;


import com.example.redstudyplatform.domain.Base_Administrator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Base_AdministratorDao {

    // 添加基地管理员
    @Insert("insert into Base_Administrator (bausername, bapassword, baname, barole) " +
            "values(#{username}, #{password}, #{name}, #{role})")
    public void add(Base_Administrator baseAdministrator);

    // 根据用户名查询基地管理员
    @Select("select bauid as uid, bausername as username, bapassword as password, baname as name, barole as role " +
            "from Base_Administrator where bausername = #{username}")
    public Base_Administrator getByUsername(String username);

    // 统计总基地管理员数量
    @Select("select count(*) from base_administrator")
    public int countAll();
}
