package per.xck.jdfresh.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@ApiModel("生鲜种类类")
@Entity
@Table(name = "freshcategory")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FreshCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @Column
    @ApiModelProperty("种类名称")
    private String name;
}
