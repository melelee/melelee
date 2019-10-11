package com.melelee.melelee.controller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Response.
 *
 * @param <T> the type parameter
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    /**
     * The constant SUCCESS.
     */
    public static final int SUCCESS = 0;
    /**
     * The constant ERROR.
     */
    public static final int ERROR = -1;

    /**
     * Instantiates a new Response.
     *
     * @param code    the code
     * @param message the message
     */
    public Response(int code, String message) {
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
     * Success response.
     *
     * @param <T> the type parameter
     * @return the response
     */
    public static <T> Response<T> success() {
        return success(null);
    }

    /**
     * Success response.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the response
     */
    public static <T> Response<T> success(T data) {
        return success("操作成功", data);
    }

    /**
     * Success message response.
     *
     * @param <T>     the type parameter
     * @param message the message
     * @return the response
     */
    public static <T> Response<T> successMessage(String message) {
        return success(message, null);
    }

    /**
     * Success response.
     *
     * @param <T>     the type parameter
     * @param message the message
     * @param data    the data
     * @return the response
     */
    public static <T> Response<T> success(String message, T data) {
        return new Response<>(Response.SUCCESS, message, data);
    }

    /**
     * Failure response.
     *
     * @param <T>     the type parameter
     * @param message the message
     * @return the response
     */
    public static <T> Response<T> failure(String message) {
        return failure(Response.ERROR, message);
    }

    /**
     * Failure response.
     *
     * @param <T>     the type parameter
     * @param code    the code
     * @param message the message
     * @return the response
     */
    public static <T> Response<T> failure(int code, String message) {
        return new Response<>(code, message, null);
    }
}
