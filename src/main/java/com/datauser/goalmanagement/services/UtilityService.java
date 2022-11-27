package com.datauser.goalmanagement.services;

import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UtilityService {


    public String getUserData(final ServletRequest servletRequest) {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request == null) {
            return null;
        }
        final HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        var userPrincipal = request.getUserPrincipal();
        if (userPrincipal == null) {
            return null;
        }
        return userPrincipal.getName();
    }
}
