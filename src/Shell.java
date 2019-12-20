import java.util.Scanner;

public class Shell {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int i;
        int headPos;
        System.out.println(">欢迎使用磁盘调度实验程序");
        System.out.println(">磁盘访问序列为："+OS.TracksToString());
        System.out.print(">请输入磁头起始位置：");
        headPos= Integer.parseInt(sc.next());
        System.out.println(">FCFS先来先服务算法");
        OS.FCFS(headPos);
        System.out.println("    走道顺序为："+OS.MessageToString());
        System.out.println("    磁头走过的总道数："+OS.getTracksNum());
        System.out.println(">SSTF最短寻道时间优先算法" );
        OS.SSTF(headPos);
        System.out.println("    走道顺序为："+OS.MessageToString());
        System.out.println("    磁头走过的总道数："+OS.getTracksNum());
        System.out.println(">SCAN扫描算法");
        System.out.println("  *磁头移动方向为向磁道号变小的方向：");
        OS.SCAN(headPos,0);
        System.out.println("    走道顺序为："+OS.MessageToString());
        System.out.println("    磁头走过的总道数："+OS.getTracksNum());
        System.out.println("  *磁头移动方向为向磁道号变大的方向：");
        OS.SCAN(headPos,1);
        System.out.println("    走道顺序为："+OS.MessageToString());
        System.out.println("    磁头走过的总道数："+OS.getTracksNum());

    }
}
