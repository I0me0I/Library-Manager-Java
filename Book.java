import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Book implements Serializable{
    private int number; // 编号
    private String name; // 名字
    private String author; // 作者
    private String category; // 分类
    private int count; // 数量

    /**
     * 根据输入的值设置 Book 类的所有属性
     * @return 如果成功，返回 true，否则返回 false
     */
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
    
    /**
     * 显示 Book 类的所有属性
     */
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

    /**
     * 获取 number 属性的值
     * @return number 属性的值
     */
    public int getNumber(){
        return number;
    }

    /**
     * 获取 name 属性的值
     * @return name 属性的值
     */
    public String getName(){
        return name;
    }

    /**
     * 获取 author 属性的值
     * @return author 属性的值
     */
    public String getAuthor(){
        return author;
    }

    /**
     * 获取 category 属性的值
     * @return category 属性的值
     */
    public String getCategory(){
        return category;
    }

    /**
     * 获取 count 属性的值
     * @return count 属性的值
     */
    public int getCount(){
        return count;
    }

    /**
     * 设置 number 属性的值
     * @param num number 属性的值
     */
    public void setNumber(int num){
        number = num;
    }

    /**
     * 设置 name 属性的值
     * @param name name 属性的值
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * 设置 author 属性的值
     * @param auth author 属性的值
     */
    public void setAuthor(String auth){
        author = auth;
    }

    /**
     * 设置 category 属性的值
     * @param cate category 属性的值
     */
    public void setCategory(String cate){
        category = cate;
    }

    /**
     * 设置 count 属性的值
     * @param count count 属性的值
     */
    public void setCount(int count){
        this.count = count;
    }
}
