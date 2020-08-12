package com.zero.demo.dto;

import com.zero.demo.util.BaseParam;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 * @date 2020/8/3-18:34
 */
@Data
public class User  extends BaseParam {
    @NotNull(message = "id 不能为空")
    private  long id;
    @NotNull(message = "name 不能为空")
    private  String name;
    @NotNull(message = "sex 不能为空")
    private String sex;
    @NotNull(message = "age 不能为空")
    private  Integer age;
    @NotNull(message = "email 不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;


}
