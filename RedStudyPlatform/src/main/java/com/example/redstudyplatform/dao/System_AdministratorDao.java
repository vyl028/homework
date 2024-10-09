package com.example.redstudyplatform.dao;


import com.example.redstudyplatform.domain.System_Administrator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface System_AdministratorDao {

    @Insert("insert into System_Administrator (sausername,sapassword) values(#{username},#{password})")
    public void add(@Param("username") String username, @Param("password") String password);

    @Select("select sauid as uid, sausername as username, sapassword as password from System_Administrator where sausername = #{username}")
    public System_Administrator getByUsername(String username);
}
