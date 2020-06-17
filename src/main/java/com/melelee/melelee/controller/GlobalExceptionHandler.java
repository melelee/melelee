package com.melelee.melelee.controller;


import com.melelee.melelee.controller.vo.Result;
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
     * Default error handler Result.
     *
     * @param e the e
     * @return the Result
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Object> defaultErrorHandler(Exception e) {
        log.error("system run error：", e);
        return new Result<>(-1, "系统运行异常");
    }

    /**
     * Handle bind exception Result.
     *
     * @param e the e
     * @return the Result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleBindException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError == null) {
            return new Result<>(9010, "业务参数校验异常");
        }
        log.info("parameter check error:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return new Result<>(9010, "业务参数校验异常：" + fieldError.getField() + " " + fieldError.getDefaultMessage());
    }

    /**
     * Handle missing servlet request parameter exception Result.
     *
     * @param e the e
     * @return the Result
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException：", e);
        return new Result<>(9011, "参数缺失：" + e.getParameterName());
    }

    /**
     * Handle multipart exception Result.
     *
     * @param e the e
     * @return the Result
     */
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleMultipartException(MultipartException e) {
        log.error("file upload multipartException：", e);
        return new Result<>(9020, "文件上传异常");
    }

    /**
     * Handle servlet request part exception Result.
     *
     * @param e the e
     * @return the Result
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleServletRequestPartException(MissingServletRequestPartException e) {
        log.error("file upload missingservletrequestpartexception：", e);
        return new Result<>(9021, "文件未上传");
    }
}

