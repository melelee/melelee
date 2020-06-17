package com.melelee.melelee.demo;

import com.melelee.melelee.entity.Role;

import java.util.Optional;

/**
 * @author by melelee
 * @date 2020/4/23 16:37
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Role role = new Role();
        Optional<Role> optionalO = Optional.ofNullable(role);
        optionalO.ifPresent(System.out::println);
        System.out.println(optionalO.isPresent());
        Role role1 = optionalO.orElse(new Role());
        System.out.println(role==role1);
        optionalO.orElseGet(() -> role1);
        String s = optionalO.map(role2 -> role2.getDescription()).orElseGet(() -> "defa");
        System.out.println(s);
    }
}
