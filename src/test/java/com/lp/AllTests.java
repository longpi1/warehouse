package com.lp;

import com.lp.sys.exception.GlobalExceptionHandlerTest;
import com.lp.sys.service.RoleServiceTest;
import com.lp.sys.service.UserServiceTest;
import com.lp.warehouse.service.InportServiceTest;
import com.lp.warehouse.service.OutportServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试套件 - 运行所有单元测试
 * 
 * 使用方法:
 * 1. IDE中右键运行此类
 * 2. 或使用Maven命令: mvn test
 *
 * @author lp
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        // 仓储业务测试
        InportServiceTest.class,
        OutportServiceTest.class,
        
        // 系统管理测试
        UserServiceTest.class,
        RoleServiceTest.class,
        
        // 异常处理测试
        GlobalExceptionHandlerTest.class
})
public class AllTests {
    // 测试套件类无需代码,仅作为测试入口
}
