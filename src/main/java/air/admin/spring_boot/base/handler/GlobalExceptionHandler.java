package air.admin.spring_boot.base.handler;

import air.admin.spring_boot.config.vo.Result;
import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import static air.admin.spring_boot.util.enums.StatusCodeEnum.*;


/**
 * 全局异常处理
 *
 * @author ayo
 */
        
        
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理SpringBoot Validation 参数验证抛出的异常
     * */
    //处理MethodArgumentNotValidException，该异常会在请求参数验证失败时抛出
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidationException(MethodArgumentNotValidException e) {
        return Result.fail(HttpStatus.BAD_REQUEST.value(),e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
    //处理ConstraintViolationException，该异常会在方法级别的参数验证失败时抛出
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> handleConstraintViolationException(ConstraintViolationException e) {
        return Result.fail(HttpStatus.BAD_REQUEST.value(),e.getConstraintViolations().iterator().next().getMessage());
    }

    /**
     * 处理Assert异常
     */
    @ExceptionHandler(AssertionError.class)
    public Result<?> handleAssertionError(AssertionError e) {
        return Result.fail(e.getMessage());
    }

    /**
     * 处理权限不足
     */
    @ExceptionHandler(value = NotPermissionException.class)
    public Result<?> handleNotPermissionException() {
        return Result.fail("权限不足");
    }

    /**
     * 处理账号封禁
     */
    @ExceptionHandler(value = DisableServiceException.class)
    public Result<?> handleDisableServiceExceptionException() {
        return Result.fail("此账号已被禁止访问服务");
    }

    /**
     * 处理无此角色异常
     */
    @ExceptionHandler(value = NotRoleException.class)
    public Result<?> handleNotRoleException() {
        return Result.fail("权限不足");
    }

    /**
     * 处理SaToken异常
     */
    @ExceptionHandler(value = NotLoginException.class)
    public Result<?> handlerNotLoginException(NotLoginException nle) {
        // 判断场景值，定制化异常信息
        String message;
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else {
            message = "当前会话未登录";
        }
        // 返回给前端
        return Result.fail(UNAUTHORIZED.getCode(), message);
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> handleSystemException() {
        return Result.fail(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getMsg());
    }

}
