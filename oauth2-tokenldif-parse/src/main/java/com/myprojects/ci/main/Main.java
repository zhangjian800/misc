package com.myprojects.ci.main;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myprojects.ci.ldifparse.Parser;

public class Main {
    private final static String [] SPRING_FILE_ARRAY = {"classpath:applicationContext.xml"};
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String fileName = null;
        String action = null;
        if(args != null && args.length >= 2) {
             action = args[0];
             fileName = args[1];

        }
        if(StringUtils.isEmpty(fileName)) {
            System.out.println("========The file or action is  not provided");
            System.exit(-1);
        }
        System.out.println("========The file is  "+ fileName);
        try {
            ApplicationContext ac = new ClassPathXmlApplicationContext(SPRING_FILE_ARRAY);;
            Parser parser =  null;
            if("token".equals(action)) {
                parser = (Parser)ac.getBean("oauth2TokenParser");
            } else if ("AccessLog".equals(action)) {
                 parser = (Parser)ac.getBean("accessLogParser");
            } else {
                System.out.println("========Invalid Action");
                System.exit(-1);
            }

            parser.parse(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("========The total costs:  "+ (end - start));


    }
}
