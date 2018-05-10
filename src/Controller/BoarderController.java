package Controller;

import DAO.BoarderDAO;
import Main.ProjectMain;
import Model.Boarder;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class BoarderController {
    Scanner sc = new Scanner(System.in);

    public String boardPrint() {
        String inputStr = "";
        do {
            System.out.println("===================");
            System.out.println("게시판 관리 페이지입니다.");
            System.out.println("===================");
            System.out.println("P> 게시글 등록");
            System.out.println("M> 게시글 수정");
            System.out.println("D> 게시글 삭제");
            System.out.println("S> 게시글 검색");
            System.out.println("L> 게시판 목록");
            System.out.println("Q> 메인 화면으로");
            do {
                inputStr = sc.nextLine();
                switch (inputStr) {
                    case "P":
                        postBoard();
                        break;
                    case "M":
                        modifyBoard();
                        break;
                    case "D":
                        deleteBoard();
                        break;
                    case "S":
                        break;
                    case "L":
                        listBoard();
                        break;
                    case "Q":
                        ProjectMain pm = new ProjectMain();
                        pm.mainStart();
                        break;
                    default:
                        System.out.println("올바른 값을 입력해주세요.");
                        continue;
                }
                System.out.println("홈 화면으로 가려면 Y를 입력해주세요.");
                String result = sc.nextLine();
                if (result.equals("y") || result.equals("Y")) {
                    break;
                }
            } while (true);
        } while (true);
    }

    int numCount = 0;

    BoarderDAO bdao = new BoarderDAO();

    public void postBoard() {
        Boarder boarder = new Boarder();
        System.out.println("게시글 등록");
        Scanner sc = new Scanner(System.in);
        System.out.println("작성자: ");
        String writer = sc.nextLine();
        boarder.setWriter(writer);
        System.out.println("제목: ");
        String subject = sc.nextLine();
        boarder.setSubject(subject);
        System.out.println("내용: ");
        String contents = sc.nextLine();
        boarder.setContents(contents);
        System.out.println("글 비밀번호: ");
        String pw = sc.nextLine();
        boarder.setPw(pw);
        boarder.setNum(++numCount);
        boarder.setRegDate(Calendar.getInstance().getTime().toString());
        bdao.writeFile(boarder);
        System.out.println("글 등록 완료");
    }

    public void listBoard() {
        Boarder boarder = null;
        System.out.println("게시판 목록");
        System.out.println("글번호\t작성자\t제목\t내용\t조회수\t좋아요수\t싫어요수\t등록일");
        Map<Integer, Boarder> hm = bdao.readFile();
        Iterator<Integer> it = hm.keySet().iterator();
        Integer key = 0;
        while (it.hasNext()) {
            key = it.next();
            boarder = hm.get(key);
            System.out.println(boarder.getNum() + "\t" + boarder.getWriter() + "\t" + boarder.getSubject() + "\t" + boarder.getContents()
                    + "\t" + "\t" + "\t" + "\t" + boarder.getRegDate() + "\t" + boarder.getPw());
        }
        // Map<Integer, String> hm = new Map<Integer, String>();
    }

    public void modifyBoard() {
        Boarder boarder = null;
        System.out.println("게시물 수정");
        System.out.println("수정할 글 번호를 입력하세요.");
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        Map<Integer, Boarder> map = bdao.readFile();
        boarder = map.get(num1);
        System.out.println("1. 작성자 : " + boarder.getWriter());
        System.out.println("2. 제목 : " + boarder.getSubject());
        System.out.println("3. 내용 : " + boarder.getContents());
        System.out.println("4. 등록일 : " + boarder.getRegDate());
        do {
            System.out.println("수정할 내용 번호(1~3)를 입력하세요.");
            int num2 = sc.nextInt();
            switch (num2) {
                case 1:
                    System.out.println("수정 내용을 입력하세요.");
                    String writer = sc.nextLine();
                    boarder.setWriter(writer);
                    bdao.modifyMap(boarder);
                    System.out.println("수정되었습니다.");
                    break;
                case 2:
                    System.out.println("수정 내용을 입력하세요.");
                    String subject = sc.nextLine();
                    boarder.setSubject(subject);
                    bdao.modifyMap(boarder);
                    System.out.println("수정되었습니다.");
                    break;
                case 3:
                    System.out.println("수정 내용을 입력하세요.");
                    String contents = sc.nextLine();
                    boarder.setContents(contents);
                    bdao.modifyMap(boarder);
                    System.out.println("수정되었습니다.");
                    break;
                default:
                    System.out.println("1~3 중에 하나를 입력하세요.");
                    break;
            }

            // Iterator<Integer> it = map.keySet().iterator();
            // while (it.hasNext()) {
            // int i = it.next();
            // boarder boarder1 = map.get(i);
            // System.out.println(
            // boarder1.getNum() + "\t" + boarder1.getWriter() + "\t" + boarder1.getSubject() + "\t" +
            // boarder1.getContents()
            // + "\t" + "\t" + "\t" + "\t" + boarder1.getRegistrationData() + "\t" +
            // boarder1.getPw());
            // }

            System.out.println("수정을 계속하려면 c 또는 C를, 게시글 수정을 종료하려면 q 또는 Q를 입려하세요.");
            String strForQuit = sc.nextLine();
            if (strForQuit.equals("c") || strForQuit.equals("C")) {
                continue;
            } else if (strForQuit.equals("q") || strForQuit.equals("Q")) {
                break;
            }
        } while (true);
    }

    public void deleteBoard() {
        Boarder boarder = null;
        System.out.println("게시글 삭제");
        System.out.println("삭제할 글 번호를 입력하세요.");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Map<Integer, Boarder> map = bdao.readFile();
        boarder = map.get(num);
        do {
            System.out.println("글 비밀번호를 입력하세요.");
            int pw = sc.nextInt();
            if (pw == Integer.parseInt(boarder.getPw())) {
                System.out.println("비밀번호가 일치합니다. 게시글이 삭제되었습니다.");
                bdao.deleteMap(boarder);
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }
        } while (true);
    }
}

