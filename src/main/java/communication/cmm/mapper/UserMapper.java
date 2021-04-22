package communication.cmm.mapper;

import communication.cmm.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    @Insert("insert into user(name,accountId,token,qmtCreate,qmtModified) " +
            "values(#{name},#{accountId},#{token},#{qmtCreate},#{qmtModified})")
    void insert(User user);
}