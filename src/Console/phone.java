package Console;

import java.util.ArrayList;
import java.util.List;

public class phone {
	private List<String> Phone=new ArrayList<>();
	private String mail;
	@Override
	public String toString() {
		return "phone [phone=" + Phone + "]";
	}

	
	public phone(List<String> phone, String mail) {
		super();
		this.Phone = phone;
		this.mail = mail;
	}


	public phone() {
		// TODO Auto-generated constructor stub
	}

	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public List<String> getPhone() {
		return Phone;
	}
	
	public  void setPhone(List<String> phone) {
		this.Phone = phone;
	}
	public void replacePhone(String ph1,String ph2)
	{
		int i=0;
		for(String str :Phone)
		{
			if(str.equals(ph1))
			{
				
				Phone.set(i, ph2);
				System.out.println("SuccessFully replaced");
			}
			i++;
		}
	}


	public void addPhone(String ph) {
		// TODO Auto-generated method stub
		Phone.add(ph);
		
	}

}
