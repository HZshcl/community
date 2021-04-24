package communication.cmm.mapper;

import communication.cmm.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Insert("insert into user(name,accountId,token,qmtCreate,qmtModified,avatarUrl) " +
            "values(#{name},#{accountId},#{token},#{qmtCreate},#{qmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);
    @Select("select * from user where id=#{id}")
    User findById(@Param(value = "id") String id);
}
