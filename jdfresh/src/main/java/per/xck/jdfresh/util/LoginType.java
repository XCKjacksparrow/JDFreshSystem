package per.xck.jdfresh.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginType {
    private String status;      // 状态 success?fail
    private String errorMsg;    // 错误信息 用户名不存在?密码错误
}
