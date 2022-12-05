import java.util.Scanner;

public class Main {
	public static void registerMain(){
		String username,password,email,name;
		Scanner input=new Scanner(System.in);
		System.out.print("Enter UserName: ");
		username =input.nextLine();
		System.out.print("Enter Password: ");
		password=input.nextLine();
		System.out.print("Enter Email: ");
		email = input.nextLine();
		System.out.print("Enter Name: ");
		name=input.nextLine();
		User user=new User(username,password,email,name,0,true);
		SignUp signUp =new SignUp(user);
	}

	public static LoginUser loginUserMain(){
		String username,password;
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter UserName: ");
		username =scanner.nextLine();
		System.out.print("Enter Password: ");
		password=scanner.nextLine();
		LoginUser login;
		login=new LoginUser(username,password);
		return login;
	}

	public static LoginAdmin loginAdminMain(){
		String username,password;
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter UserName: ");
		username =scanner.nextLine();
		System.out.print("Enter Password: ");
		password=scanner.nextLine();
		LoginAdmin login;
		login=new LoginAdmin(username,password);
		return login;
	}

	public static boolean afterLoginUser(LoginUser login){
		System.out.println("==============Make your Choice==============");
		System.out.println("1-Show All Services\n2-Search for Service\n3-Refund\n4-LogOut");
		Scanner scanner =new Scanner(System.in);
		int choose=scanner.nextInt();
		if (choose==1){
			ServiceList serviceList=new ServiceList();
			System.out.println(serviceList);
			System.out.println("Choose?\n1-yes\n2-no");
			choose=scanner.nextInt();
			if (choose==1){
				System.out.print("Enter Service Name:");
				Scanner input=new Scanner(System.in);
				String name=input.nextLine();
				serviceList=new ServiceList(name);
				System.out.println(serviceList);
				System.out.print("Enter Provider Name:");
				String sp =input.nextLine();

			}
			return false;
		}
		else if (choose==2){
			System.out.print("Enter Service Name:");
			Scanner input=new Scanner(System.in);
			String name=input.nextLine();
			ServiceList serviceList=new ServiceList(name);
			System.out.println(serviceList);
			System.out.println("Choose?\n1-yes\n2-no");
			return false;
		}
		else if (choose==3){

		}
		else if (choose==4){
			return true;
		}
		return false;
	}

	public static boolean afterLoginAdmin(LoginAdmin login){
		if (login.getAdmin().getName().equals("admin")) {
			System.out.println("==============Make your Choice==============");
			System.out.println("1-Add New ServiceProvider\n2-Refund Requests\n3-LogOut");
			Scanner scanner = new Scanner(System.in);
			int choose = scanner.nextInt();
			if (choose == 1) {
				System.out.print("Provider Name:");
				Scanner input=new Scanner(System.in);
				String name=input.nextLine();
				System.out.print("Provider userName:");
				String username=input.nextLine();
				System.out.print("Provider password:");
				String password=input.nextLine();
				ServiceProvider serviceProvider=new ServiceProvider(name,username,password);
				AddNewProvider addNewProvider=new AddNewProvider(serviceProvider);
				return false;
			}
			else if (choose==2){
				RefundList.getRefundList();
				return false;
			}
			else if (choose==3){
				return true;
			}
		}
		else {
			System.out.println("==============Make your Choice==============");
			System.out.println("1-Add New Service\n2-Put Discount\n3-LogOut");
			Scanner scanner = new Scanner(System.in);
			int choose = scanner.nextInt();
			if (choose == 1) {
				Scanner input=new Scanner(System.in);
				System.out.print("Service Name:");
				String name=input.nextLine();
				String spName=login.getAdmin().getName();
				System.out.print("Price:");
				double price=input.nextDouble();
				System.out.print("Discount:");
				double discount=input.nextDouble();
				AddNewService addNewService=new AddNewService(name,spName,price,discount);
			}
			else if (choose==2){
				System.out.print("Enter service Name:");
				Scanner input=new Scanner(System.in);
				String name=input.nextLine();
				System.out.print("Enter Percentage:");
				Scanner input1=new Scanner(System.in);
				double dis=input1.nextDouble();
				Discount discount=new Discount(name,login.getAdmin().getName(),dis);
			}
			else if (choose==3){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		while (true) {
			System.out.println("============Welcome============\n1-Register\n2-Login");
			Scanner scanner = new Scanner(System.in);
			int choose = scanner.nextInt();
			if (choose == 1) {
				registerMain();
			}
			else if (choose == 2) {
				while (true) {
					System.out.println("1-Admin\n2-User");
					choose=scanner.nextInt();
					if (choose==1){
						LoginAdmin login=loginAdminMain();
						if (login.getAdmin()!=null) {
							System.out.println("==============Welcome " + login.getAdmin().getName() + "==============");
							while (true) {
								if(afterLoginAdmin(login)){
									break;
								}
							}
						}
						else {
							System.out.println("UserName or Password is Wrong");
						}

					}
					else if (choose==2) {
						LoginUser login = loginUserMain();
						if (login.getUser() != null) {
							System.out.println("==============Welcome " + login.getUser().getName() + "==============");
							while (true) {
								if(afterLoginUser(login)){
									break;
								}
							}
						} else {
							System.out.println("UserName or Password is Wrong");
						}
					}
				}

			}
			else {
				System.out.println("Bye :)");
				break;
			}
		}
	}

}
