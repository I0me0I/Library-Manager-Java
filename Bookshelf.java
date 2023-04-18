import java.io.Serializable;
import java.util.Vector;

public class Bookshelf implements Serializable {
    private Vector<Book> books = new Vector<Book>();

    enum Column{
        NUMBER,
        NAME,
        AUTHOR,
        CATEGORY,
        COUNT
    };

    public void addBook(Book book){
        books.addElement(book);
    }

    public void showColumns(){
        System.out.printf(
            "%s\t %s\t %s\t %s\t %s\t\n",
            "编号",
            "书名 ",
            "作者",
            "分类",
            "数量"
        );
    }

    public void showBook(int index){
        books.elementAt(index).show();
    }

    public int getBooksCount(){
        return books.size();
    }
    
    public Book findBook(int num){
        for(Book book : books){
            if(book.getNumber() == num){
                return book;
            }
        }

        return null;
    }

    public void removeBook(Book book){
        books.removeElement(book);
    }

    public Bookshelf searchBooks(Column col, int min, int max){
        Bookshelf bs = new Bookshelf();

        if(col == Column.NUMBER){
            for(Book book : books){
                if(
                    book.getNumber() >= min
                    && book.getNumber() <= max
                ){
                    bs.addBook(book);
                }
            }
        }
        else if(col == Column.COUNT){
            for(Book book : books){
                if(
                    book.getCount() >= min
                    && book.getCount() <= max
                ){
                    bs.addBook(book);
                }
            }
        }

        return bs;
    }

    public Bookshelf searchBooks(Column col, String word){
        Bookshelf bs = new Bookshelf();
        
        if(col == Column.NAME){
            for(Book book : books){
                if(book.getName().contains(word)){
                    bs.addBook(book);
                }
            }
        }
        else if(col == Column.AUTHOR){
            for(Book book : books){
                if(book.getAuthor().contains(word)){
                    bs.addBook(book);
                }
            }
        }
        else if(col == Column.CATEGORY){
            for(Book book : books){
                if(book.getCategory().contains(word)){
                    bs.addBook(book);
                }
            }
        }

        return bs;
    }

    public void modifyBook(Book book, Column col, int val){
        int index = books.indexOf(book);

        if(col == Column.NUMBER){
            books.elementAt(index).setNumber(val);
        }
        else if(col == Column.COUNT){
            books.elementAt(index).setCount(val);
        }
    }

    public void modifyBook(Book book, Column col, String val){
        int index = books.indexOf(book);

        if(col == Column.NAME){
            books.elementAt(index).setName(val);
        }
        else if(col == Column.AUTHOR){
            books.elementAt(index).setAuthor(val);
        }
        else if(col == Column.CATEGORY){
            books.elementAt(index).setCategory(val);
        }
    }

    public boolean fetchBook(Book book){
        int count = book.getCount();

        if(count > 0){
            book.setCount(count - 1);
            return true;
        }
        else{
            return false;
        }
    }

    public void putBook(Book book){
        book.setCount(book.getCount() + 1);
    }
}
