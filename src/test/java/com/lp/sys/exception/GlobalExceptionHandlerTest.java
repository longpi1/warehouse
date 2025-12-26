package com.lp.sys.exception;

import com.lp.sys.common.ResultObj;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * 全局异常处理器单元测试
 *
 * @author lp
 */
@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    /**
     * 测试: 处理业务异常 - 默认错误码
     */
    @Test
    public void testHandleBusinessException_DefaultCode() {
        // Given: 业务异常
        BusinessException exception = new BusinessException("商品库存不足");

        // When: 处理异常
        ResultObj result = exceptionHandler.handleBusinessException(exception);

        // Then: 返回错误信息
        assertNotNull(result);
        assertEquals(Integer.valueOf(-1), result.getCode());
        assertEquals("商品库存不足", result.getMsg());
    }

    /**
     * 测试: 处理业务异常 - 自定义错误码
     */
    @Test
    public void testHandleBusinessException_CustomCode() {
        // Given: 带自定义错误码的业务异常
        BusinessException exception = new BusinessException(1001, "自定义业务错误");

        // When: 处理异常
        ResultObj result = exceptionHandler.handleBusinessException(exception);

        // Then: 返回自定义错误码
        assertNotNull(result);
        assertEquals(Integer.valueOf(1001), result.getCode());
        assertEquals("自定义业务错误", result.getMsg());
    }

    /**
     * 测试: 处理参数校验异常
     */
    @Test
    public void testHandleValidationException() {
        // Given: 参数校验异常
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("inportVo", "number", "进货数量必须大于0");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldError()).thenReturn(fieldError);

        // When: 处理异常
        ResultObj result = exceptionHandler.handleValidationException(exception);

        // Then: 返回校验错误信息
        assertNotNull(result);
        assertEquals(Integer.valueOf(-1), result.getCode());
        assertEquals("进货数量必须大于0", result.getMsg());
    }

    /**
     * 测试: 处理参数校验异常 - 无具体错误
     */
    @Test
    public void testHandleValidationException_NoFieldError() {
        // Given: 参数校验异常但无具体字段错误
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldError()).thenReturn(null);

        // When: 处理异常
        ResultObj result = exceptionHandler.handleValidationException(exception);

        // Then: 返回默认错误信息
        assertNotNull(result);
        assertEquals(Integer.valueOf(-1), result.getCode());
        assertEquals("参数校验失败", result.getMsg());
    }

    /**
     * 测试: 处理系统异常
     */
    @Test
    public void testHandleException() {
        // Given: 系统异常
        Exception exception = new NullPointerException("空指针异常");

        // When: 处理异常
        ResultObj result = exceptionHandler.handleException(exception);

        // Then: 返回通用错误信息
        assertNotNull(result);
        assertEquals(Integer.valueOf(-1), result.getCode());
        assertEquals("系统异常，请联系管理员", result.getMsg());
    }

    /**
     * 测试: 处理数据库异常
     */
    @Test
    public void testHandleException_DatabaseException() {
        // Given: 数据库异常
        Exception exception = new RuntimeException("数据库连接失败");

        // When: 处理异常
        ResultObj result = exceptionHandler.handleException(exception);

        // Then: 返回通用错误信息
        assertNotNull(result);
        assertEquals(Integer.valueOf(-1), result.getCode());
        assertEquals("系统异常，请联系管理员", result.getMsg());
    }

    /**
     * 测试: 业务异常 - 空消息
     */
    @Test
    public void testHandleBusinessException_NullMessage() {
        // Given: 异常消息为null
        BusinessException exception = new BusinessException(null);

        // When: 处理异常
        ResultObj result = exceptionHandler.handleBusinessException(exception);

        // Then: 返回null消息
        assertNotNull(result);
        assertEquals(Integer.valueOf(-1), result.getCode());
        assertNull(result.getMsg());
    }

    /**
     * 测试: 业务异常 - 空字符串消息
     */
    @Test
    public void testHandleBusinessException_EmptyMessage() {
        // Given: 异常消息为空字符串
        BusinessException exception = new BusinessException("");

        // When: 处理异常
        ResultObj result = exceptionHandler.handleBusinessException(exception);

        // Then: 返回空字符串
        assertNotNull(result);
        assertEquals(Integer.valueOf(-1), result.getCode());
        assertEquals("", result.getMsg());
    }
}
