package com.myprojects.ci.main;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldOAuth2User implements java.io.Serializable {

    String ACCOUNT_TYPE_MACHINE = "machine";
    String ACCOUNT_TYPE_USER = "user";
    String ACCOUNT_TYPE_SA = "service";
    String ACCOUNT_TYPE_ORG_PROV = "adminUser";
    String ACCOUNT_TYPE_ANONYMOUS = "anonymous";
    
    private String accountType;

    private String realm;
    private String uid;
    private String cisUUID;
    private Set<String> cisServices;
    private String mail;

    private String status;
    private String pwdAccountDisabled;
    private Set<String> isMemberOfSet;

    private String pwdChangeTime;

    private String password;
    private long ExpiredDays;
    private String serviceAccessTime;
    private String userModifyTimeStamp;

    public OldOAuth2User() {

    }

    public OldOAuth2User(String uid, String password) {
        this.uid = uid;
        this.password = password;
    }

    public long getExpiredDays() {
        return ExpiredDays;
    }

    public void setExpiredDays(long expiredDays) {
        ExpiredDays = expiredDays;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwdAccountDisabled() {
        return pwdAccountDisabled;
    }

    public void setPwdAccountDisabled(String pwdAccountDisabled) {
        this.pwdAccountDisabled = pwdAccountDisabled;
    }

    public String getPwdChangeTime() {
        return pwdChangeTime;
    }

    public void setPwdChangeTime(String pwdChangeTime) {
        this.pwdChangeTime = pwdChangeTime;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCisUUID() {
        return cisUUID;
    }

    public void setCisUUID(String cisUUID) {
        this.cisUUID = cisUUID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<String> getIsMemberOfSet() {
        return isMemberOfSet;
    }

    public void setIsMemberOfSet(Set<String> isMemberOfSet) {
        this.isMemberOfSet = isMemberOfSet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<String> getCisServices() {
        return cisServices;
    }

    public void setCisServices(Set<String> cisServices) {
        this.cisServices = cisServices;
    }

    public String getServiceAccessTime() {
        return serviceAccessTime;
    }

    public void setServiceAccessTime(String serviceAccessTime) {
        this.serviceAccessTime = serviceAccessTime;
    }
    public String getUserModifyTimeStamp() {
        return userModifyTimeStamp;
    }

    public void setUserModifyTimeStamp(String userModifyTimeStamp) {
        this.userModifyTimeStamp = userModifyTimeStamp;
    }


    private static final String REG_EXP_SERVICE_ENTITLEMENT = "(?i)entitlement group for ([^,]*)";
    private static final Pattern REG_EXP_PATTERN_SERVICE_ENTITLEMENT = Pattern
            .compile(REG_EXP_SERVICE_ENTITLEMENT);

    private static String REG_EXP_SYSTEM_ROLE = "(cn=)([^,]*)";
    private static Pattern REG_EXP_PATTERN_SYSTEM_ROLE = Pattern
            .compile(REG_EXP_SYSTEM_ROLE);

    private static final String REG_EXP_SERVICE_ROLE = "(?i)role group for ([^,]*)";
    private static final Pattern REG_EXP_PATTERN_SERVICE_ROLE = Pattern
            .compile(REG_EXP_SERVICE_ROLE);

    private static final String PART_PATH_FULLADMIN_PREFIX = "cn=Full Admin Group,ou=System";
    private static final String PART_PATH_USERADMIN_PREFIX = "cn=User Admin Group,ou=System,";
    private static final String PART_PATH_ALLUSER_PREFIX = "cn=all users group,";
    private static final String PART_PATH_READONLYADMIN_PREFIX = "cn=Readonly Admin Group,ou=System";

    private static final String ID_FULL_ADMIN =   "id_full_admin";
    private static final String ID_USER_ADMIN =   "id_user_admin";
    private static final String ID_READONLY_ADMIN = "id_readonly_admin";

    private static final String FULL_ADMIN =   "Full_Admin";
    private static final String USER_ADMIN =   "User_Admin";
    private static final String READONLY_ADMIN =  "Readonly_Admin";
    private static final String USER =   "User";

    private static final String STRING_ENTITLEMENT_GROUP = "entitlement group for";

    /**
     * Extract service entitlement.
     *
     * @param group
     *            the group
     * @return the string
     */
    private static String extractServiceEntitlement(final String isMemberOf) {
        Matcher matcher = REG_EXP_PATTERN_SERVICE_ENTITLEMENT.matcher(isMemberOf);
        if (matcher.find()) {
            return matcher.group(matcher.groupCount()).toLowerCase();
        } else {
            return null;
        }
    }

    /**
     * Extract user role
     *
     * @param group
     *            the group
     * @return the string
     */
    private static String extractUserRole(final String isMemberOf) {
        String userRole = null;
        if(isMemberOf.toLowerCase().startsWith(PART_PATH_FULLADMIN_PREFIX.toLowerCase())) {
            userRole = ID_FULL_ADMIN;
        } else if(isMemberOf.toLowerCase().startsWith(PART_PATH_USERADMIN_PREFIX.toLowerCase())) {
            userRole = ID_USER_ADMIN;
        } else if(isMemberOf.toLowerCase().startsWith(PART_PATH_ALLUSER_PREFIX.toLowerCase())) {
            userRole = USER;
        } else if(isMemberOf.toLowerCase().startsWith(PART_PATH_READONLYADMIN_PREFIX.toLowerCase())) {
            userRole = ID_READONLY_ADMIN;
        }

        return userRole;
    }

    /**
     * Extract service entitlement.
     *
     * @param group
     *            the group
     * @return the string
     */
    private static String extractServiceRole(final String isMemberOf) {
        Matcher matcher = REG_EXP_PATTERN_SERVICE_ROLE.matcher(isMemberOf);
        if (matcher.find()) {
            return matcher.group(matcher.groupCount()).toLowerCase();
        } else {
            return null;
        }
    }

    private static String extractSystemRole(final String isMemberOf) {
        Matcher matcher = REG_EXP_PATTERN_SYSTEM_ROLE.matcher(isMemberOf);
        if (matcher.find()) {
            return matcher.group(matcher.groupCount()).toLowerCase();
        } else {
            return null;
        }
    }

    public Set<String> getEntitledService() {
        //So far for org provisional account do not have entitled services.
        if(ACCOUNT_TYPE_ORG_PROV.equalsIgnoreCase(accountType)) {
            return null;

        }
        if(ACCOUNT_TYPE_MACHINE.equalsIgnoreCase(accountType)) {
            return cisServices;
        }

        if (isMemberOfSet == null) {
            return null;
        }
        Set<String> entitlements = new HashSet<String>();
        for (String isMemberOf : isMemberOfSet) {
            String serviceEntitlment = extractServiceEntitlement(isMemberOf);
            if (serviceEntitlment != null) {
                entitlements.add(serviceEntitlment);
            }
        }
        return entitlements;
    }

    public Set<String> getUserRoles() {
        if (ACCOUNT_TYPE_ORG_PROV.equalsIgnoreCase(accountType)){
            return null;
        }
        if (isMemberOfSet == null) {
            return null;
        }
        Set<String> roles = new HashSet<String>();
        if(ACCOUNT_TYPE_SA.equalsIgnoreCase(accountType)) {
            for (String isMemberOf : isMemberOfSet) {
                String systemRole = extractSystemRole(isMemberOf);
                if(systemRole != null) {
                    //exclude entitlement group
                    if (!systemRole.startsWith(STRING_ENTITLEMENT_GROUP))
                        roles.add(systemRole);
                }
            }
        }
        else {
            for (String isMemberOf : isMemberOfSet) {
                String userRole = extractUserRole(isMemberOf);
                if(userRole != null) {
                    roles.add(userRole);
                    //The following is for supporting backwards compatibility
                    if(userRole.equals(ID_FULL_ADMIN)) {
                        roles.add(FULL_ADMIN);
                    } else if(userRole.equals(ID_USER_ADMIN)) {
                        roles.add(USER_ADMIN);
                    } else if (userRole.equals(ID_READONLY_ADMIN)) {
                    	roles.add(READONLY_ADMIN);
                    }
                }             
                String userServiceRole = extractServiceRole(isMemberOf);                
                if(userServiceRole != null) {
                    roles.add(userServiceRole);
                }
            }
        }
        return roles;

    }

}
