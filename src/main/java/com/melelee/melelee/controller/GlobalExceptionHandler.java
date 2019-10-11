package com.melelee.melelee.controller;


import com.melelee.melelee.controller.bean.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * The type Global exception handler.
 */
@Slf4j
@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {


    /**
     * Default error handler response.
     *
     * @param e the e
     * @return the response
     * @throws Exception the exception
     */
    @ExceptionHandler(value = Exception.class)
    public Response defaultErrorHandler(Exception e) throws Exception {
        Response stringResponse = null;
        log.error("system run error：", e);
        stringResponse = new Response(-1, "系统运行异常");
        return stringResponse;
    }

    /**
     * Handle bind exception response.
     *
     * @param e the e
     * @return the response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBindException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.info("parameter check error:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return new Response(9010, "业务参数校验异常：" + fieldError.getField() + " " + fieldError.getDefaultMessage());
    }

    /**
     * Handle missing servlet request parameter exception response.
     *
     * @param e the e
     * @return the response
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException：", e);
        return new Response(9011,"参数缺失：" + e.getParameterName());
    }

    /**
     * Handle multipart exception response.
     *
     * @param e the e
     * @return the response
     */
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleMultipartException(MultipartException e) {
        log.error("file upload multipartException：", e);
        return new Response(9020, "文件上传异常");
    }

    /**
     * Handle servlet request part exception response.
     *
     * @param e the e
     * @return the response
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleServletRequestPartException(MissingServletRequestPartException e) {
        log.error("file upload missingservletrequestpartexception：", e);
        return new Response(9021, "文件未上传");
    }
}

