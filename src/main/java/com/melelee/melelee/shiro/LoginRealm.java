package com.melelee.melelee.shiro;


import com.melelee.melelee.entity.User;
import com.melelee.melelee.service.UserService;
import com.melelee.melelee.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type My shiro realm.
 */
@Slf4j
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findByUsername(username);
        if (null == user) {
            return null;
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), ByteSource.Util.bytes(username), getName());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println(PasswordUtils.encryptPassword("admin","sdf"));
    }
}