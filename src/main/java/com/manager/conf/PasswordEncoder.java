package com.manager.conf;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 加密器
 *
 * @author lsy <liushuoyang03@kuaishou.com>
 * Created on 2021-02-16
 */
@Service
public class PasswordEncoder extends BCryptPasswordEncoder {

}
