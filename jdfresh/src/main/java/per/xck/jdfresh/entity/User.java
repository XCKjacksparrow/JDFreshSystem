package per.xck.jdfresh.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@ApiModel("用户实体类")
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @Column
    @ApiModelProperty("用户名")
    private String username;

    @Column
    @ApiModelProperty("密码")
    private String password;

    @Column
    @ApiModelProperty("地址")
    private String address;

    @Column
    @ApiModelProperty("是否配送员  0--不是 1--是 default为0")
    private Integer isdeliverier;
}
