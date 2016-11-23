package com.myprojects.ci.ldifparse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OAuth2TokenParserNew implements Parser {
    private OAuth2TokenDao oauth2TokenDao = null;

    public OAuth2TokenDao getOauth2TokenDao() {
        return oauth2TokenDao;
    }

    public void setOauth2TokenDao(OAuth2TokenDao oauth2TokenDao) {
        this.oauth2TokenDao = oauth2TokenDao;
    }

    public void parse(String fileName) throws Exception {
        parse2StringList(fileName);
    }

    /*
     * oauth2expirytime: 1424400536029 oauth2tokenid:
     * M2E0M2FhYWItZDQ4MS00ZWI3LWE5YzktMzc4NGNiNzc5MWQ4NGRiYjkyNWMtMWM2
     * oauth2scope: webexsquare:get_conversation oauth2realm: / oauth2type:
     * access_token oauth2clientid:
     * Ca01245e5cc2fd0f69f8d25d32a5aa235f63c78f338b518b5fd3e2a3e9572c00b
     * entryUUID: 5ef189d7-4968-4aa3-9f8c-a3e09a8717aa creatorsName:
     * cn=brokerUser,ou=System Services,dc=Identity createTimestamp:
     * 20150219144855.973Z modifyTimestamp: 20150219144855.973Z modifiersName:
     * cn=brokerUser,ou=System Services,dc=Identity ds-sync-hist::
     * ZHMtY3JlYXRlLXRpbWU6MDAwMDAxNEJBMjUwMjNBNTY2NzcwNTUyQzZCOTpyZXBsOgAAAUvvv71QI
     * ++/vQ== subschemaSubentry: cn=schema ds-entry-checksum: 12359327280
     * 
     */
    private void parse2StringList(String fileName) throws Exception {
        List<String> lineList = new ArrayList<String>();
        String line = null;
        // FileReader reads text files in the default encoding.
        try {
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            OAuth2Token newToken = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (lineList.size() > 50000 && line.startsWith("oauth2expirytime:")) {
                    // TODO: insert previous records
                    parseAndInsertTokens(lineList);
                    lineList = null;
                    System.gc();
                    lineList = new ArrayList<String>();
                }
                lineList.add(line);
            }
            // TODO: insert last records
            parseAndInsertTokens(lineList);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseAndInsertTokens(List<String> lineList) throws Exception {
        if (lineList == null || lineList.isEmpty()) {
            return;
        }
        List<OAuth2Token> tokenList = new ArrayList<OAuth2Token>();
        String line = null;
        Iterator<String> ite = lineList.iterator();
        OAuth2Token newToken = null;
        int i = 1;
        while (ite.hasNext()) {
            line = ite.next();
            boolean isNewToken = line.startsWith("oauth2expirytime:");
            if (isNewToken) {
                newToken = new OAuth2Token();
                tokenList.add(newToken);
            }
            try {
                convertOAuth2TokenProp(newToken, line);
            } catch (Exception e) {
                System.out.println("Failed to parse line" + i + " ：" + line);
            }
            i++;
        }

        this.oauth2TokenDao.insertOAuth2Token(tokenList);
    }

    private void convertOAuth2TokenProp(OAuth2Token token, String line) {
        if (line == null || line.isEmpty())
            return;
        String[] arr = new String[2];
        int idx = line.indexOf(":");
        String firstPart = line.substring(0, idx);
        String sencondPart = line.substring(idx + 1);
        if (firstPart != null) {
            arr[0] = firstPart.trim();
        }
        if (sencondPart != null) {
            arr[1] = sencondPart.trim();
        }
        if (arr != null && arr.length == 2) {
            if ("entryUUID".equalsIgnoreCase(arr[0])) {
                // oauth2tokenid=M2E0M2FhYWItZDQ4MS00ZWI3LWE5YzktMzc4NGNiNzc5MWQ4NGRiYjkyNWMtMWM2,ou=oauth2tokens,ou=openam-oauth2,ou=tokens,dc=Identity
                token.setOauth2TokenId(arr[1]);
            } else if ("oauth2clientid".equalsIgnoreCase(arr[0])) {
                token.setClientid(arr[1]);
            } else if ("oauth2realm".equalsIgnoreCase(arr[0])) {
                token.setRealm(arr[1]);
            } else if ("oauth2type".equalsIgnoreCase(arr[0])) {
                token.setTokenType(arr[1]);
            } else if ("oauth2username".equalsIgnoreCase(arr[0])) {
                token.setUid(arr[1]);
            } else if ("oauth2scope".equalsIgnoreCase(arr[0])) {
                Set scope = token.getScopes();
                if (token.getScopes() != null) {
                    scope.add(arr[1]);
                } else {
                    scope = new HashSet<String>();
                    scope.add(arr[1]);
                    token.setScopes(scope);
                }
            } else if ("oauth2cisUUID".equalsIgnoreCase(arr[0])) {
                token.setUuid(arr[1]);
            } else if ("creatorsName".equalsIgnoreCase(arr[0])) {

            } else if ("oauth2expirytime".equalsIgnoreCase(arr[0])) {
                token.setExpireTime(arr[1]);
            } else if ("createTimestamp".equalsIgnoreCase(arr[0])) {
                token.setCreateTime(arr[1]);
            } else if ("modifyTimestamp".equalsIgnoreCase(arr[0])) {
                token.setModifyTime(arr[1]);
            }
        }
    }

}
