import java.io.Serializable;
import java.util.Vector;

public class Bookshelf implements Serializable {
    private Vector<Book> books = new Vector<Book>(); // 保存 Book 对象

    enum Column{
        NUMBER,
        NAME,
        AUTHOR,
        CATEGORY,
        COUNT
    }; // 包含 Book 类所有属性的枚举

    /**
     * 添加 Book 对象
     * @param book 要添加的 Book 对象
     */
    public void addBook(Book book){
        books.addElement(book);
    }

    /**
     * 显示 Book 类的属性列
     */
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

    /**
     * 显示指定索引的 Book 对象的属性值
     * @param index 要指定的索引
     */
    public void showBook(int index){
        books.elementAt(index).show();
    }

    /**
     * 获取 Book 对象的数量
     * @return Book 对象的数量
     */
    public int getBooksCount(){
        return books.size();
    }
    
    /**
     * 根据 number 属性的值查找指定的 Book 对象
     * @param num 要查找的 number 属性的值
     * @return 如果找到，返回指定的 Book 对象，否则返回 null
     */
    public Book findBook(int num){
        for(Book book : books){
            if(book.getNumber() == num){
                return book;
            }
        }

        return null;
    }

    /**
     * 移除 Book 对象
     * @param book 要移除的 Book 对象
     */
    public void removeBook(Book book){
        books.removeElement(book);
    }

    /**
     * 根据指定的属性和属性值范围，查找 Book 对象
     * @param col 要指定的属性，必须为 int 类型
     * @param min 属性的最小值（包含该值）
     * @param max 属性的最大值（包含该值）
     * @return Bookshelf 对象，其中包含所有符合条件的 Book 对象
     */
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

    /**
     * 根据指定的属性和属性值范围，查找 Book 对象
     * @param col 要指定的属性，必须为 String 类型
     * @param word 属性要包含的子字符串
     * @return Bookshelf 对象，其中包含所有符合条件的 Book 对象
     */
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

    /**
     * 修改 Book 对象指定属性的属性值
     * @param book 要修改的 Book 对象
     * @param col 要指定的属性，必须为 int 类型
     * @param val 要修改为的属性值
     */
    public void modifyBook(Book book, Column col, int val){
        int index = books.indexOf(book);

        if(col == Column.NUMBER){
            books.elementAt(index).setNumber(val);
        }
        else if(col == Column.COUNT){
            books.elementAt(index).setCount(val);
        }
    }

    /**
     * 修改 Book 对象指定属性的属性值
     * @param book 要修改的 Book 对象
     * @param col 要指定的属性，必须为 String 类型
     * @param val 要修改为的属性值
     */
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

    /**
     * 将指定的 Book 对象的 count 属性值减一
     * @param book 要指定的 Book 对象
     * @return 如果 count > 0，则修改成功并返回 true，否则返回 false
     */
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

    /**
     * 将指定的 Book 对象的 count 属性值加一
     * @param book 要指定的 Book 对象
     */
    public void putBook(Book book){
        book.setCount(book.getCount() + 1);
    }
}
