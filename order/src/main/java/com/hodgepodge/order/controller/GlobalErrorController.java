package com.hodgepodge.order.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * Title: GlobalErrorController
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月14日
 * @since 1.8
 */
@Controller
public class GlobalErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }
}
