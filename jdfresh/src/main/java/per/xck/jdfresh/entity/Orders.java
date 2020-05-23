package per.xck.jdfresh.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ApiModel("订单类")
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @Column
    @ApiModelProperty("订单内容")
    private String content;

    @Column
    @ApiModelProperty("下单人")
    private String username;

    @Column
    @ApiModelProperty("派送员")
    private String deliverier;

    @Column
    @ApiModelProperty("送货地址")
    private String address;

    @Column
    @ApiModelProperty("下单时间")
    private Long orderTime;

    @Column
    @ApiModelProperty("收货时间")
    private Long receiptTime;

    @Column
    @ApiModelProperty("状态")
    private String status;

    @Column
    @ApiModelProperty("订单价格")
    private Double price;

    @Column
    @ApiModelProperty("是否付款 0--未付款 1--已付款")
    private Integer isPaid;
}
