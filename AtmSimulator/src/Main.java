import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean flag1 = true;
		Vector<Account> accountList = new Vector<Account>();
		// Kalo mau akses akun default pake password abcdefg
		Account defaultAccount1 = new Account("1234567", 50000, "abcdefg","Default","Bourne"); 
		Account defaultAccount2 = new Account("7654321", 50000, "abcdefg","Default","Meyliana"); 
		Account defaultAccount3 = new Account("1231231", 50000, "abcdefg","Default","Evelyn"); 
		accountList.add(defaultAccount1);
		accountList.add(defaultAccount2);
		accountList.add(defaultAccount3);
		
		while(flag1 == true) {
			System.out.println("==============================");
			System.out.println("|       ATM SIMULATOR        |");
			System.out.println("==============================");
			System.out.println("| 1. Create Account          |");
			System.out.println("| 2. Login                   |");
			System.out.println("| 3. Available Account       |");
			System.out.println("| 4. Exit App                |");
			System.out.println("==============================");
			System.out.print(">> ");
			int userInput1 = scan.nextInt();
			scan.nextLine();
			cls();
			
			switch(userInput1) {
			case 1:
				System.out.println("--------------------------------------------------");
				System.out.println("|              Creating a new account            |");
				System.out.println("--------------------------------------------------");
				
				try {
					boolean createAccountLoop = true;
					boolean checkAccountLoop = true;
					String inputAccount = "";
					while(createAccountLoop == true || checkAccountLoop == true) {
						System.out.println("Input account number [must be numeric & >3 digit]");
						System.out.print(">> ");
						inputAccount = scan.nextLine();
						
						if(inputAccount.matches("^[0-9]*$") && inputAccount.length() > 3) {
							createAccountLoop = false;
						}
						checkAccountLoop = false;
						for(Account data : accountList) {
							if(data.getAccountNumber().equals(inputAccount)) {
								System.out.println("Account number already created!");
								checkAccountLoop = true;
								break;
							}
						}
					}
					
					boolean createBalanceLoop = true;
					int inputBalance = 0;
					while(createBalanceLoop == true) {
						System.out.println("Input balance [<= 100000]");
						System.out.print(">> ");
						inputBalance = scan.nextInt();
						scan.nextLine();
						
						if(inputBalance <= 100000 && inputBalance >= 0) {
							createBalanceLoop = false;
						}
					}
					
					boolean createPasswordLoop = true;
					String inputPassword = "";
					while(createPasswordLoop == true) {
						System.out.println("Input password [>4 character]");
						System.out.print(">> ");
						inputPassword = scan.nextLine();
						if(inputPassword.length() > 4) {
							createPasswordLoop = false;
						}
					}
					
					boolean createUsernameLoop = true;
					String inputUsername = "";
					while(createUsernameLoop == true) {
						System.out.println("Input Username [must be character only]");
						System.out.print(">> ");
						inputUsername = scan.nextLine();
						if(inputUsername.matches("^[a-zA-Z]*$")) {
							createUsernameLoop = false;
						}
					}
					Account newAccount = new Account(inputAccount, inputBalance, inputPassword, "New", inputUsername);
					accountList.add(newAccount);
					System.out.println("");
					System.out.println("Account Created!");
					System.out.println("------------------------");
					System.out.println("Account    : " + inputAccount);
					System.out.println("Balance    : " + inputBalance);
					System.out.println("Username   : " + inputUsername);
					System.out.println("------------------------\n");
					System.out.println("Press enter to continue...");
					scan.nextLine();
				}catch(Exception e) {
					System.out.println(e);
				}
				cls();
				break;
			case 2:
				System.out.println("---------------------------------------------");
				System.out.println("|           Login to your account           |");
				System.out.println("---------------------------------------------");
				System.out.println("Input account number that you want to login");
				System.out.print(">> ");
				String inputAccountLogin = scan.nextLine();
				int found = -1;
				for(int i = 0; i < accountList.size(); i++) {
					if(accountList.get(i).getAccountNumber().equals(inputAccountLogin)) {
						found = i;
					}
				}
				if(found != -1) {
					System.out.print("Input password\n>> ");
					String inputPasswordLogin = scan.nextLine();
					if(inputPasswordLogin.equals(accountList.get(found).getPassword())) {
						System.out.println("Successfully login!");
						cls();
						ATMMenu(args, accountList.get(found), accountList);
					} else {
						System.out.println("Wrong password");
					}
				} else {
					System.out.println("");
					System.out.println("Account not found!");
					
					System.out.println("Press enter to continue...");
					scan.nextLine();
				}
				
				cls();
				break;
			case 3 :
				System.out.println("----------------------------------------------------");
				System.out.printf("|%-3s|%-15s|%-8s|%-20s|\n","No","Account","Type","Username");
				System.out.println("----------------------------------------------------");
				try {
					for(int i = 0; i < accountList.size(); i++) {
						Account data = accountList.get(i);
						System.out.printf("|%-3d|%-15s|%-8s|%-20s|\n",i+1,data.getAccountNumber(),data.getAccountType(),data.getUsername());
					}
				}catch (Exception e) {
					System.out.println(e);
				}
				System.out.println("----------------------------------------------------");
				
				System.out.println("Press enter to continue...");
				scan.nextLine();
				
				cls();
				break;
			case 4 :
				System.out.println("Thank you for using this app");
				flag1 = false;
				
				cls();
				break;
			default:
				System.out.println("Invalid Input");
				break;
			}
		}
	}

	public static void ATMMenu(String[] args, Account data, Vector<Account> accountList) {
		Scanner scan = new Scanner(System.in);
		boolean flag = true;
		while(flag == true) {
			System.out.println("Hello, " + data.getUsername() + "!");
			System.out.println("What do you want to today?");
			System.out.println("1. Check Balance");
			System.out.println("2. Transfer to another account");
			System.out.println("3. Withdraw balance");
			System.out.println("4. History");
			System.out.println("5. Admin");
			System.out.println("6. Logout");
			System.out.print(">> ");
			int inputMenu = scan.nextInt();
			scan.nextLine();
			cls();
			switch(inputMenu) {
			case 1 : 
				System.out.println("Your balance : " + data.getBalance());
				System.out.println("\nPress enter to continue");
				scan.nextLine();
				break;
			case 2 :
				System.out.println("Transfer type");
				System.out.println("1. Single");
				System.out.println("2. Multiple ( Multithread )");
				int inputTransferType = scan.nextInt();
				scan.nextLine();
				if(inputTransferType == 1) {
					System.out.println("--------------------------------------------------");
					System.out.printf("%-3s|%-15s|%-8s|%-20s|\n","No","Account","Type","Username");
					System.out.println("--------------------------------------------------");
					int index = 1;
					for(int i = 0; i < accountList.size(); i++) {
						Account datas = accountList.get(i);
						if(!datas.getAccountNumber().equals(data.getAccountNumber())) {
							System.out.printf("%-3d|%-15s|%-8s|%-20s|\n",index,datas.getAccountNumber(),datas.getAccountType(),datas.getUsername());
							index++;
						}
					}
					System.out.println("--------------------------------------------------\n");
					boolean transferLoop = true;
					String inputTransfer = "";
					while(transferLoop == true) {
						int tag = 0;
						System.out.println("Input another account to transfer");
						System.out.print(">> ");
						inputTransfer = scan.nextLine();
						int flagging = 0;
						for(int i = 0; i < accountList.size(); i++) {
							if(accountList.get(i).getAccountNumber().equals(inputTransfer)) {
								flagging = 1;
								tag = i;
								break;
							}
						}
						if(flagging == 0) {
							System.out.println("Account not found");
						} else {
							transferLoop = false;
							System.out.println("Input amount of money");
							System.out.print(">> ");
							int moneyAmount = scan.nextInt();
							scan.nextLine();
							ATMUserTransfer user1 = new ATMUserTransfer(data,moneyAmount,1,"transfer");
							user1.start();
							data.setHistory(new Date(), moneyAmount, "---");
							accountList.get(tag).setHistory(new Date(), moneyAmount, "+++");
							System.out.println("Press enter to continue...");
							scan.nextLine();
							cls();
						}
					}
				} else if(inputTransferType == 2) {
					System.out.println("--------------------------------------------------");
					System.out.printf("%-3s|%-15s|%-8s|%-20s|\n","No","Account","Type","Username");
					System.out.println("--------------------------------------------------");
					int index = 1;
					for(int i = 0; i < accountList.size(); i++) {
						Account datas = accountList.get(i);
						if(!datas.getAccountNumber().equals(data.getAccountNumber())) {
							System.out.printf("%-3d|%-15s|%-8s|%-20s|\n",index,datas.getAccountNumber(),datas.getAccountType(),datas.getUsername());
							index++;
						}	
					}
					System.out.println("--------------------------------------------------\n");
					boolean transferLoop = true;
					String inputTransfer1 = "";
					String inputTransfer2 = "";
					while(transferLoop == true) {
						int tag1 = 0;
						int tag2 = 0;
						System.out.println("Input another first account to transfer");
						System.out.print(">> ");
						inputTransfer1 = scan.nextLine();
						int flagging1 = 0;
						for(int i = 0; i < accountList.size(); i++) {
							if(accountList.get(i).getAccountNumber().equals(inputTransfer1)) {
								flagging1 = 1;
								tag1 = i;
								break;
							}
						}
						System.out.println("Input another second account to transfer");
						System.out.print(">> ");
						inputTransfer2 = scan.nextLine();
						int flagging2 = 0;
						for(int i = 0; i < accountList.size(); i++) {
							if(accountList.get(i).getAccountNumber().equals(inputTransfer2)) {
								flagging2 = 1;
								tag2 = i;
								break;
							}
						}
						if(flagging1 == 0) {
							System.out.println("First Account not found");
						} else if (flagging2 == 0){
							System.out.println("Second Account not found");
						} else {
							transferLoop = false;
							System.out.println("Input amount of money");
							System.out.print(">> ");
							int moneyAmount = scan.nextInt();
							scan.nextLine();
							ATMUserTransfer user1 = new ATMUserTransfer(data,moneyAmount,1,"transfer");
							user1.start();
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								System.out.println(e);
							}
							ATMUserTransfer user2 = new ATMUserTransfer(data,moneyAmount,1,"transfer");
							user2.start();
							try {
						        user1.join();
						        user2.join();
						    } catch (InterruptedException e) {
						        System.out.println(e);
						    }
							accountList.get(tag1).setHistory(new Date(), moneyAmount, "+++");
							accountList.get(tag2).setHistory(new Date(), moneyAmount, "+++");
							System.out.println(data.getUsername() + " Final Balance is " + data.getBalance());
							System.out.println("Press enter to continue...");
							scan.nextLine();
							cls();
						}
					}
				}
				break;
			case 3 :
				boolean withdrawMoneyLoop = true;
				int inputMoneyWithdraw = 0;
				while(withdrawMoneyLoop == true) {
					System.out.println("Input how many money you want to withdraw");
					System.out.print(">> ");
					inputMoneyWithdraw = scan.nextInt();
					scan.nextLine();
					if(inputMoneyWithdraw > 0) {
						withdrawMoneyLoop = false;
					}
				}
				if(inputMoneyWithdraw > data.getBalance()) {
					System.out.println("Not enough money to withdraw");
				} else {
					System.out.println("Successfully withdrawn");
					System.out.println("Please take the money from the atm");
					data.setHistory(new Date(), inputMoneyWithdraw,"---");
				}
				System.out.println("Press enter to continue...");
				scan.nextLine();
				cls();
				break;
			case 4 :
				if(data.getHistory().size() != 0) {
					System.out.println("------------------------------------------------------");
					System.out.printf("%-3s|%-10s|%-7s|%-30s|\n","No","Money","Status","Date");
					System.out.println("------------------------------------------------------");
					for(int i = 0; i < data.getHistory().size(); i++) {
						History datas = data.getHistory().get(i);
						System.out.printf("%-3d|%-10s|%-7s|%-30s|\n",i+1,datas.getMoney(),datas.getStatus(),datas.getTransactionDate());
					}
					System.out.println("------------------------------------------------------");

				} else {
					System.out.println("No History Data");
				}
				System.out.println("Press enter to continue...");
				scan.nextLine();
				cls();
				break;
			case 5 :
				System.out.println("Admin Menu");
				System.out.println("1. Change Username");
				System.out.println("2. Change Password");
				System.out.print(">> ");
				int inputAdmin = scan.nextInt();
				scan.nextLine();
				if(inputAdmin == 1) {
					System.out.println("Input your new username");
					String newUsername = scan.nextLine();
					data.setUsername(newUsername);
					System.out.println("Successfully changed");
					System.out.println("Press enter to continue");
					scan.nextLine();
				} else if(inputAdmin == 2) {
					System.out.println("Enter old password");
					String oldPassword = scan.nextLine();
					if(data.getPassword().equals(oldPassword)) {
						System.out.println("Enter your new password");
						String newPassword = scan.nextLine();
						data.setPassword(newPassword);
						System.out.println("Your Password Successfully Changed");
						System.out.println("Press enter to continue...");
						scan.nextLine();
						cls();
					} else {
						System.out.println("Wrong password");
						System.out.println("Press enter to continue...");
						scan.nextLine();
						cls();
					}
				} else {
					System.out.println("Input Invalid");
				}
				break;
			case 6 :
				System.out.println("Logged Out");
				System.out.println("Press enter to continue...");
				scan.nextLine();
				cls();
				flag = false;
				break;
			default :
				System.out.println("Input Invalid");
				break;
			}
		}
	}
	
    public static void cls() {
    	for(int i=0; i<32; i++) {
    		System.out.println("");
    	}
    }
}

class ATMUserTransfer extends Thread {
	Account account;
	int moneyAmount;
	int totalLoop;
	String status;
    public ATMUserTransfer(Account account, int moneyAmount, int totalLoop,String status) {
		super();
		this.account = account;
		this.moneyAmount = moneyAmount;
		this.totalLoop = totalLoop;
		this.status = status;
	}

	public void run() {
		if(status.equals("transfer")) {
	        performTransferValidation(moneyAmount);
		} else if(status.equals("withdraw")) {
			performTransferWithdraw(moneyAmount);
		}
    }

    private void performTransferValidation(int amount) {
        	try {
        		System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
        		Thread.sleep(1000);
                System.out.println("Checking balance...");
                Thread.sleep(2000);
                if(account.getBalance() - amount < 0) {
                	System.out.println("Not enough money");
                } else {
                	account.setBalance(account.getBalance() - amount);
                	System.out.println("Success");
                	System.out.println("New balance: " + account.getBalance());
                	account.setHistory(new Date(), amount, "---");
                }
        	} catch (InterruptedException e) {
        		System.out.println(e);
        	}
    }
    
    private void performTransferWithdraw(int amount) {
    	try {
    		System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
    		Thread.sleep(1000);
            System.out.println("Checking balance...");
            Thread.sleep(3000);
            if(account.getBalance() - amount < 0) {
            	System.out.println("Not enough money");
            } else {
            	account.setBalance(account.getBalance() - amount);
            	System.out.println("Success");
            	System.out.println("New balance: " + account.getBalance());
            	account.setHistory(new Date(), amount,"---");
            }
    	} catch (InterruptedException e) {
    		System.out.println(e);
    	}
    }
}
