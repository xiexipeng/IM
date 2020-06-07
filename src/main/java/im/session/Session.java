package im.session;

import lombok.Data;

/**
 * @author: xiexipeng@u51.com
 * @create: 2020/06/06 14:15:08
 * @description:
 * @Version
 **/
@Data
public class Session {

    private String userId;

    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
