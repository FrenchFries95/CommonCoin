import java.util.*;

public class Block {
	
	public String hash;
	public String previousHash;
	public String merkleRoot;
	public String coinName;
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //our data will be a simple message
	public long timeStamp; //as milliseconds since 1/1/1970
	public int nonce;
	
	
	//Block Constructor
	public Block(String previousHash) {
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); //This occurs after the other values are set
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp)+
				Integer.toString(nonce) +
				merkleRoot
				);
		return calculatedhash;
	}
	
	//Increases nonce value til hash target is reached
	public void mineBlock(int difficulty) {
		merkleRoot = StringUtil.getMerkleRoot(transactions);
		String target = new String(new char[difficulty]).replace('\0',  '0'); //Create a string with difficulty * "0"
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined! : " + hash);	
	}
	
	//Add transactions to this block
	public boolean addTransaction(Transaction transaction) {
		//process transaction and check if valid, unless block is genesis block then ignore.
		if(transaction == null) return false;
		if((previousHash !="0")) {
			if((transaction.processTransaction() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		transactions.add(transaction);
		System.out.println("Transaction successfully added to block");
		return true;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
