package exercise.library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookServiceImplTest {
	
	private static final String HARRY_POTTER_ISBN = "ISBN-001";
	private static final String UNKNOWN_ISBN = "ISBN-9999999999999999999999";
	private static final String INVALID_PREFIX = "INVALID_TEXT";
	private static final String HARRY_POTTER_SUMMARY = "[ISBN-001] Harry Potter and the Deathly Hallows - Sorcery and Magic.";
	private static final String PLAYER_OF_GAMES_ISBN = "ISBN-002";
	private static final String PLAYER_OF_GAMES_SUMMARY = "[ISBN-002] The Player of Games - Jernau Morat Gurgeh. The Player of Games. Master of every...";
	private static final String GENIUS_ISBN = "ISBN-003";
	private static final String GENIUS_SUMMARY = "[ISBN-003] Genius: Richard Feynman and Modern Physics - A brilliant interweaving of Richard Feynman's colourful life and a...";
	private BookServiceImpl bsi;
	
	@Before
    public void setUp() {
        bsi = new BookServiceImpl();
    }

	@Test
	public void testRetrieveBook_Success() {
		try {
			assertEquals("Known ISBN not found", bsi.retrieveBook(HARRY_POTTER_ISBN).getIsbn(), HARRY_POTTER_ISBN);
			assertEquals("Known ISBN not found", bsi.retrieveBook(PLAYER_OF_GAMES_ISBN).getIsbn(), PLAYER_OF_GAMES_ISBN);
			assertEquals("Known ISBN not found", bsi.retrieveBook(GENIUS_ISBN).getIsbn(), GENIUS_ISBN);
		} catch (BookNotFoundException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRetrieveBook_BookNotFoundException() {
		try {
			Book b = bsi.retrieveBook(UNKNOWN_ISBN);
		} catch (BookNotFoundException bne) {
			assertEquals(bne.getMessage(), BookServiceImpl.BOOK_NOT_FOUND_EXCEPTION_PREFIX + UNKNOWN_ISBN);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRetrieveBook_InvalidBookPrefixException() {
		try {
			Book b = bsi.retrieveBook(INVALID_PREFIX);
		} catch (BookNotFoundException bne) {
			fail(bne.getMessage());
		} catch (Exception e) {
			assertEquals(e.getMessage(), BookServiceImpl.INVALID_ISBN_PREFIX_EXCEPTION_PREFIX + BookRepositoryImpl.ISBN_PREFIX);
		}
	}
	
	@Test
	public void testBookSummary_Success() {
		try {
			assertEquals("Known ISBN not found", bsi.getBookSummary(HARRY_POTTER_ISBN), HARRY_POTTER_SUMMARY);
			assertEquals("Known ISBN not found", bsi.getBookSummary(PLAYER_OF_GAMES_ISBN),PLAYER_OF_GAMES_SUMMARY );
			assertEquals("Known ISBN not found", bsi.getBookSummary(GENIUS_ISBN),GENIUS_SUMMARY );
		} catch (BookNotFoundException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
