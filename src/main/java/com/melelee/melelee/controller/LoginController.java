package com.melelee.melelee.controller;

import com.melelee.melelee.controller.bean.Response;
import com.melelee.melelee.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * The type Login controller.
 *
 * @author melelee
 */
@Slf4j
@RestController
public class LoginController {
    /**
     * Index string.
     *
     * @return the string
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * Success string.
     *
     * @return the string
     */
    @GetMapping(value = "/success")
    public String success() {
        return "success";
    }

    /**
     * Unauthorized string.
     *
     * @return the string
     */
    @GetMapping(value = "/403")
    public String unauthorized() {
        return "403";
    }

    /**
     * Login string.
     *
     * @return the string
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "please do login!";
    }

    /**
     * Do login response.
     *
     * @param username the username
     * @param password the password
     * @param captcha  the captcha
     * @return the response
     */
    @PostMapping(value = "/dologin")
    public Response doLogin(@RequestParam String username, @RequestParam String password,
                            @RequestParam(required = false) String captcha) {
        Response<User> userResponse;

        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.KEY_CAPTCHA);
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            return Response.failure("登录失败");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();


        try {
            currentUser.login(token);
            userResponse = Response.successMessage("登录成功");
        } catch (UnknownAccountException uae) {
            userResponse = Response.failure("用户不存在");

        } catch (IncorrectCredentialsException ice) {
            userResponse = Response.failure("密码错误");

        } catch (LockedAccountException lae) {
            userResponse = Response.failure("账户已锁定");

        } catch (ExcessiveAttemptsException eae) {
            userResponse = Response.failure("密码错误次数过多");

        } catch (AuthenticationException ae) {
            log.error("error:", ae);
            userResponse = Response.failure("登录失败");
        }

        return userResponse;
    }
}
