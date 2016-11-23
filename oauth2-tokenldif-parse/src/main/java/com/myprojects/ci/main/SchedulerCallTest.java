package com.myprojects.ci.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.myprojects.ci.util.JsonUtil;
import com.myprojects.ci.util.OAuth2HttpClient;


public class SchedulerCallTest {

	public static void main(String [] args) {
		String orgID = "123456789";
		String [] includeOrgIDS = {"xxx0sss",orgID, "23232-2323-232"};
		String [] excluedeOrgIDS = {"998122-129q9x-1212",orgID, "j2112-211-121"};
		
		Set<String> set1 = new HashSet<String>(Arrays.asList(includeOrgIDS));
		Set<String> set2 = new HashSet<String>(Arrays.asList(excluedeOrgIDS));
		
		if(!checkIfNeedSend(orgID, set1, set2)) {
			System.out.println("Do not send");
		} else {
			System.out.println("send");
		}
		
		String [] excluedeOrgIDS2 = {"998122-129q9x-1212","j2112-211-121"};
		
		set1 = new HashSet<String>(Arrays.asList(includeOrgIDS));
		set2 = new HashSet<String>(Arrays.asList(excluedeOrgIDS2));
		
		if(!checkIfNeedSend(orgID, set1, set2)) {
			System.out.println("Do not send");
		} else {
			System.out.println("send");
		}
		
		
		String [] includeOrgIDS3 = {"xxx0sss", "23232-2323-232"};
		String [] excluedeOrgIDS3 = {"998122-129q9x-1212", "j2112-211-121"};
		
		set1 = new HashSet<String>(Arrays.asList(includeOrgIDS3));
		set2 = new HashSet<String>(Arrays.asList(excluedeOrgIDS3));
		
		if(!checkIfNeedSend(orgID, set1, set2)) {
			System.out.println("Do not send");
		} else {
			System.out.println("send");
		}
	}
	
	
    private static boolean checkIfNeedSend(String orgID, Set<String> orgIDS, Set<String> excluedeOrgIDS) {
        
		boolean needSendNotification = false;
		
		if(excluedeOrgIDS != null && excluedeOrgIDS.contains(orgID)) {
			needSendNotification = false;
		} else if(orgIDS != null && orgIDS.contains(orgID)) {
			needSendNotification = true;
		} else if(isTestOrg(orgID)) {
			needSendNotification = false;
		} else {
			needSendNotification = true;
		}
		
    	return needSendNotification;
    }
	
    
    private static boolean isTestOrg(String orgID) {
    	System.out.println("check if test org");
    	return true;
    }
	public static void main2(String [] args) {
		
		String schedulerEndpoint = "https://scheduler-a.wbx2.com/scheduler/api/v1/tasks";
		
		Map<String, String> headers = new HashMap<String, String>();
		String accessToken = "YmRmMTQxZDUtMThlOC00NGNkLWJiNzQtNTdjNTJkZWRhZTFlZDYzMTBkMDUtZTY311"; //This is for test
		headers.put("Authorization", "Bearer " + accessToken);

		Set<String> referenceIDs = new HashSet<String>();
		referenceIDs.add("oowec-we9ew9ck-ewidckk");
		referenceIDs.add("ewoowed-ewdciwei");
		
		Set<String> accessTokenIDs = new HashSet<String>();
		accessTokenIDs.add("ii-99--2d--0");
		accessTokenIDs.add("ewrew-wvdr3w-23edc-23");
		
		String message = getNotificationMessage(referenceIDs, accessTokenIDs);
		
		String postBody = " { \"tag\": \"OAuth2Token-Revoke-Notification\","
						+ "   \"schedule\": { \"startAt\" : " + 300 + " },"
						+ "   \"action\": { \"type\" : \"HTTP\" , \"method\" : \"POST\", \"uri\" : \"https://idbroker.webex.com/idb/HealthCheck.jsp\","
									   + "  \"headers\" : {\"Content-Type\": \"application/json\"},"
						+ "                 \"body\":\"" + message + "\" } "
						+ " } ";
		
		System.out.println("Scheuler task with post body:" + postBody);
		
		OAuth2HttpClient.APIResponse apiResponse = null;
		boolean retried = false;
		try {
			apiResponse  = OAuth2HttpClient.getInstance().httpPostCall(schedulerEndpoint, headers, postBody);
			System.out.println(apiResponse.getResponse());
			if(200 == apiResponse.getStatus()) {
				String taskID = getTaskID(apiResponse.getResponse());
				System.out.println("Succeeded to schedule a task for " + postBody + " with taskID " + taskID);
			} else if(401 == apiResponse.getStatus() && !retried) {
				
				System.out.println("===Retry==");

				
				retried = true;
				accessToken = "YmRmMTQxZDUtMThlOC00NGNkLWJiNzQtNTdjNTJkZWRhZTFlZDYzMTBkMDUtZTY3111"; //refresh a new access token
	    		headers.put("Authorization", "Bearer " + accessToken);
				apiResponse  = OAuth2HttpClient.getInstance().httpPostCall(schedulerEndpoint, headers, postBody);
				System.out.println(apiResponse.getResponse());
				if(200 == apiResponse.getStatus()) {
					String taskID = getTaskID(apiResponse.getResponse());
					System.out.println("Succeeded to re-schedule a task for " + postBody + " with taskID " + taskID);
				} else {
					System.out.println("Failed to re-schedule a task for " + postBody + " , status is "
											+ apiResponse.getStatus() + ", response is " + apiResponse.getResponse());
				}						
			} else {
				System.out.println("Failed to schedule a task for " + postBody + " , status is "
										+ apiResponse.getStatus() + ", response is " + apiResponse.getResponse());
			}
		} catch (Exception e) {
			System.out.println("Failed to schedule a task for " + postBody);
			e.printStackTrace();
		}

		
	}
	
	
    private static String getTaskID(String response) {
    	if(response == null) {
    		return null;
    	}
		java.util.Map result = JsonUtil.fromJson(response, java.util.Map.class);
		if(result != null) {
			String taskID = (String) result.get("id");
			return taskID;
		}
		return null;
    }
    
	
	private static String getNotificationMessage(Set<String> referenceIDs, Set<String> accessTokenIDs) {
		if( (referenceIDs == null || referenceIDs.isEmpty())
			&& (accessTokenIDs == null || accessTokenIDs.isEmpty())) {
			return null;
		}
		
		StringBuffer messageBuffer = new StringBuffer();
		messageBuffer.append("{");
		
		if(referenceIDs != null && !referenceIDs.isEmpty()) {
			Iterator ite = referenceIDs.iterator();
			messageBuffer.append("\"reference_id\": [");
			String value = "";
			while (ite.hasNext()) {
				value += "\"" + (String) ite.next() + "\",";
			}
			messageBuffer.append(value.substring(0, value.length() - 1));
			messageBuffer.append("]");
		}
		
		if(accessTokenIDs != null && !accessTokenIDs.isEmpty()) {
			Iterator ite = accessTokenIDs.iterator();
			if(referenceIDs != null && !referenceIDs.isEmpty()) {
				messageBuffer.append(",");
			}
			messageBuffer.append("\"token_id\": [");
			String value = "";
			while (ite.hasNext()) {
				value += "\"" + (String) ite.next() + "\",";
			}
			messageBuffer.append(value.substring(0, value.length() - 1));
			messageBuffer.append("]");
		}
		
		messageBuffer.append("}");
		String message =  messageBuffer.toString();
		if(message != null) {
			String replaceDoubleQuoteMessage = message.replaceAll("\"","\\\\\"");
			return replaceDoubleQuoteMessage;
		}
		return null;
	}

	
	
	
}
