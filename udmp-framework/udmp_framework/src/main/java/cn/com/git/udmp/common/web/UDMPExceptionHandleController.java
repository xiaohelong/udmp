

package cn.com.git.udmp.common.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.com.git.udmp.common.exception.ExceptionMessageHelper;
import cn.com.git.udmp.common.mapper.JsonMapper;
import cn.com.git.udmp.core.exception.app.BizException;
import cn.com.git.udmp.core.exception.app.IgnoreBizException;
import cn.com.git.udmp.core.exception.app.RollbackableBizException;

/**
 * UDMP基础异常捕获
 * @description 捕获明确的UDMP可以捕获到的异常，主要针对BaseController的子类抛出的异常
 * @see BaseDefaultExceptionHandleController,BaseController
 * @author Spring Cao
 * @date 2016年8月25日 下午4:21:59
 */
public class UDMPExceptionHandleController extends BaseDefaultExceptionHandleController{
    
    /**
     * 
     * @title 业务异常处理
     * @description
     *
     * @see cn.com.git.udmp.common.web.BaseDefaultExceptionHandleController#bizException(javax.servlet.http.HttpServletResponse, java.lang.Exception)
     * @param response
     * @param ex
     * @return
     */
    @Override
    @ExceptionHandler({ BizException.class, IgnoreBizException.class, RollbackableBizException.class })
    public String bizException(HttpServletResponse response, Exception ex) {
        // TODO 业务异常返现
        // json格式：{type:success,message:xxx:data:{}}
        String error = "";
        if (ex instanceof BizException) {
            error = ExceptionMessageHelper.getExMsg(((BizException) ex).getErrCode());
        } else {
            error = ex.getMessage();
        }
        ParentResultVO vo = new ParentResultVO();
        vo.setMessage(error);
        vo.setErrorType(ParentResultVO.ERROR_TYPE_BUSINESS);
        logger.error(">>>>>>>>>>>>>>>>>>>UDMPExceptionHandleController:已经捕获bizException，并封装返回数据");
        return this.renderString(response, vo);
    }
    
    
    /**
     * 
     * @title 参数绑定异常
     * @description
     *
     * @see cn.com.git.udmp.common.web.BaseDefaultExceptionHandleController#bindException()
     * @return
     */
    @Override
    @ExceptionHandler({ BindException.class, ConstraintViolationException.class, ValidationException.class })
    public String bindException() {
        logger.error(">>>>>>>>>>>>>>>>>>>UDMPExceptionHandleController:已经捕获bindException，并跳转400页");
        return CTL_REDIRECT_ERR_400;
    }
    
    /**
     * 
     * @title 客户端返回JSON字符串
     * @description
     * 
     * @param response
     * @param object
     * @return
     */
    public String renderString(HttpServletResponse response, Object object) {
        return renderString(response, JsonMapper.toJsonString(object), CTL_CONTENT_TYPE_JSON);
    }

    /**
     * 
     * @title 客户端返回字符串
     * @description
     * 
     * @param response
     * @param string
     * @param type
     * @return
     */
    public String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding(CTL_CHAR_CODE_UTF8);
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
