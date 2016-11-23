package com.myprojects.ci.main;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class NimbusJose {
	
	private String _ENCRYPTION_KEY = "TizTYy3qY8LI2deBEYmQ6uPGgjL0uVFitrbnGH7QSC4=";
	
	private static String  _PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAno2er3jpruyVfJTyftuTlhzK9lWjU50QcT3tIGbZZoPn3oCLLWcahxNwmyxRBEMquyxf6EnIgJBipdKSxYKF1OBZ93KvF/P9AEYs+3L6K/WEddyTZaVBIH979+jLZ83HGKLIERjDBWYxmF4NaPWUGnb28nes+nZZq6/iK+uV1CrAX5RgpndHWetJHR6g9389Mw0lY8weOOMGuBa0kNSXy/XUa8etk2FtFxvXiTGBzqDOYxMQIRiDvlV8k1C5Smk4gC728hrFMgNRMxS0n5/HEgjjiuMZby2zpuFF9bn0RlFtCfr0m02148dknDU2eKo1qROXqRJiW5Q8ql2koDXSdwIDAQAB";
	
	private static String  _PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCejZ6veOmu7JV8lPJ+25OWHMr2VaNTnRBxPe0gZtlmg+fegIstZxqHE3CbLFEEQyq7LF/oSciAkGKl0pLFgoXU4Fn3cq8X8/0ARiz7cvor9YR13JNlpUEgf3v36MtnzccYosgRGMMFZjGYXg1o9ZQadvbyd6z6dlmrr+Ir65XUKsBflGCmd0dZ60kdHqD3fz0zDSVjzB444wa4FrSQ1JfL9dRrx62TYW0XG9eJMYHOoM5jExAhGIO+VXyTULlKaTiALvbyGsUyA1EzFLSfn8cSCOOK4xlvLbOm4UX1ufRGUW0J+vSbTbXjx2ScNTZ4qjWpE5epEmJblDyqXaSgNdJ3AgMBAAECggEAIUNKuDtgow8FKXbCDPUZ2FNADT/YHPGFgoxgU/zfvQ8NNPO4vxSzTwU4CYXZlVBWBaCAmmXoR3iDCZDMs9z3ILrUhQItU5QfTtBqHXJ4o4JFgMh+r8Nkj2HPayoOij90dmmoGVYdi+Ntoi6RF6T1pjuxARs0UfNESq2CgH5TF4huQ5Vl756g8/817TiXcK8SLVg3gw8OphrLTVijGTWzPzUsmCg47AW67kpVsuo9HaVwLtybrTKrgTvgl6pwdPugDBHRwx3ltcdb29Tmp0m9Xi2P+dmpjwHShrchyrNrn0cFsY+8WvGkQnTFIRv0PbKwtl2FvjLPfYgaORCT+TpBsQKBgQD2d2muIqzLZog82Foec/uhgA6Q2iHSOSof5AVYTWFH47V8//e/A9XUR2v/PasFmhbOpJG8kZqB1OiDRh2kj7es9NcY1nmgLQ0HS+/1M9irD6aOe/7jNBrDjXOGuGRVrL/TNOJTSr9r5dSvYlIlk6sAUoSEg640iqqwVs+KMdjV/wKBgQCkr6msQiDBpo9FT5NvXiKaXpWy9PpF8c4jlNGBd9l81n/DaIUd+ul6PNRlLzTINlp8MQmvlPW8kw+n7JBE+7Yf/CSS7GXmhQaktPMi4g9z5SJVhBbsgOikmxB0Jm2wRoN84wQAUUgziPYC4/TNy9MXTcQerT9uB0n29RVRTnaziQKBgAOhDIBQ6GRyVQ04TlNmd80P+qbtX1QeHV2s2NtNAGKzmqeFiVtZrhtbFd8DRHJnq3BNggvZxQGmZeoQ7TzQFO9KB6TI+699GlUCLD1bDxStvVEZyE4GJWK9wXa5XljYm1Z7z91UG292SeRvOTBudTUjAeqFmdoSNKM2FAY/56Z3AoGABWzgCxO2noqNJ7xiYAXa3/kDt3yIjitUHin16Oz41jr+xII5+G3eqS891AOFGp9nxbWb2xPuMIMrvd12Nz+PlWgqEEkHZlu1h1GKvPhzh97hja2nT61DfMC6/wiRDoCULlYQOK500XXOnPPDidAQKTFfp58PeuF0l1UlmaMbF/kCgYEAxa8sH/fMwkL5S2lv5I0yxdfhfRNfJCp2+uK2KdWfTKo7g99uQNUZUbiLI6a0+Yuus+VloaqmQNZl10SRRyZjJzn7xg3U/PeJ30vKnvFwRn/6QZqMp4ZfLSyMJZqL1L85FI79M7TOufsUvSld1u4H57S+yroZYRh8oljqOvqVduc=";
	
	
	public static void main(String [] args) {
		
		NimbusJose jose = new NimbusJose();
		
		try {
			jose.encryptAndRSASignSample();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private String JWSHelloOwrld() throws Exception{
		// Create an HMAC-protected JWS object with some payload
		JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
		                                    new Payload("Hello world!"));

		// We need a 256-bit key for HS256 which must be pre-shared
		byte[] sharedKey = new byte[32];
		new SecureRandom().nextBytes(sharedKey);

		// Apply the HMAC to the JWS object
		jwsObject.sign(new MACSigner(sharedKey));

		// Serialise to URL-safe format
		jwsObject.serialize();
		
		
		
		System.out.println("JWSHelloOwrld::"+ jwsObject.getSignature().toString()); 
		
		return jwsObject.toString();
		
	}
	
	
	public String signAndEncryptSample() throws Exception {
		
		// Generate 256-bit AES key for HMAC as well as encryption
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		SecretKey secretKey = keyGen.generateKey();

		// Create HMAC signer
		JWSSigner signer = new MACSigner(secretKey.getEncoded());

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
	     							.subject("fef3476e-24c8-4aaa-a0cf-52d154c686d5")
	     							.claim("iss", "https://idbroker.webex.com")
	     							.claim("client_id", "C3beada7bb02298024e661e205e0fd60843847260ebe2eec366ccaed451dfa48e")
	     							.claim("cis_uuid", "fef3476e-24c8-4aaa-a0cf-52d154c686d5")
	     							.claim("realm", "b3be0dba-7efe-4949-a7e7-4190f7072b46")
	     							.claim("scope", "['Identity:SCIM', 'Identity:Organization']")
	     							.claim("entitled_service", "['squared-room-moderation', 'files']")
	     							.claim("cisRole", "['User']")
	     							.claim("token_type", "bearer")
	     							.claim("user_type", "user")
	     							.claim("expiry_time", "1441580903376")
	     							.build();

		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

		// Apply the HMAC
		signedJWT.sign(signer);

		// Create JWE object with signed JWT as payload
		JWEObject jweObject = new JWEObject(
		    new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).contentType("JWT").build(),
		    new Payload(signedJWT));

		// Perform encryption
		jweObject.encrypt(new DirectEncrypter(secretKey.getEncoded()));

		// Serialise to JWE compact form
		String jweString = jweObject.serialize();
		
		System.out.println("gereateJWEString===" + jweString);
		System.out.println("the length is===" + jweString.length());

		// Parse the JWE string
		JWEObject jweObject2 = JWEObject.parse(jweString);

		// Decrypt with shared key
		jweObject2.decrypt(new DirectDecrypter(secretKey.getEncoded()));

		// Extract payload
		SignedJWT signedJWT2 = jweObject2.getPayload().toSignedJWT();
		boolean result = signedJWT.verify(new MACVerifier(secretKey.getEncoded()));
		System.out.println("verifySignature.result==="+result);
		
		System.out.println("SignJWT2==="+signedJWT2.getPayload());
		
		return jweString;

		
	}
	
	   
	public String encryptAndRsaEncryptSample() throws Exception {
		
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		keyGenerator.initialize(1024);

		KeyPair kp = keyGenerator.genKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey)kp.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey)kp.getPrivate();
		
		// Create RSA-signer with the private key
		JWSSigner signer = new RSASSASigner(privateKey);

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
	     							.subject("fef3476e-24c8-4aaa-a0cf-52d154c686d5")
	     							.claim("iss", "https://idbroker.webex.com")
	     							.claim("client_id", "C3beada7bb02298024e661e205e0fd60843847260ebe2eec366ccaed451dfa48e")
	     							.claim("cis_uuid", "fef3476e-24c8-4aaa-a0cf-52d154c686d5")
	     							.claim("realm", "b3be0dba-7efe-4949-a7e7-4190f7072b46")
	     							.claim("scope", "['webexsquare:get_conversation','Identity:SCIM']")
	     							.claim("entitled_service", "['squared-team-member', 'squared-room-moderation', 'files','messenger-interop',"
	     									+ "'webex-messenger','ciscouc', 'squared-fusion-cal', 'squared-call-initiation','squared-fusion-mgmt',"
	     									+ "'webex-squared','ciscouc', 'squared-syncup']")
	     							.claim("cisRole", "['User']")
	     							.claim("token_type", "bearer")
	     							.claim("user_type", "user")
	     							.claim("expiry_time", "1441580903376")
	     							.build();

		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

		// Apply the HMAC
		signedJWT.sign(signer);

		String s = signedJWT.serialize();
		
		System.out.println("RSA.JWS=="+s);
		
		signedJWT = SignedJWT.parse(s);
		JWSVerifier verifier = new RSASSAVerifier(publicKey);
		boolean result = signedJWT.verify(verifier);
		System.out.println("RSA.JWS.VERIFY==="+result);

		
		// Generate 256-bit AES key for HMAC as well as encryption
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		SecretKey secretKey = keyGen.generateKey();
		
		// Create JWE object with signed JWT as payload
		JWEObject jweObject = new JWEObject(
	    new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).contentType("JWT").build(),
	    new Payload(signedJWT));		
		

//		JWEObject jweObject = new JWEObject(
//			    new JWEHeader.Builder(JWEAlgorithm.A256KW, EncryptionMethod.A256CBC_HS512).contentType("JWT").build(),
//			    new Payload(signedJWT));
		
		// Perform encryption
		jweObject.encrypt(new DirectEncrypter(secretKey.getEncoded()));
		
//		jweObject.encrypt(new AESEncrypter(secretKey.getEncoded()));

		// Serialise to JWE compact form
		String jweString = jweObject.serialize();
		
		System.out.println("gereateJWEString===" + jweString);
		System.out.println("the length is===" + jweString.length());

		// Parse the JWE string
		JWEObject jweObject2 = JWEObject.parse(jweString);

		// Decrypt with shared key
		jweObject2.decrypt(new DirectDecrypter(secretKey.getEncoded()));
//		jweObject2.decrypt(new AESDecrypter(secretKey.getEncoded()));

		// Extract payload
		SignedJWT signedJWT2 = jweObject2.getPayload().toSignedJWT();
		result = signedJWT.verify(new RSASSAVerifier(publicKey));
		System.out.println("verifySignature.result==="+result);
		
		System.out.println("SignJWT2==="+signedJWT2.getPayload());
		
		JSONObject jsonObj = signedJWT2.getPayload().toJSONObject();
		String realm = (String) jsonObj.get("realm");
		String cisUUID = (String) jsonObj.get("cis_uuid");
		String scope = (String) jsonObj.get("scope");
		System.out.println("realm==="+realm);
		System.out.println("cisUUID==="+cisUUID);
		System.out.println("client_id==="+scope);

		return jweString;

		
	}
	
	public String encryptAndRSASignSample() throws Exception {
		
		
		// Generate 256-bit AES key for HMAC as well as encryption
		/*
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		SecretKey secretKey = keyGen.generateKey();
		*/
		
		byte[] encoded = Base64.getDecoder().decode(_ENCRYPTION_KEY.getBytes());
		SecretKey secretKey = new SecretKeySpec(encoded, "AES");
		
		Set<String> scopes = new HashSet<String> ();
		scopes.add("webexsquare:get_conversation");
		scopes.add("Identity:SCIM");
		
		Set<String> cisRoles = new HashSet<String> ();
		cisRoles.add("User");
		
		Set<String> entitleServices = new HashSet<String> ();
		entitleServices.add("squared-team-member");
		entitleServices.add("squared-room-moderation");
		entitleServices.add("files");
		entitleServices.add("messenger-interop");
//		JWTClaimsSet claimsSet4Encryption = new JWTClaimsSet.Builder()
//			.claim("scope", "['webexsquare:get_conversation','Identity:SCIM']")
//			.claim("entitled_service", "['squared-team-member', 'squared-room-moderation', 'files','messenger-interop',"
//					+ "'webex-messenger','ciscouc', 'squared-fusion-cal', 'squared-call-initiation','squared-fusion-mgmt',"
//					+ "'webex-squared','ciscouc', 'squared-syncup']")
//			.claim("cisRole", "['User']")
//			.build();
		
		JWTClaimsSet claimsSet4Encryption = new JWTClaimsSet.Builder()
		.claim("scope", scopes)
		.claim("entitled_service", entitleServices)
		.claim("cisRole", cisRoles)
		.build();
		
		// Create JWE object with signed JWT as payload
		JWEObject jweObject = new JWEObject(
	    new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256).contentType("JWT").build(),
	    new Payload(claimsSet4Encryption.toJSONObject()));		

		// Perform encryption
		jweObject.encrypt(new DirectEncrypter(secretKey.getEncoded()));

		// Serialise to JWE compact form
		String jweString = jweObject.serialize();
		
		System.out.println("RSA.JWS.encryptedData==="+jweString);

		/*
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		keyGenerator.initialize(2048);

		KeyPair kp = keyGenerator.genKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey)kp.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey)kp.getPrivate();
		
		
		byte[] publicKeyBytes = publicKey.getEncoded();
		
		String pubKeyStr = Base64.getEncoder().encodeToString(publicKeyBytes);
		String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
		System.out.println(" publicKey == "+ pubKeyStr);
		System.out.println(" privateKey == "+ privateKeyStr);
		
*/
		
		// get the private key
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		KeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(_PRIVATE_KEY.getBytes()));
		RSAPrivateKey privateKey2 = (RSAPrivateKey)keyFactory.generatePrivate(privateKeySpec);
		  
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(_PUBLIC_KEY.getBytes()));
		KeyFactory keyFact = KeyFactory.getInstance("RSA");
		RSAPublicKey pubKey2 = (RSAPublicKey) keyFact.generatePublic(x509KeySpec);

		
		/*
		byte[] encodedPublicKey = Base64.decode(_PUBLIC_KEY.getBytes());
		byte[] encodedPrivateKey = Base64.decode(_PRIVATE_KEY.getBytes());
		
		BigInteger modulus = new BigInteger(1, encodedPublicKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec ks = new RSAPublicKeySpec(modulus, pubExp);
		RSAPublicKey pubKey = (RSAPublicKey)keyFactory.generatePublic(ks);
		 */
		
		System.out.println(" publicKey2 == "+ Base64.getEncoder().encodeToString(pubKey2.getEncoded()));
		
		System.out.println(" EncryptionKey == "+ Base64.getEncoder().encodeToString(secretKey.getEncoded()));
		
		// Create RSA-signer with the private key
		JWSSigner signer = new RSASSASigner(privateKey2);

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
	     							.claim("iss", "https://idbroker.webex.com")
	     							.claim("client_id", "C3beada7bb02298024e661e205e0fd60843847260ebe2eec366ccaed451dfa48e")
	     							.claim("cis_uuid", "fef3476e-24c8-4aaa-a0cf-52d154c686d5")
	     							.claim("realm", "b3be0dba-7efe-4949-a7e7-4190f7072b46")
	     							.claim("token_id", "YTI3MTMxMDctOWJlNi00YTkzLThhMzItZGZjMDkzMjA0YTMzNGZiYzZhODgtNWIx")
	     							.claim("token_type", "bearer")
	     							.claim("user_type", "user")
	     							.claim("expiry_time", "1441580903376")
	     							.claim("private", jweString)
	     							.build();

		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

		// Apply the HMAC
		signedJWT.sign(signer);

		String s = signedJWT.serialize();
		
		System.out.println("ENCRYPT.RSA.JWS=="+s);
		System.out.println("the length is===" + s.length());

		
		signedJWT = SignedJWT.parse(s);
		JWSVerifier verifier = new RSASSAVerifier(pubKey2);
		boolean result = signedJWT.verify(verifier);
		System.out.println("RSA.JWS.VERIFY==="+result);

		JWTClaimsSet singedToken = signedJWT.getJWTClaimsSet();
		String realm = (String) singedToken.getClaim("realm");
		String cisUUID = (String) singedToken.getClaim("cis_uuid");
		String client_id = (String) singedToken.getClaim("client_id");
		System.out.println("realm==="+realm);
		System.out.println("cisUUID==="+cisUUID);
		System.out.println("client_id==="+client_id);
		
		String encryptedData = (String) singedToken.getClaim("private");
		
		System.out.println("RSA.JWS.encryptedData==="+encryptedData);

		// Parse the JWE string
		JWEObject jweObject2 = JWEObject.parse(encryptedData);

		// Decrypt with shared key
		jweObject2.decrypt(new DirectDecrypter(secretKey.getEncoded()));

		System.out.println("jweObject2==="+jweObject2.getPayload());
		
		JSONObject jsonObj = jweObject2.getPayload().toJSONObject();
		Object obj = jsonObj.get("scope");
		System.out.println(obj.getClass().getName());
		JSONArray scope = (JSONArray) jsonObj.get("scope");
		JSONArray cisRole = (JSONArray) jsonObj.get("cisRole");
		JSONArray entitled_service = (JSONArray) jsonObj.get("entitled_service");
		
		Set<String> scopeResult = new HashSet<String>();
		for(int i=0; i<scope.size();i ++) {
			scopeResult.add((String)scope.get(i));
		}
		System.out.println("scope==="+scopeResult);
		System.out.println("cisRole==="+cisRole);
		System.out.println("entitled_service==="+entitled_service);
		

		return jweString;

		
	}
	
	public String rsaSignAndEncryptSample() throws Exception {
		
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		keyGenerator.initialize(2048);

		KeyPair kp = keyGenerator.genKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey)kp.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey)kp.getPrivate();
		
		
		// Create RSA-signer with the private key
		JWSSigner signer = new RSASSASigner(privateKey);

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
	     							.claim("iss", "https://idbroker.webex.com")
	     							.claim("client_id", "C3beada7bb02298024e661e205e0fd60843847260ebe2eec366ccaed451dfa48e")
	     							.claim("cis_uuid", "fef3476e-24c8-4aaa-a0cf-52d154c686d5")
	     							.claim("realm", "b3be0dba-7efe-4949-a7e7-4190f7072b46")
	     							.claim("scope", "['webexsquare:get_conversation','Identity:SCIM']")
	     							.claim("entitled_service", "['squared-team-member', 'squared-room-moderation', 'files','messenger-interop'"
	     									+ "'webex-messenger','ciscouc', 'squared-fusion-cal', 'squared-call-initiation','squared-fusion-mgmt']"
	     									+ "'webex-squared','ciscouc', 'squared-syncup']")
	     							.claim("cisRole", "['User']")
	     							.claim("token_id", "YTI3MTMxMDctOWJlNi00YTkzLThhMzItZGZjMDkzMjA0YTMzNGZiYzZhODgtNWIx")	     							
	     							.claim("token_type", "bearer")
	     							.claim("user_type", "user")
	     							.claim("expiry_time", "1441580903376")
	     							.build();

		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

		// Apply the HMAC
		signedJWT.sign(signer);

		String s = signedJWT.serialize();
		
		System.out.println("RSA.JWS=="+s);
		
		signedJWT = SignedJWT.parse(s);
		JWSVerifier verifier = new RSASSAVerifier(publicKey);
		boolean result = signedJWT.verify(verifier);
		System.out.println("RSA.JWS.VERIFY==="+result);

		
		// Generate 256-bit AES key for HMAC as well as encryption
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		SecretKey secretKey = keyGen.generateKey();
		
		
		// Create JWE object with signed JWT as payload
		JWEObject jweObject = new JWEObject(
	    new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).contentType("JWT").build(),
	    new Payload(signedJWT));		
		

//		JWEObject jweObject = new JWEObject(
//			    new JWEHeader.Builder(JWEAlgorithm.A256KW, EncryptionMethod.A256CBC_HS512).contentType("JWT").build(),
//			    new Payload(signedJWT));
		
		// Perform encryption
		jweObject.encrypt(new DirectEncrypter(secretKey.getEncoded()));
		
//		jweObject.encrypt(new AESEncrypter(secretKey.getEncoded()));

		// Serialise to JWE compact form
		String jweString = jweObject.serialize();
		
		System.out.println("gereateJWEString===" + jweString);
		System.out.println("the length is===" + jweString.length());

		// Parse the JWE string
		JWEObject jweObject2 = JWEObject.parse(jweString);

		// Decrypt with shared key
		jweObject2.decrypt(new DirectDecrypter(secretKey.getEncoded()));
//		jweObject2.decrypt(new AESDecrypter(secretKey.getEncoded()));

		// Extract payload
		SignedJWT signedJWT2 = jweObject2.getPayload().toSignedJWT();
		result = signedJWT.verify(new RSASSAVerifier(publicKey));
		System.out.println("verifySignature.result==="+result);
		
		System.out.println("SignJWT2==="+signedJWT2.getPayload());
		
		JSONObject jsonObj = signedJWT2.getPayload().toJSONObject();
		String realm = (String) jsonObj.get("realm");
		String cisUUID = (String) jsonObj.get("cis_uuid");
		String scope = (String) jsonObj.get("scope");
		System.out.println("realm==="+realm);
		System.out.println("cisUUID==="+cisUUID);
		System.out.println("client_id==="+scope);

		return jweString;

		
	}
	
	public void JWESharedKeySample() throws Exception{
		// The shared key
		byte[] key128 = {
		(byte)177, (byte)119, (byte) 33, (byte) 13, (byte)164, (byte) 30, (byte)108, (byte)121,
		(byte)207, (byte)136, (byte)107, (byte)242, (byte) 12, (byte)224, (byte) 19, (byte)226 };

		// Create the header
		JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM);

        Payload payload = new Payload(tokenData);
	

		// Create the JWE object and encrypt it
		JWEObject jweObject = new JWEObject(header, payload);
		jweObject.encrypt(new DirectEncrypter(key128));

		// Serialise to compact JOSE form...
		String jweString = jweObject.serialize();

		System.out.println(jweString);
		
		// Parse into JWE object again...
		jweObject = JWEObject.parse(jweString);

		// Decrypt
		jweObject.decrypt(new DirectDecrypter(key128));

		// Get the plain text
		payload = jweObject.getPayload();
		
		System.out.println(payload);
	}
	
	static String tokenData = "{\"iss\": \"https://idbroker.webex.com\"," 
			   +  "\"realm\": \"b3be0dba-7efe-4949-a7e7-4190f7072b46\","
			   +  " \"cis_uuid\": \"fef3476e-24c8-4aaa-a0cf-52d154c686d5\","
			   +  " \"user_type\": \"user\","
			   +  " \"token_type\": \"bearer\","
			   +  " \"expiry_time\": 1441580903376,"
			   +  " \"token_id\": \"NTNmODA1NzYtMzhiZS00ZmEyLWE3MTktMTRiOGY5NjVlZDVjNjY5ZTY2YjgtMDJi\","
			   +  " \"client_id\": \"C3beada7bb02298024e661e205e0fd60843847260ebe2eec366ccaed451dfa48e\","
			   +  " \"scope\": ["
			   +  "     \"Identity:SCIM\","
			   +  "     \"Identity:Organization\"],"
			   +  " \"cisRole\": ["
			   +  "     \"Full Admin\""
			   +  " ],"
			   +  " \"entitled_service\": ["
			   +  "						        \"squared-team-member\","
			   +  "					        \"squared-room-moderation\","
			   +  "				        \"files\","
			   +  "			        \"messenger-interop\","
			   +  "		        \"webex-messenger\","
			   +  "	        \"ciscouc\","
			   +  "     \"squared-fusion-cal\","
			   +  "       \"squared-call-initiation\","
			   +  "      \"squared-fusion-mgmt\","
		       +  "       \"webex-squared\","
			   +  "      \"squared-syncup\"]}";

}

