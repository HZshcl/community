package communication.cmm.mapper;

import communication.cmm.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmtCreate,gmtModified,creator,tag) " +
            "values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from question")
    @Results(
            {
                    @Result(id=true,column ="id",property = "id"),
                    @Result(column = "title", property = "title"),
                    @Result(column ="description",property = "description"),
                    @Result(column ="gmtCreate",property = "gmtCreate"),
                    @Result(column ="gmtModified",property = "gmtModified"),
                    @Result(column ="commentCount",property = "commentCount"),
                    @Result(column ="viewCount",property = "viewCount"),
                    @Result(column ="likeCount",property = "likeCount"),
                    @Result(column ="tag",property = "tag"),
                    @Result(column = "creator",property = "creator"),
                    @Result(column = "creator",property = "user",one = @One(select = "communication.cmm.mapper.UserMapper.findById"))

            }
    )
    public List<Question> findAll();
}
