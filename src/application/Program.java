package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		try {
			System.out.println("Enter contract data");
			System.out.print("Number: ");
			int number = sc.nextInt();
			System.out.print("Date (dd/MM/yyyy): ");
			Date date = sdf.parse(sc.next());
			System.out.print("Contract value: ");
			double totalValue = sc.nextDouble();
			
			Contract contract = new Contract(number, date, totalValue);
			
			System.out.print("Enter number of installments: ");
			int n = sc.nextInt();		
			
			ContractService contractService = new ContractService(new PaypalService());
			
			contractService.processContract(contract, n);
			
			System.out.println("Installments:");			
			for (Installment x : contract.getInstallments()) {
				System.out.println(x);
			}

		}
		catch (InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
		
		
	
	}

}
