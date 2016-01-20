/**
 * Author: Dennis Grzegorzewski
 * Date: 01/19/2016
 * FileName: AccountHolder.java
 * Class: ITMD 510
 * Assignment: LAB1
 * Due: 01/30/2016 11:59PM
 */

/**
 * AccountHolder class
 */
public class AccountHolder
{
	
	private static double annualInterestRate;
	private double balance;
	
    /**
     * Constructor method of AccountHolder class
	 * @param balance
	 */
	public AccountHolder(double balance) {
		//super();
		this.balance = balance;
	}

	/**
	 * Instance method to add a deposit amount to the balance
     * @param depositAmount
     */
	public void deposit(double depositAmount)
	{
		// Add amount deposited to balance.
		balance = balance + depositAmount;
    }
	
    /**
     * Instance method to subtract a withdrawal  amount from the balance
     * @param withdrawal Amount
     */
    public void withdrawal(double withdrawalAmount)
    {
    	// Initialize transactionFee.
		double transactionFee = 0.0;
		
		// Balances below $500 are charged a $50 transaction fee.
    	if (balance - withdrawalAmount < 500.0)
    	{
    		// set transaction fee for low balance
    		transactionFee = 50.0;
    	}
    	
		// Balances below $100 are not allowed.
    	if (balance - withdrawalAmount - transactionFee >= 100.0)
    	{
    		// Deduct amount withdrawn from balance.
    		balance = balance - withdrawalAmount - transactionFee;
    	}
    }
	
    /**
     * 
     */
    public void monthlyInterest()
    {
    	// Add monthly interest to balance 
    	balance += balance * (annualInterestRate / 12.0);
    }
	
    /**
     * @param rateUpdate
     */
    public static void modifyMonthlyInterest(double rateUpdate)
    {
		// TODO Auto-generated method stub
    	annualInterestRate = rateUpdate;
    }

	/**
	 * 
	 */
	@Override
	public String toString() {
		return String.format("$%.2f", balance);

	}
	
}
