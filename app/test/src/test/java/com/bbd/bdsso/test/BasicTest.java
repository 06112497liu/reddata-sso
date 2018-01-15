/**  
 * bbdservice.com Inc
 * All Rights Reserved 2016
 */
package com.bbd.bdsso.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * 测试基类
 * 
 * @author byron
 * @version $Id: BasicTest.java, v 0.1 May 3, 2016 2:31:14 PM byron Exp $
 */
@Test
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class BasicTest extends AbstractTestNGSpringContextTests {

}
