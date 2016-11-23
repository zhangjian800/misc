package com.myprojects.ci.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


public class MainTest {

    public static void main(String[] args) {
        
        System.out.println("123456789 org, zhangjian800@cisco.com " + isConsumerTestUser("123456789", "zhangjian800@cisco.com"));
        
        System.out.println("123456789 org, collabctg+123@gmail.com " + isConsumerTestUser("123456789", "collabctg+123@gmail.com"));
        System.out.println("123456789 org, citest-acerew@gmail.com " + isConsumerTestUser("123456789", "citest-acerew@gmail.com"));
        System.out.println("123456789 org, collabctg+123@abcd.com " + isConsumerTestUser("123456789", "collabctg+123@abcd.com"));
        System.out.println("123456789 org, citest-acerew@abcd.com " + isConsumerTestUser("123456789", "citest-acerew@abcd.com"));
        
        
        System.out.println("consumer org, zhangjian800@cisco.com " + isConsumerTestUser("consumer", "zhangjian800@cisco.com"));
        
        System.out.println("consumer org, collabctg+123@gmail.com " + isConsumerTestUser("consumer", "collabctg+123@gmail.com"));
        System.out.println("consumer org, citest-acerew@gmail.com " + isConsumerTestUser("consumer", "citest-acerew@gmail.com"));
        System.out.println("consumer org, collabctg+123@abcd.com " + isConsumerTestUser("consumer", "collabctg+123@abcd.com"));
        System.out.println("consumer org, citest-acerew@abcd.com " + isConsumerTestUser("consumer", "citest-acerew@abcd.com"));
        
        
        
        System.out.println("Consumer org, zhangjian800@cisco.com " + isConsumerTestUser("Consumer", "zhangjian800@cisco.com"));
        
        System.out.println("Consumer org, COLLabctg+123@gmail.com " + isConsumerTestUser("Consumer", "COLLabctg+123@gmail.com"));
        System.out.println("Consumer org, CITEST-acerew@gmail.com " + isConsumerTestUser("Consumer", "CITEST-acerew@gmail.com"));
        System.out.println("Consumer org, COLLabctg+123@abcd.com " + isConsumerTestUser("Consumer", "COLLabctg+123@abcd.com"));
        System.out.println("Consumer org, CITEST-acerew@abcd.com " + isConsumerTestUser("Consumer", "CITEST-acerew@abcd.com"));

        System.out.println("Consumer org, zhangjian800@cisco.com " + isConsumerTestUser("Consumer", "zhangjian800@cisco.com"));
        
        System.out.println("Consumer org, CITEST+123@gmail.com " + isConsumerTestUser("Consumer", "CITEST+123@gmail.com"));
        System.out.println("Consumer org, CITEST-acerew@gmail.com " + isConsumerTestUser("Consumer", "CITEST-acerew@gmail.com"));
        System.out.println("Consumer org, CITEST+123@abcd.com " + isConsumerTestUser("Consumer", "CITEST+123@abcd.com"));
        System.out.println("Consumer org, CITEST-acerew@abcd.com " + isConsumerTestUser("Consumer", "CITEST-acerew@abcd.com"));
        
        
        
        System.out.println("consumer org, prefixcollabctg+123@gmail.com " + isConsumerTestUser("consumer", "prefixcollabctg+123@gmail.com"));
        System.out.println("consumer org, prefixcitest-acerew@gmail.com " + isConsumerTestUser("consumer", "prefixcitest-acerew@gmail.com"));
        System.out.println("consumer org, prefixcollabctg+123@abcd.com " + isConsumerTestUser("consumer", "prefixcollabctg+123@abcd.com"));
        System.out.println("consumer org, prefixcitest-acerew@abcd.com " + isConsumerTestUser("consumer", "prefixcitest-acerew@abcd.com"));
        
        
        System.out.println("consumer org, collabctg+123@gmail.com1 " + isConsumerTestUser("consumer", "collabctg+123@gmail.com1"));
        System.out.println("consumer org, citest-acerew@gmail.com1 " + isConsumerTestUser("consumer", "citest-acerew@gmail.com1"));
        System.out.println("consumer org, collabctg+123@abcd.com1 " + isConsumerTestUser("consumer", "collabctg+123@abcd.com1"));
        System.out.println("consumer org, citest-acerew@abcd.com1 " + isConsumerTestUser("consumer", "citest-acerew@abcd.com1"));
        
        

    }
    
    static String CONSUMER_ORGID = "consumer";

    private static final Pattern[] patterns = new Pattern[] {
            Pattern.compile("collabctg+.*@gmail.com", Pattern.CASE_INSENSITIVE),
            Pattern.compile("citest-.*@gmail.com", Pattern.CASE_INSENSITIVE) };
    private static final Set<Pattern> patternSet = new HashSet<Pattern>(Arrays.asList(patterns));

    public static boolean isConsumerTestUser(String realm, String uid) {
        if (CONSUMER_ORGID.equalsIgnoreCase(realm)) {
            for (Pattern pattern : patternSet) {
                if (pattern.matcher(uid).matches()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

}
