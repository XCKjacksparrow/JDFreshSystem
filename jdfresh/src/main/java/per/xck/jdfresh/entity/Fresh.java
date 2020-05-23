package per.xck.jdfresh.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@ApiModel("生鲜类")
@Entity
@Table(name = "fresh")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @Column
    @ApiModelProperty("生鲜种类名称")
    private String freshCategory;

    @Column
    @ApiModelProperty("生鲜名称")
    private String name;

    @Column
    @ApiModelProperty("数量")
    private Integer amount;
}
