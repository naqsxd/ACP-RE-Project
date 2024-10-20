
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        PostController postController = new PostController();

        int isLoggedIn = -1; // Could have made it a boolean, but if its an int we can use it to check if the user is a client or an admin by the value it returns
    
        System.out.println("Welcome to Real Estate Application");
    
        while (isLoggedIn == -1) {
            System.out.println("Please enter a number to proceed:\n- Register (1)\n- Login (2)\n- Exit (0)");
            int choice = getValidInput(scanner, new int[]{0, 1, 2});
            
            switch (choice) {

                case 1:
                    userController.registerUser(scanner);
                    break;

                case 2:
                    int userId = handleLogin(scanner, userController);
                    if (userId >= 1) {
                        isLoggedIn = userId;
                        ClientView.showClientMenu();
                        postController.handleClient(userId);
                        break;
                    } 
                    // else if (isLoggedIn == 2) {
                    //     // AdminView.showAdminMenu();
                    //     // break;
                    // }
                    
                    isLoggedIn = -1;
                    break;

                case 0:
                    System.out.println("Exiting application. Thank you!");
                    scanner.close();
                    return;  // Exit the application
            }
        }
    }  
    