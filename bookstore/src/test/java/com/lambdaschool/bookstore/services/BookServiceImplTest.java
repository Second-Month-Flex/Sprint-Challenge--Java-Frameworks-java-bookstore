package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void afindAll()
    {
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void bfindBookById()
    {

        assertEquals(26, bookService.findBookById(26).getBookid());

    }

    @Test(expected = EntityNotFoundException.class)
    public void cnotFindBookById()
    {
        assertEquals("Calling Texas Home", bookService.findBookById(99999).getBookid());

    }

    @Test
    public void ddelete()
    {
        bookService.delete(26);
        assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void save()
    {
        Section s5 = new Section("Religion");
        s5.setSectionid(21);
        Book newBook = new Book("The Adons Code", "12345423", 2009, s5);

        Book addbook = bookService.save(newBook);
        assertNotNull(addbook);

        Book foundBook = bookService.findBookById(addbook.getBookid());
        assertEquals(addbook.getTitle(), foundBook.getTitle());


    }

    @Test
    public void update()
    {
    }

    @Test
    public void deleteAll()
    {
    }
}