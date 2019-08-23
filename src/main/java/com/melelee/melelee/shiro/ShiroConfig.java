package com.melelee.melelee.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Shiro config.
 */
@Configuration
public class ShiroConfig {
    /**
     * My realm my realm.
     *
     * @return the my realm
     */
    @Bean
    LoginRealm myRealm() {
        LoginRealm loginRealm = new LoginRealm();
        loginRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return loginRealm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     *
     * @return hashed credentials matcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);

        return hashedCredentialsMatcher;
    }

    /**
     * Security manager default web security manager.
     *
     * @return the default web security manager
     */
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    /**
     * Shiro filter chain definition shiro filter chain definition.
     *
     * @return the shiro filter chain definition
     */
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        defaultShiroFilterChainDefinition.addPathDefinition("/static/**", "anon");
        defaultShiroFilterChainDefinition.addPathDefinition("/", "anon");
        defaultShiroFilterChainDefinition.addPathDefinition("/index", "anon");
        defaultShiroFilterChainDefinition.addPathDefinition("/login", "anon");
        defaultShiroFilterChainDefinition.addPathDefinition("/dologin", "anon");

//        defaultShiroFilterChainDefinition.addPathDefinition("/**", "authc");
        //FIXME 不是这么玩的
        defaultShiroFilterChainDefinition.addPathDefinition("/**", "anon");

        return defaultShiroFilterChainDefinition;
    }
}