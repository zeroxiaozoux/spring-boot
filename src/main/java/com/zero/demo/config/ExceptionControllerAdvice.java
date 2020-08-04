package com.zero.demo.config;

import com.zero.demo.exception.APIException;
import com.zero.demo.util.ResultCode;
import com.zero.demo.util.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author admin
 * @date 2020/8/4-14:04
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    //UserController.addUser
//    @ExceptionHandler(MethodArgumentNotValidException .class)
//    public String MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        if(log.isInfoEnabled()) log.info("****** ExceptionControllerAdvice ******");
//        // 从异常对象中拿到ObjectError对象
//        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
//        // 然后提取错误提示信息进行返回
//        return objectError.getDefaultMessage();
//    }

    // 添加自定义异常
//    @ExceptionHandler(APIException.class)
//    public String APIExceptionHandler(APIException e) {
//        return e.getMsg();
//    }

// -------------- 第一版 ---------------
//    @ExceptionHandler(APIException.class)
//    public ResultVO<String> APIExceptionHandler(APIException e) {
//        // 注意哦，这里返回类型是自定义响应体
//        return new ResultVO<>(e.getCode(), "响应失败", e.getMsg());
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
//        // 注意哦，这里返回类型是自定义响应体
//        return new ResultVO<>(1001, "参数校验失败", objectError.getDefaultMessage());
//    }

// ------------  第二版   枚举 -------------
    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        if(log.isInfoEnabled()) log.info("****** ExceptionControllerAdvice.APIExceptionHandler ******");
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        if(log.isInfoEnabled()) log.info("****** ExceptionControllerAdvice.MethodArgumentNotValidExceptionHandler ******");
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }


}
