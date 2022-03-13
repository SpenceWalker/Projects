package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;

import java.math.BigDecimal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";


    private final UserService userService = new UserService();
    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final AccountServices accountServices = new AccountServices();
    private final TransferServices transferServices = new TransferServices();


    private AuthenticatedUser currentUser;


    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }else {
            transferServices.setAuthToken(currentUser.getToken());
            userService.setAuthToken(currentUser.getToken());
            accountServices.setAuthToken(currentUser.getToken());
        }
        }


    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {

        Account getBalance = accountServices.getBalance(currentUser.getToken());
        printBalanceOrError(getBalance);
		// TODO Auto-generated method stub
	}

    private void printBalanceOrError(Account account){
        if (account != null){
            consoleService.printBalance(account);
        }else {
            consoleService.printErrorMessage();
        }
    }

	private void viewTransferHistory() {
		// TODO Auto-generated method stub
		
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub



        User[] users = userService.getListOfAllUsers();

        if (users != null){
            consoleService.printUserSelection(users);
            int userId = consoleService.promptForUserId();
            if (userId > 0){
                User user = userService.getUserById(userId);
                if (user != null){
                    consoleService.printSingleUser(user);

                } else {
                    consoleService.printErrorMessage();
                }

            }else{
                consoleService.printErrorMessage();
            }
        }


//        if (users == null){
//            consoleService.printErrorMessage();
//        }else{
//            consoleService.printAllUsers(users);
//        }
//        int userId = consoleService.promptForUserId();

        Transfer transferSent = consoleService.promptForTransferData();
        transferServices.sendTransfer(transferSent);


//        int accountFrom = accountServices.
	}

//    BigDecimal bucks = consoleService.promptForBigDecimal("Enter amount as a decimal number");

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
