import java.util.Scanner;

public class GeneralUser extends User{
    /**
     * 普通用户的<菜单>界面
     */
    public void menu(){
        while(true){
            System.out.print(
                "\n--- 图书借阅系统 ---\n\n"
                + "1.显示所有图书\n"
                + "2.查找图书\n"
                + "3.借阅图书\n"
                + "4.归还图书\n"
                + "5.导入数据\n"
                + "6.导出数据\n"
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
                    query();
                    break;
                
                case 3:
                    borrowBook();
                    break;
                
                case 4:
                    returnBook();
                    break;
                
                case 5:
                    importData();
                    break;

                case 6:
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
     * <借阅图书>界面
     */
    public void borrowBook(){
        System.out.println("\n--- 借阅图书 ---\n");

        Book book = find();
        if(book == null){
            return;
        }

        if(confirm("借阅")){
            if(bookshelf.fetchBook(book)){
                bookshelf.showColumns();
                book.show();
                System.out.println("\n借阅成功！");
            }
            else{
                System.out.println("\n没有库存，借阅失败！");
            }
        }
    }

    /**
     * <归还图书>界面
     */
    public void returnBook(){
        System.out.println("\n--- 归还图书 ---\n");

        Book book = find();
        if(book == null){
            return;
        }

        if(confirm("归还")){
            bookshelf.showColumns();
            book.show();
            bookshelf.putBook(book);
            System.out.println("\n归还成功！");
        }
    }
}
