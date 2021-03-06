import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.*;

public class WalletArray {

	public PrivateKey privateKey;
	public PublicKey publicKey;
	
	public ArrayList<Wallet> WalletArray = new ArrayList<Wallet>(); 
	
	public WalletArray() {
		generateKeyPair();
	}
	
	public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			//Initialize key generator and generate a KeyPair
			keyGen.initialize(ecSpec, random);	//256 bytes provides an acceptable security level
		KeyPair keyPair = keyGen.generateKeyPair();
		//Set the public and private keys from the keyPair
		privateKey = keyPair.getPrivate();
		publicKey = keyPair.getPublic();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addWallet(Wallet wallet) {
		WalletArray.add(wallet);
		
	}
	
}
