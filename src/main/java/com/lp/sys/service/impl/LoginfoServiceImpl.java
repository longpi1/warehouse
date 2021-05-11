package com.lp.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lp.sys.domain.Loginfo;
import com.lp.sys.mapper.LoginfoMapper;
import com.lp.sys.service.LoginfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {

}
