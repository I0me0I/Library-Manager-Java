import java.util.Scanner;

public class Main {
    /**
     * 主函数，<图书馆管理系统>的启动界面
     * @param args 命令行参数（不使用）
     */
    public static void main(String args[]){
        while(true){
            System.out.print(
                "\n欢迎来到Java图书馆管理系统！\n\n"
                + "1.进入图书管理子系统\n"
                + "2.进入图书借阅子系统\n"
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
                    new Administrator().menu();
                    return;
                
                case 2:
                    new GeneralUser().menu();
                    return;

                case 0:
                    return;
                
                default:
                    System.out.println("无效的数字，请重新输入！");
            }
        }
    }
}
