package com.supermarket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Date :June 16,2018 This file contains three function of SuperMarker
 * Application.
 * 
 * @author MukulJaiswal.
 * @version 1.0
 */

public class Item {

	Scanner scanner = new Scanner(System.in);

	/**
	 * This function list all items available in SuperMarket.
	 * 
	 * @return Nothing.
	 * @throws IOException
	 */
	void listAllItems() throws IOException {
		File file = new File("items.txt");

		if (file.length() == 0)
			System.out.println("Sorry ! No item in the Store.");
		else {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String lineRead = null;
			System.out.println("ID" + "  " + "Name" + "  " + "Quantity" + "  " + "Date");
			while ((lineRead = bufferedReader.readLine()) != null) {
				JOptionPane.showMessageDialog(null, lineRead);
				System.out.println(lineRead);
			}
			bufferedReader.close();
		}
	}

	/**
	 * This function is used to add new items in a Store.
	 * 
	 * @return Nothing.
	 * @throws IOException
	 */

	void addNewItems() throws IOException {

		// Primary Key in a File
		int Id = 0;
		char choice;
		String name;
		int quantity;
		String date;
		int flag = 0;

		FileWriter fstream = null;
		BufferedWriter out = null;

		File file = new File("items.txt");
		file.createNewFile();

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String lineRead = null;
		
		while ((lineRead = bufferedReader.readLine()) != null) {
			String splitByLine[] = lineRead.split("\n");
			char firstCharacter = (splitByLine[splitByLine.length - 1]).charAt(0);
			Id = Character.getNumericValue(firstCharacter);
		}

		do {
			System.out.println("Enter the details of an items: ");

			System.out.print("Name: ");
			name = scanner.nextLine();

			System.out.print("Quantity :");
			quantity = scanner.nextInt();

			scanner.nextLine();

			System.out.print("Date :");
			date = scanner.nextLine();

			try {
				if (Id == 0) {
					fstream = new FileWriter("items.txt", true);
					out = new BufferedWriter(fstream);
					out.write(++Id + "	" + name + "	" + quantity + "	" + date + "\n");
					System.out.println("Item is Successfully Added.");

				} else {
					fstream = new FileWriter("items.txt", true);
					out = new BufferedWriter(fstream);
					out.write(++Id + "	" + name + "	" + quantity + "	" + date + "\n");
					System.out.println("Item is Successfully Added.");
				}

				System.out.println("Do you want to add more Items:(Y/N)");
				choice = scanner.next().charAt(0);

				scanner.nextLine();

				if (choice == 'Y' || choice == 'y') {
					flag = 1;
				} else if (choice == 'N' || choice == 'n') {
					flag = 0;
				} else {
					System.out.println("Wrong Choice !");
				}
			} catch (Exception exception) {
				System.err.println("Error while writing to file: " + exception.getMessage());
			} finally {
				out.close();
				bufferedReader.close();
			}

		} while (flag == 1);

	}

	/**
	 * This function removes the selected item in the list.
	 * 
	 * @throws IOException
	 * @see IOException
	 */
	void removeItems() {
		String removeTerm;
		String filepath = "items.txt";

		System.out.print("Enter the Product ID which you want to delete :");
		removeTerm = scanner.nextLine();

		removeRecord(filepath, removeTerm);
	}

	/**
	 * This function removes the selected Selected record from the file.
	 * 
	 * @param filepath
	 *            It saves the name of the file.
	 * @param removeTerm
	 *            It saves the item which is to be removed.
	 * @return Nothing
	 */
	private static void removeRecord(String filepath, String removeTerm) {

		Scanner scanner = null;

		String tempFile = "temp.txt";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		PrintWriter printWriter = null;

		String id = "", name = "", quantity = "", date = "";
		try {
			fileWriter = new FileWriter(tempFile, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			scanner = new Scanner(new File(filepath));

			// Using Delimiter for File searching
			scanner.useDelimiter("[\n	]");

			while (scanner.hasNext()) {
				id = scanner.next();
				name = scanner.next();
				quantity = scanner.next();
				date = scanner.next();

				if (!id.equalsIgnoreCase(removeTerm)) {
					printWriter.println(id + "	" + name + "	" + quantity + "	" + date);
				} else {
					System.out.println("Item is Removes Successfully");
				}
			}
		} catch (Exception exception) {
			System.out.println(exception);
		} finally {
			scanner.close();
			printWriter.flush();
			printWriter.close();
			oldFile.delete();
		}
		File dump = new File(filepath);
		// Renaming temporary file back to original file
		newFile.renameTo(dump);
	}
}
