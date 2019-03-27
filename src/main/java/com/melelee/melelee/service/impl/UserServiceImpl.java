package com.melelee.melelee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melelee.melelee.entity.User;
import com.melelee.melelee.mapper.UserMapper;
import com.melelee.melelee.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author melelee
 * @since 2019-03-27
 */
@Service
@Transactional(propagation = Propagation.NESTED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
