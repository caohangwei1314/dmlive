package cn.caohangwei.dmlive.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;



public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public BaseResult exceptionHandler(Exception exception){
        LOGGER.error("全局统一异常处理:",exception);
        return new BaseResult(0,"failed",exception);
    }

}
