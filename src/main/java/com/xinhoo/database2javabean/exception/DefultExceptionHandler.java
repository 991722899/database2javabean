package com.xinhoo.database2javabean.exception;

import com.xinhoo.database2javabean.model.ResultModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: DefultExceptionHandler
 * @Description:
 * @author: chaochao.chen
 * @date: 2019/5/20 17:09
 */
@ControllerAdvice
public class DefultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultModel excepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return ResultModel.error(e.getMessage());
    }
}
