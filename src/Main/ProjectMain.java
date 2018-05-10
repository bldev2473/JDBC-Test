package Main;

import Controller.BoarderController;
import Controller.MemberController;

import java.util.Scanner;

public class ProjectMain {

    public static void main(String[] args) {
        ProjectMain pm = new ProjectMain();
        pm.mainStart();
    }

    public void mainStart() {
        Scanner sc = new Scanner(System.in);
        String inputStr = "";
        do {
            System.out.println("회원 관리는 M, 게시판 관리는 B를 입력해주세요.");
            inputStr = sc.nextLine();
            switch (inputStr) {
                case "M":
                    MemberController mc = new MemberController();
                    mc.memberPrint();
                    break;
                case "B":
                    BoarderController bc = new BoarderController();
                    bc.boardPrint();
                    break;
                default:
                    System.out.println("올바른 값을 입력해주세요");
                    continue;
            }
        } while (true);

    }
}