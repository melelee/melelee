package com.melelee.melelee.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Result.
 *
 * @author melelee
 * @param <T> the type parameter
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * The constant SUCCESS.
     */
    public static final int SUCCESS = 0;
    /**
     * The constant ERROR.
     */
    public static final int ERROR = -1;

    /**
     * Instantiates a new Result.
     *
     * @param code    the code
     * @param message the message
     */
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * The Code.
     */
    int code;
    /**
     * The Message.
     */
    String message;
    /**
     * The Data.
     */
    T data;


    /**
     * Success Result.
     *
     * @param <T> the type parameter
     * @return the Result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * Success Result.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the Result
     */
    public static <T> Result<T> success(T data) {
        return success("操作成功", data);
    }

    /**
     * Success message Result.
     *
     * @param <T>     the type parameter
     * @param message the message
     * @return the Result
     */
    public static <T> Result<T> successMessage(String message) {
        return success(message, null);
    }

    /**
     * Success Result.
     *
     * @param <T>     the type parameter
     * @param message the message
     * @param data    the data
     * @return the Result
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(Result.SUCCESS, message, data);
    }

    /**
     * Failure Result.
     *
     * @param <T>     the type parameter
     * @param message the message
     * @return the Result
     */
    public static <T> Result<T> failure(String message) {
        return failure(Result.ERROR, message);
    }

    /**
     * Failure Result.
     *
     * @param <T>     the type parameter
     * @param code    the code
     * @param message the message
     * @return the Result
     */
    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }
}
