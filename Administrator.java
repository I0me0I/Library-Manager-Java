import java.util.Scanner;

public class Administrator extends User {

    /**
     * 管理员的<菜单>界面
     */
    public void menu(){
        while(true){
            System.out.print(
                "\n--- 图书管理系统 ---\n\n"
                + "1.显示所有图书\n"
                + "2.添加图书\n"
                + "3.删除图书\n"
                + "4.查找图书\n"
                + "5.修改图书\n"
                + "6.导入数据\n"
                + "7.导出数据\n"
                + "0.退出\n\n"
                + "请选择："
            );

            Scanner scan = new Scanner(System.in);
            if(!scan.hasNextInt()){
                System.out.println("请输入数字！");
                continue;
            }
            int key = scan.nextInt();

            switch(key){
                case 1:
                    show();
                    break;
                
                case 2:
                    add();
                    break;
                
                case 3:
                    del();
                    break;
                
                case 4:
                    query();
                    break;
                
                case 5:
                    mod();
                    break;
                
                case 6:
                    importData();
                    break;
                
                case 7:
                    exportData();
                    break;

                case 0:
                    return;
                
                default:
                    System.out.println("无效的数字，请重新输入！");
            }
        }
    }

    /**
     * <添加图书>界面
     */
    public void add(){
        System.out.println(
            "\n--- 添加图书 ---\n" +
            "请输入以下信息"
        );

        Book book = new Book();

        if(book.setAll()){
            if(bookshelf.findBook(book.getNumber()) != null){
                System.out.println("编号重复，添加失败！");
            }
            else{
                bookshelf.addBook(book);
                System.out.println("添加成功！");
            }

        }
        else{
            System.out.println("添加失败！");
        }
    }

    /**
     * <删除图书>界面
     */
    public void del(){
        System.out.println("\n--- 删除图书 ---\n");

        Book book = find();
        if(book == null){
            return;
        }

        if(confirm("删除")){
            bookshelf.removeBook(book);
            System.out.println("删除成功！");
        }
    }

    /**
     * <修改图书>界面
     */
    public void mod(){
        System.out.println("\n--- 修改图书 ---\n");

        Book book = find();
        if(book == null){
            return;
        }

        System.out.print(
            "\n选择修改的属性\n"
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
                System.out.print("\n请输入修改后的值：");
                if(!scan.hasNextInt()){
                    System.out.println("输入无效，已返回！");
                    return;
                }
                bookshelf.modifyBook(
                    book,
                    Bookshelf.Column.values()[key - 1],
                    scan.nextInt()
                );
                break;

            case 2:
            case 3:
            case 4:
                System.out.print("\n请输入修改后的值：");
                bookshelf.modifyBook(
                    book,
                    Bookshelf.Column.values()[key - 1],
                    scan.next()
                );
                break;
            
            case 0:
                return;
            
            default:
                System.out.println("无效的数字，已返回！");
                return;
        }

        bookshelf.showColumns();
        book.show();
        System.out.println("\n修改完成！");
    }

}
