package com.zzn.servicebase;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program : edu_parent
 * @ClassName : GuliException
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-28 16:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private  Integer code;

    private String msg;
}
