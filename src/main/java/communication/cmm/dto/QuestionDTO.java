package communication.cmm.dto;

import communication.cmm.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private String id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String  creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
