package com.frame.spring.annotation.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @Author: railgun
 * 2021/6/8 12:05
 * PS:
 **/
@Scope(value = "singleton")
@Controller
public class UserController {
}
