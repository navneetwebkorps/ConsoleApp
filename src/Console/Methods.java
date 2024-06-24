gipackage Console;

import java.lang.reflect.Method;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

//thisss import org.apache.logging.log4j.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methods {
	public static List<User> Users = new ArrayList<>();
	public static List<phone> p = new ArrayList<>();
	public static String phoneRegrex = "\\s*{0}[0-9]{10}\\s*{0}";
	public static String mailRegrex = "^\\s*{0}[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-z]{2,})\\s*{0}$";
//	"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
//	+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"

	public static Scanner sc = new Scanner(System.in);

	public static void Ui() throws Exception {
		System.out.println("...........................................");
		System.out.println("Console Base App");
		System.out.println("Create User : 1");
		System.out.println("Update User : 2");
		System.out.println("Delete User : 3");
		System.out.println("Fetch Single User : 4");
		System.out.println("Fetch All User : 5");
		System.out.println("If you want to exit and come to main pannel just press 0");

		try {
			int num = sc.nextInt();
			sc.nextLine();

			if (num == 1) {

				Methods.create();
			} else if (num == 2) {
				Methods.update();
			} else if (num == 3) {
				Methods.delete();
			} else if (num == 4) {
				Methods.fetchSingleUser();
			} else if (num == 5) {
				Methods.fetchAllUser();
			} else if (num == 0) {
				System.out.println("wrong Input , Press 1 to exit");
				if (sc.nextInt() == 1) {
					Methods.Ui();
				}
			} else {
				System.out.println("Invalid Input");
				Methods.Ui();
			}

		} catch (InputMismatchException e) {

			System.out.println("Invalid Input");
			System.out.println("..............................");

			 System.out.println("wrong Input , Press any key to exit");
			try {
				sc.nextLine();
				String inp = sc.nextLine();
				 System.out.println(inp);
				if (!inp.isEmpty())
					Methods.Ui();

			} catch (InputMismatchException i) {
				System.out.println("Again Wrong input");

			}
			
		}

	}

	// .......................................................................................
	public static void create() throws Exception {
		
		try {
			
			System.out.println("Enter Name");
		
			String name=sc.nextLine();
			//System.out.println("name is "+name);
			Methods.exit(name);
			System.out.println("Enter mail");
			
			 String mail=sc.nextLine();
			// System.out.println(mail);
			Methods.exit(mail);
			boolean mailAns = Methods.StringValidate(mailRegrex, mail);
			if (mailAns == false) {
				System.out.println("Wrong mail format please re Enter Details");
				Methods.create();
				
			}
			int c = (int) Users.stream().filter(user -> user.getEmail().equals(mail)).count();
			if (c != 0) {
				System.out.println("User Already Exist");
				Thread.sleep(500);
				Methods.create();
			}
			System.out.println("Enter Phone Number");
	
			String phone = sc.nextLine();
			Methods.exit(phone);
			
			boolean phoneAns = Methods.StringValidate(phoneRegrex, phone);
			if (phoneAns == false) {
				System.out.println("Wrong phone no. format please re Enter Details");
				Methods.create();
			}
			List<String> newPhone = new ArrayList<>();
			newPhone.add(phone);
			phone ob = new phone(newPhone, mail);
			p.add(ob);
			User obj = new User(name, mail, p);
			boolean ans = Users.add(obj);
			if (ans == true) {
				System.out.println("Successfuly created");
				Thread.sleep(500);
			} else {
				System.out.println("Not Created");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
			Methods.create();
		}
		Thread.sleep(1000);

		Methods.Ui();

	}

	// .........................................................................................
	public static void update() throws Exception

	{

		try {
			
		
			System.out.println("Enter mail");
			
			String mail = sc.nextLine();
			System.out.println("mail "+mail);
			Methods.exit(mail);
			boolean mailAns = Methods.StringValidate(mailRegrex, mail);
			if (mailAns == false) {
				System.out.println("Wrong mail format please re Enter Details");
				Methods.update();
			}
			int c1 = (int) Users.stream().filter(user -> user.getEmail().equals(mail)).count();

			if (c1 == 0) {
				System.out.println("User Not Found");
				Methods.Ui();

			}

			System.out.println("Update Phone number : 1");
			System.out.println("Add another Phone number : 2");
			System.out.println("Update name : 3");

			try {
				int num = sc.nextInt();
				sc.nextLine();

				if (num == 1) {
					try {
						System.out.println("Enter Old phone Unmber");
				
						String phOld = sc.nextLine();
						Methods.exit(phOld);
						boolean phoneOldAns = Methods.StringValidate(phoneRegrex, phOld);
						if (phoneOldAns == false) {
							System.out.println("Wrong phone no. format please re Enter Details");
							Methods.update();
						}
						System.out.println("Enter phone number for update");
					
						String ph = sc.nextLine();
						Methods.exit(ph);
						boolean phoneAns = Methods.StringValidate(phoneRegrex, ph);
						if (phoneAns == false) {
							System.out.println("Wrong phone no. format please re Enter Details");
							Methods.update();
						}
					 Users.stream().filter(user -> user.getEmail().equals(mail)).findFirst().ifPresent(user -> {
							p.stream().filter(ph1 -> ph1.getMail().equals(mail)).findFirst().ifPresent(ph1 -> {
								ph1.replacePhone(phOld, ph);
								System.out.println("Phone number is modified");
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							});
						});

					}

					catch (InputMismatchException e) {
						System.out.println("Wrong phone Number format");
						System.out.println(".....................................");
						Thread.sleep(500);
						Methods.update();
					}

				} else if (num == 2) {
					try {
						System.out.println("Add Phone Number");
											String ph = sc.nextLine();
						Methods.exit(ph);
						boolean phoneAns = Methods.StringValidate(phoneRegrex, ph);
						if (phoneAns == false) {
							System.out.println("Wrong phone no. format please re Enter Details");
							Methods.update();

						}
						p.stream().filter(ph1 -> (ph1.getMail().equals(mail))).findFirst()
								.ifPresent(ph1 -> ph1.addPhone(ph));
						System.out.println("Phone number added Successfuly");
						Thread.sleep(500);
						
					} catch (InputMismatchException e) {
						System.out.println("Wrong phone number format");
						System.out.println("........................................");
						Methods.update();
					}
				} else if (num == 3) {
					System.out.println("Enter new name");
					String newName = sc.nextLine();
					Methods.exit(newName);
					Users.stream().filter(user -> user.getEmail().equals(mail)).findFirst()
							.ifPresent(user -> user.setName(newName));
					System.out.println("Name changed Successfully");
					Thread.sleep(500);
				} else {
					System.out.println("Wrong Input");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			}

			Methods.Ui();
		}

		catch (InputMismatchException e) {
			System.out.println("Invalid Input format Please try again");
			Thread.sleep(300);
			Methods.update();

		}
	}
	// ...................................................................................

	public static void delete() throws Exception {

		try {
			System.out.println("Enter mail");
			String mail = sc.nextLine();
			Methods.exit(mail);
			boolean regexAns = Methods.StringValidate(mailRegrex, mail);
			if (regexAns == false) {
				System.out.println("Wrong mail Please Re Enter");
				Methods.delete();
			}
			int c = (int) Users.stream().filter(user -> user.getEmail().equals(mail)).count();
			 if (c == 0)
				System.out.println("User Not Found");

			Users.stream().filter(user -> user.getEmail().equals(mail)).findFirst()
					.ifPresent(user -> Users.remove(user));
			p.stream().filter(pho -> pho.getMail().equals(mail)).findFirst().ifPresent(pho -> p.remove(pho));
			System.out.println("Successfully removed");
			Thread.sleep(500);

			
		} catch (InputMismatchException e) {
			System.out.println("Invalid mail");
			System.out.println("..........................................");
		}

		Methods.Ui();
	}

	// .....................................................................................
	public static void fetchSingleUser() throws Exception {
		System.out.println("Enter mail");
	
		String mail = sc.nextLine();
		Methods.exit(mail);

		boolean mailAns = Methods.StringValidate(mailRegrex, mail);
		if (mailAns == false) {
			System.out.println("Invalid input please reEnter");
			Methods.fetchSingleUser();
		}

		int[] c = { 0 };
		Users.stream().filter(user -> user.getEmail().equals(mail)).findFirst().ifPresent(user -> {
			p.stream().filter(objP -> objP.getMail().equals(mail)).findFirst().ifPresent(objP -> {
				System.out.println("Name : " + user.getName());
				System.out.println("Mail : " + user.getEmail());
				System.out.println("Phone no. : " + objP.getPhone());
				c[0]++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
		});
		try {
			if (c[0] == 0) {
				throw new UserPrincipalNotFoundException(mail);
			}
		} catch (UserPrincipalNotFoundException u) {
			System.out.println("User Not Found" + u);
			Thread.sleep(500);
		}

		Methods.Ui();
	}

	// ......................................................................................
	public static void fetchAllUser() throws Exception {

		for (User user : Users) {

			p.stream().filter(objP -> objP.getMail().equals(user.getEmail())).findFirst().ifPresent(objP -> {
				System.out.println("Name : " + user.getName());
				System.out.println("Mail : " + user.getEmail());
				System.out.println("Phone no. :  1" + objP.getPhone());
				System.out.println(".........................................");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println("Exception in thread.sleep");
				}

			});
		}

	Methods.Ui();

	}

	// ........................................................................................
	public static Boolean StringValidate(String re, String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				return false;
			}
		}
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(str);
		boolean ans = matcher.matches();
		return ans;

	}

	 public static void exit(String s) throws Exception {
		//s.trim();
		if(s.isEmpty()) {
			System.out.println("string is empty"+s);
			Methods.Ui();
		}
		char c=s.charAt(0);
		try {
		
		int i = Character.getNumericValue(c);
	
		if (i == 0) {
			Methods.Ui();
		}
		}		catch(Exception e)
		{
			System.out.println("error in exit method");
		}
		
		
		
	}
}
