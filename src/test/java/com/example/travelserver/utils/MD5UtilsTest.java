package com.example.travelserver.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilsTest {

    @Test
    public void getPwd() {
        System.out.println(MD5Utils.getPwd("123"));
    }
}