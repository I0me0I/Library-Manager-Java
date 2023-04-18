import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Book implements Serializable{
    private int number;
    private String name;
    private String author;
    private String category;
    private int count;

    public boolean setAll(){
        Scanner scan = new Scanner(System.in);

        try{
            System.out.print("编号：");
            number = scan.nextInt();
            System.out.print("书名：");
            name = scan.next();
            System.out.print("作者：");
            author = scan.next();
            System.out.print("分类：");
            category = scan.next();
            System.out.print("数量：");
            count = scan.nextInt();
        }
        catch(InputMismatchException e){
            System.out.print("输入不匹配，");
            return false;
        }

        return true;
    }

    public void show(){
        System.out.printf(
            "%d\t %s\t %s\t %s\t %d\t\n",
            number,
            name,
            author,
            category,
            count
        );
    }

    public int getNumber(){
        return number;
    }

    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public String getCategory(){
        return category;
    }

    public int getCount(){
        return count;
    }

    public void setNumber(int num){
        number = num;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAuthor(String auth){
        author = auth;
    }

    public void setCategory(String cate){
        category = cate;
    }

    public void setCount(int count){
        this.count = count;
    }
}
