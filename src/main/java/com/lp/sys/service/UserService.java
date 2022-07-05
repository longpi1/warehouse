package com.lp.sys.service;

import com.lp.sys.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author lp
 */
public interface UserService extends IService<User> {

    /**
     * 保存用户和角色之间的关系
     *
     * @param uid
     * @param ids
     */
    void saveUserRole(Integer uid, Integer[] ids);

}
