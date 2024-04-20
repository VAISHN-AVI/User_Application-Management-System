package jdbc_user;

import java.util.Scanner;

public class UserMain {
	Scanner scanner = new Scanner(System.in);
	System.out.println("Welcome !!! \n1.Sign Up \n2.Login \n3.Fetch \n4.Exit");
	int choice = scanner.nextInt();
	User user = new User();

	UserCRUD crud = new UserCRUD();

	switch(choice)
	{
	case 1:
	{
		System.out.println("Enter the person id ");
		int id = scanner.nextInt();
		System.out.println("Enter the person name ");
		String name = scanner.next();
		System.out.println("Enter the person phone ");
		long phone = scanner.nextLong();
		System.out.println("Enter the person email ");
		String email = scanner.next();
		System.out.println("Enter the person password ");
		String password = scanner.next();

		user.setId(id);
		user.setName(name);
		user.setPhone(phone);
		user.setEmail(email);
		user.setPassword(password);
	
		int count = crud.signUpUser(user);
		if (count != 0) 
		{
			System.out.println("SignUp Successfully");
		} 
		else {
			System.out.println("Failed to sign Up");
		}
	}
	break;
	
	case 2: 
	{
		System.out.println("Enter the person email ");
		String email = scanner.next();

		System.out.println("Enter the person password ");
		String password = scanner.next();

		String dbPassword = crud.getPassword(email);

		if (dbPassword != null) {
			if (dbPassword.equals(password)) {
				System.out.println("Login successsfully");
				System.out.println(" Enter your choice \n1.Display info  \n2.Changr password \n3.Logout");
				System.out.println("Enter your choice");
				switch (scanner.nextInt()) {
				case 1: {
					User user1 = crud.dispalyPerson(email);
					System.out.println("ID:" + user1.getId());
					System.out.println("Name:" + user1.getName());
					System.out.println("Phone:" + user1.getPhone());
					System.out.println("Password:" + user1.getPassword());
					System.out.println("facebook:" + user1.getFacebook());
					System.out.println("instagram:" + user1.getInstagram());
					System.out.println("snapchat:" + user1.getSnapchat());
					System.out.println("twitter:" + user1.getTwitter());
				}
				break;
				case 2: {
					System.out.println("Enter the new password");
					String newPassword = scanner.next();

					int result = crud.updatePassword(email, newPassword);
					if (result != 0) {
						System.out.println("Password Updated successfully");
					} else {
						System.out.println("Failed to update password");
					}
				}
				break;
				case 3: {
					System.out.println("if you want to exit enter 1");
			         int choice11=scanner.nextInt();

					if(choice11==1) {
						System.out.println("exit successfully");
					}
					else if(choice11!=1) 
					{
						System.out.println("unable to exit");
					crud.exitUser();
					break;
				   }
				}
					
				break;
				default:
					break;
				}

			}
			else {
				System.out.println("Invalid Password");
			}
		} 
		else
		{
			System.out.println("Person not rgistered with the given email:" + email);
		}

	}
	
	break; 
	
	}

	}
	

}
