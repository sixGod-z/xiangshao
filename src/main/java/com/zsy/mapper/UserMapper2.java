package com.zsy.mapper;

import com.zsy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2020/2/24 15:18
 * @Created by hero
 */
@Mapper
public interface UserMapper2 {
//    //添加用户
//    @Insert("insert into user value(default,#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
//    void insUser(User user);
//    //根据token查询用户
//    @Select("select * from user where token=#{0}")
//    User selByToken(String token);
//    //根据id查询用户
//    @Select("select * from user where id=#{0}")
//    User selById(Integer id);
//
//    //根据accountId查询用户
//    @Select("select * from user where account_id=#{accountId}")
//    User selByAccountId(String accountId);
//
//    //根据用户id更新用户
//    @Update("update user set token=#{token},name=#{name},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where account_id=#{accountId}")
//    void updUserById(User user);
}
