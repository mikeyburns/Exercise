package exercise.library;

import java.util.Scanner;

public class BookServiceImpl implements BookService {

	protected static final String BOOK_NOT_FOUND_EXCEPTION_PREFIX = "Book not in the repository - ";
	protected static final String INVALID_ISBN_PREFIX_EXCEPTION_PREFIX = "Please ensure the data entered begins with the string - ";
	@Override
	public Book retrieveBook(String isbn) throws Exception,BookNotFoundException {
		// TODO Auto-generated method stub
		if(!isbn.startsWith(BookRepositoryImpl.ISBN_PREFIX, 0)){
			throw new Exception(INVALID_ISBN_PREFIX_EXCEPTION_PREFIX + BookRepositoryImpl.ISBN_PREFIX);
		}else{

			BookRepositoryImpl bookRepImpl = new BookRepositoryImpl();
			Book book = bookRepImpl.retrieveBook(isbn);
			if(book == null){
				throw new BookNotFoundException(BOOK_NOT_FOUND_EXCEPTION_PREFIX + isbn);
			}

			return book;
		}
	}

	@Override
	public String getBookSummary(String isbn) throws Exception, BookNotFoundException {
		// TODO Auto-generated method stub
			Book book = retrieveBook(isbn);
			String summary = generateSummaryString(book);
			
			return summary;
		
	}
	private String generateSummaryString(Book book){
		String summary = "["+book.getIsbn()+"]" +" " + book.getTitle() + " - ";
		String[]arr = book.getDescription().split(" ");
		String description = "";
		if(arr.length > 10){
			for(int i =0; i<10; i++){
				description += arr[i];
				if(i != 9){
					description += " ";
				}
			}
			description +="...";
		}else{
			description = book.getDescription();
		}
		summary += description;
		return summary;
		
	}

	public static void main(String[] args) {
		/*
		 *  added a main for input testing.
		 */
		
		Scanner scannerObject = new Scanner(System.in);
		System.out.println("Press 1 to retrieve book or 2 for summary");
		boolean goodInput = false;
		int method =0;
		while(!goodInput){
			while (!scannerObject.hasNextInt()){ 
				System.out.println("Please Enter 1 or 2 to select a method");
				scannerObject.next();
			}
			method = scannerObject.nextInt();
			scannerObject.nextLine();
			if(method ==1 || method == 2){
				goodInput = true;
			}else{
				System.out.println("Please Enter 1 or 2 to select a method");
			}
		}


		System.out.println("Please enter in an ISBN");
		System.out.println("Enter 'exit' to Exit");
		BookServiceImpl bookServImpl = new BookServiceImpl();

		boolean keepGoing = true;

		while(keepGoing){
			String line = scannerObject.nextLine();
			
			try {
				if(!line.equals("exit")){
					if(method == 1){
						Book bookToPrint = bookServImpl.retrieveBook(line);
						System.out.println(bookToPrint.getTitle());
						System.out.println("Please enter another ISBN or exit");
					}else if(method ==2){
						System.out.println(bookServImpl.getBookSummary(line));
						System.out.println("Please enter another ISBN or exit");
					}
				}else{
					keepGoing = false;
				}

			} catch (BookNotFoundException e) {

				e.printStackTrace();
				System.out.println("Please enter another ISBN or exit");

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Please enter another ISBN or exit");
			} 
		}


	}




}
