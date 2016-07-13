/*
 *   (C) Copyright 2009-2010 hSenid Software International (Pvt) Limited.
 *   All Rights Reserved.
 *
 *   These materials are unpublished, proprietary, confidential source code of
 *   hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *   of hSenid Software International (Pvt) Limited.
 *
 *   hSenid Software International (Pvt) Limited retains all title to and intellectual
 *   property rights in these materials.
 */

package hsl.devspace.app.selfcare.service;

import hsl.devspace.app.selfcare.login.LoginForm;


public class MockAuthenticationServiceImpl implements AuthenticationService {
//    private static final Logger logger = LoggerFactory.getLogger(MockAuthenticationServiceImpl.class);

    @Override
    public boolean authenticate(LoginForm loginForm) {
        if (loginForm.getUsername().equals("user") && loginForm.getPassword().equals("password")) {
//            logger.debug("Successfully authenticated the user into system");
            return true;
        } else {
//            logger.debug("Failed to authenticate the user into system");
            return false;
        }
    }
}
