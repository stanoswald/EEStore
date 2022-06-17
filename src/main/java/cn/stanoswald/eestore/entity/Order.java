package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单表
 *
 * @author StanOswald
 * @since 2022-06-15
 */
@Data
@NoArgsConstructor
@TableName("tbl_order")
public class Order {

    /**
     * 订单id
     */
    @TableId
    private String orderId;

    /**
     * 用户Id
     */
    private String uid;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createTime;

    /**
     * 发货时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd HH:mm:ss.SSS")
    private LocalDateTime shipTime;

    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd HH:mm:ss.SSS")
    private LocalDateTime finishedTime;


    /**
     * 订单商品列表
     */
    @TableField(exist = false)
    private List<OrderItem> itemList;

}
