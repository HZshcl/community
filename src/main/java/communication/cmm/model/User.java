package communication.cmm.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String accountId;
    private String token;
    private Long qmtCreate;
    private Long qmtModified;
}
