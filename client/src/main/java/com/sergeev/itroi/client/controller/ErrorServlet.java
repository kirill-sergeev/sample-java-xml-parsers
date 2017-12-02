package com.sergeev.itroi.client.controller;

import com.sergeev.itroi.client.config.WebConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(WebConstants.ERROR_MAPPING)
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        log.info("Status code: {} | Exception: {} | Servlet: {} | URI: {}",
            unknownIfEmpty(statusCode),
            unknownIfEmpty(throwable),
            unknownIfEmpty(servletName),
            unknownIfEmpty(requestUri));

        request.getRequestDispatcher(WebConstants.ERROR_VIEW).forward(request, response);
    }

    private String unknownIfEmpty(Object value) {
        return Objects.toString(value, "unknown");
    }
}
