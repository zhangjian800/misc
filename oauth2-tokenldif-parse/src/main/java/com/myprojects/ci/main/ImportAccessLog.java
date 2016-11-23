package com.myprojects.ci.main;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myprojects.ci.ldifparse.Parser;

public class ImportAccessLog {
    private final static String [] SPRING_FILE_ARRAY = {"classpath:applicationContext.xml"};
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String fileName = null;
        if(args != null && args.length >= 1) {
             fileName = args[0];
        }
        if(StringUtils.isEmpty(fileName)) {
            System.out.println("========The Access log file is  not provided");
            System.exit(-1);
        }
        System.out.println("========The Access log file is  "+ fileName);
        try {
            ApplicationContext ac = new ClassPathXmlApplicationContext(SPRING_FILE_ARRAY);;
            Parser parser = (Parser)ac.getBean("accessLogParser");
            parser.parse(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("========The total costs:  "+ (end - start));


    }
}
