package com.myprojects.ci.main;

import java.util.HashSet;
import java.util.Set;

public class CheckUserRole {

	public static void main(String[] args) {
		Set<String> roleSet = new HashSet<String>();

		roleSet.add(
				"cn=full admin group,ou=System,o=56e1ed0b-6352-4ce7-8d96-79a22ca0d2fd,ou=AllZone,ou=Shared,dc=Identity");
		roleSet.add(
				"cn=full admin group,ou=System,o=ff5eaae0-cd8e-48be-80f7-06dcccd8ec5e,ou=AllZone,ou=Shared,dc=Identity");
		roleSet.add(
				"cn=full admin group,ou=System,o=2b059e74-4753-4bc8-b73a-94c97c306049,ou=AllZone,ou=Shared,dc=Identity");
		roleSet.add(
				"cn=full admin group,ou=System,o=4590eb6a-2ca2-4394-bc27-9b671ce2fe73,ou=AllZone,ou=Shared,dc=Identity");
		roleSet.add("cn=base service group,ou=System Roles,dc=Identity");
		roleSet.add("cn=org base read group,ou=System Roles,dc=Identity");
		roleSet.add(
				"cn=full admin group,ou=System,o=584cf4cd-eea7-4c8c-83ee-67d88fc6eab5,ou=AllZone,ou=Shared,dc=Identity");
		roleSet.add("cn=machine account read group,ou=System Roles,dc=Identity");
		roleSet.add(
				"cn=full admin group,ou=System,o=4214d345-7caf-4e32-b015-34de878d1158,ou=AllZone,ou=Shared,dc=Identity");
		roleSet.add("cn=oauth key service group,ou=System Roles,dc=Identity");
		roleSet.add(
				"cn=full admin group,ou=System,o=df01c00b-f03e-4dfc-8cc2-f2eed73559ef,ou=AllZone,ou=Shared,dc=Identity");
		roleSet.add(
				"cn=full admin group,ou=System,o=3dfc2d0c-a708-4dfa-8ab0-2b010b92e550,ou=AllZone,ou=Shared,dc=Identity");
		roleSet.add("cn=notification subscription group,ou=System Roles,dc=Identity");
		roleSet.add("cn=spark.getroomparticipants-test,ou=System Roles,dc=Identity");
		roleSet.add(
				"cn=full admin group,ou=System,o=62058772-313d-42e2-b7f6-39fca400b319,ou=AllZone,ou=Shared,dc=Identity");
		
		OAuth2User  user = new OAuth2User();
		user.setAccountType("service");
		user.setIsMemberOfSet(roleSet);
		
		Set<String> userRoles = user.getUserRoles();
		
		System.out.println(userRoles);
		
		
		OldOAuth2User  oldUser = new OldOAuth2User();
		oldUser.setAccountType("service");
		oldUser.setIsMemberOfSet(roleSet);
		
		Set<String> oldUserRoles = oldUser.getUserRoles();
		
		System.out.println(oldUserRoles);

	}
}
