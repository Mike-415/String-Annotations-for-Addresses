package Annotations;

public class AddressTest {

	public static void main(String[] args) {
		
		Address a1 = new Address("50 Phelan Ave", "Ocean Campus", "San Francisco", "CA", "94112");
		System.out.println(a1);
		Address a2 = new Address("50 Phelan Ave", "", "San Francisco", "CA", "94112");
		System.out.println(a2);
		try {
			Address a3 = new Address("50 Phelan Ave", "Ocean Campus", "San Francisco", "California", "94112");
			System.out.println(a3);
		}catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		try {
			Address a4 = new Address("", "", "San Francisco", "CA", "94112");
			System.out.println(a4);
		}catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		try {
			Address a5 = new Address("50 Phelan Ave", "", "San Francisco", "CA", "941121");
			System.out.println(a5);
		}catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}

}
