import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class User {
    protected Bookshelf bookshelf = new Bookshelf(); // 用于保存 Book 对象
    private final int ROWS_PER_PAGE = 10; // 每页显示的属性行数

    /**
     * 用户的<菜单>界面
     */
    public abstract void menu();
    
    /**
     * 根据输入的 number 属性值查找指定的 Book 对象
     * @return 如果找到，返回指定的 Book 对象，并显示其属性值，否则返回 null
     */
    protected Book find(){
        System.out.print("请输入图书的编号：");

        Scanner scan = new Scanner(System.in);
        if(!scan.hasNextInt()){
            System.out.println("输入无效，已返回！");
            return null;
        }
        Book book = bookshelf.findBook(scan.nextInt());

        if(book == null){
            System.out.println("没有此编号的书籍！");
            return null;
        }

        bookshelf.showColumns();
        book.show();
        
        return book;
    }

    /**
     * 根据输入，确定是否执行指定的动作
     * @param act 要指定的动作
     * @return 如果输入为 1，则返回 true，其他情况均返回 false
     */
    protected boolean confirm(String act){
        System.out.print(
            "\n是否确定" + act + "？\n" +
            "1.是\n" +
            "2.否\n\n" +
            "请选择："
        );

        Scanner scan = new Scanner(System.in);
        if(!scan.hasNextInt()){
            System.out.println("输入无效，已返回！");
            return false;
        }
        int key = scan.nextInt();

        if(key == 1){
            return true;
        }
        else if(key == 2){
            System.out.println("已取消" + act + "。");
            return false;
        }
        else{
            System.out.println("无效的数字，已返回！");
            return false;
        }
    }

    /**
     * <显示所有图书>界面
     */
    public void show(){
        int page = 1;
        int count = bookshelf.getBooksCount();

        while(true){
            System.out.println("\n--- 图书目录 ---\n");
            bookshelf.showColumns();
            for(int i = 0; i < ROWS_PER_PAGE; i++){
                int index = (page - 1) * ROWS_PER_PAGE + i;
                if(index >= count)
                    break;
                bookshelf.showBook(index);
            }
            System.out.print(
                "\n1.上一页\n" +
                "2.下一页\n" +
                "0.返回\n\n" +
                "请选择："
            );

            Scanner scan = new Scanner(System.in);
            if(!scan.hasNextInt()){
                System.out.println("请输入数字！");
                continue;
            }
            int key = scan.nextInt();

            switch(key){
                case 1:
                    if(page > 1){
                        page--;
                    }
                    else{
                        System.out.println("已经是第一页！");
                    }
                    break;

                case 2:
                    if(page * ROWS_PER_PAGE < count){
                        page++;
                    }
                    else{
                        System.out.println("已经是最后一页！");
                    }
                    break;

                case 0:
                    return;
                
                default:
                System.out.println("无效的数字，请重新输入！");
            }
        }
    }

    /**
     * <查找图书>界面
     */
    public void query(){
        System.out.print(
            "\n--- 查找图书 ---\n\n"
            + "选择查找的属性\n"
            + "1.编号\n"
            + "2.书名\n"
            + "3.作者\n"
            + "4.分类\n"
            + "5.数量\n"
            + "0.返回\n\n"
            + "请选择："
        );
        
        Scanner scan = new Scanner(System.in);
        if(!scan.hasNextInt()){
            System.out.println("输入无效，已返回！");
            return;
        }
        int key = scan.nextInt();
        
        switch(key){
            case 1:
            case 5:
                try{
                    System.out.print(
                        "\n请输入范围\n" +
                        "最小值："
                    );
                    int min = scan.nextInt();
                    System.out.print("最大值：");
                    int max = scan.nextInt();

                    Bookshelf tmp = bookshelf;
                    bookshelf = bookshelf.searchBooks(
                        Bookshelf.Column.values()[key - 1],
                        min,
                        max
                    );
                   show();
                   bookshelf = tmp;
                }
                catch(InputMismatchException e){
                    System.out.println("输入无效，已返回！");
                }
                break;

            case 2:
            case 3:
            case 4:
                {
                    System.out.print("\n请输入关键字：");
                    String word = scan.next();

                    Bookshelf tmp = bookshelf;
                    bookshelf = bookshelf.searchBooks(
                        Bookshelf.Column.values()[key - 1],
                        word
                    );
                    show();
                    bookshelf = tmp;
                }
                break;
            
            case 0:
                return;
            
            default:
                System.out.println("无效的数字，已返回！");
        }
    }

    /**
     * <导入数据>界面
     */
    public void importData(){
        System.out.print(
            "\n--- 导入数据 ---\n\n" +
            "请输入文件名："
        );

        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();

        FileInputStream fis;
        ObjectInputStream ois;

        try{
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            bookshelf = (Bookshelf)ois.readObject();
            System.out.println("导入成功！");
        }
        catch(FileNotFoundException e){
            System.out.println("未找到文件！");
        }
        catch(Exception e){   
            System.out.println(e.toString() + "\n发生错误，导入失败！");
        }
    }

    /**
     * <导出数据>界面
     */
    public void exportData(){
        System.out.print(
            "\n--- 导出数据 ---\n\n" +
            "请输入文件名："
        );

        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();

        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(bookshelf);
            System.out.println("导出成功！");
        }
        catch(Exception e){   
            System.out.println(e.toString() + "\n发生错误，导出失败！");
        }
    }
}
