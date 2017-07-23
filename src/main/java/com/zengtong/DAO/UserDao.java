package com.zengtong.DAO;

import com.zengtong.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by znt on 17-7-18.
 */
@Mapper
@Component
public interface UserDao {
    String TABLE_NAME = " user ";
    String INSET_FIELDS = " name, password , salt, head_url ";
    String SELECT_FIELDS = " id, name, password, salt, head_url";
    @Insert({"insert into",TABLE_NAME,"(",INSET_FIELDS,") values(#{name},#{password},#{salt}, #{head_url})"})
    int addUser(User user);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where id=#{id}"})
    User selectById(Integer id);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where name=#{name}"})
    User selectByName(String name);

}