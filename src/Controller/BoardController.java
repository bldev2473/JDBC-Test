package Controller;

import DAO.BoardDAO;
import Main.ProjectMain;
import Model.Board;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class BoardController {
    Scanner sc = new Scanner(System.in);
    Board board;
    Map<Integer, Board> map;
    String inputStr;
    BoardDAO bdao = new BoardDAO();

    public String boardPrint() {
        inputStr = "";
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
                inputStr = sc.nextLine();
                if (inputStr.equals("y") || inputStr.equals("Y")) {
                    break;
                }
            } while (true);
        } while (true);
    }

    int numCount = 0;

    public void postBoard() {
        board = new Board();
        System.out.println("게시글 등록");
        Scanner sc = new Scanner(System.in);
        System.out.println("작성자: ");
        String writer = sc.nextLine();
        board.setWriter(writer);
        System.out.println("제목: ");
        String subject = sc.nextLine();
        board.setSubject(subject);
        System.out.println("내용: ");
        String contents = sc.nextLine();
        board.setContents(contents);
        System.out.println("글 비밀번호: ");
        String pw = sc.nextLine();
        board.setPw(pw);
        board.setNum(++numCount);
        board.setRegDate(Calendar.getInstance().getTime().toString());
        bdao.writeFile(board);
        System.out.println("글 등록 완료");
    }

    public void listBoard() {
        board = null;
        System.out.println("게시판 목록");
        System.out.println("글번호\t작성자\t제목\t내용\t조회수\t좋아요수\t싫어요수\t등록일");
        map = bdao.readFile();
        Iterator<Integer> it = map.keySet().iterator();
        Integer key = 0;
        while (it.hasNext()) {
            key = it.next();
            board = map.get(key);
            System.out.println(board.getNum() + "\t" + board.getWriter() + "\t" + board.getSubject() + "\t" + board.getContents()
                    + "\t" + "\t" + "\t" + "\t" + board.getRegDate() + "\t" + board.getPw());
        }
        // Map<Integer, String> hm = new Map<Integer, String>();
    }

    public void modifyBoard() {
        board = null;
        System.out.println("게시물 수정");
        System.out.println("수정할 글 번호를 입력하세요.");
        int num1 = sc.nextInt();
        map = bdao.readFile();
        board = map.get(num1);
        System.out.println("1. 작성자 : " + board.getWriter());
        System.out.println("2. 제목 : " + board.getSubject());
        System.out.println("3. 내용 : " + board.getContents());
        System.out.println("4. 등록일 : " + board.getRegDate());
        do {
            System.out.println("수정할 내용 번호(1~3)를 입력하세요.");
            int num2 = sc.nextInt();
            switch (num2) {
                case 1:
                    System.out.println("수정 내용을 입력하세요.");
                    String writer = sc.nextLine();
                    board.setWriter(writer);
                    bdao.modifyMap(board);
                    System.out.println("수정되었습니다.");
                    break;
                case 2:
                    System.out.println("수정 내용을 입력하세요.");
                    String subject = sc.nextLine();
                    board.setSubject(subject);
                    bdao.modifyMap(board);
                    System.out.println("수정되었습니다.");
                    break;
                case 3:
                    System.out.println("수정 내용을 입력하세요.");
                    String contents = sc.nextLine();
                    board.setContents(contents);
                    bdao.modifyMap(board);
                    System.out.println("수정되었습니다.");
                    break;
                default:
                    System.out.println("1~3 중에 하나를 입력하세요.");
                    break;
            }

            // Iterator<Integer> it = map.keySet().iterator();
            // while (it.hasNext()) {
            // int i = it.next();
            // board board = map.get(i);
            // System.out.println(
            // board.getNum() + "\t" + board.getWriter() + "\t" + board.getSubject() + "\t" +
            // board.getContents()
            // + "\t" + "\t" + "\t" + "\t" + board.getRegistrationData() + "\t" +
            // board.getPw());
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
        board = null;
        System.out.println("게시글 삭제");
        System.out.println("삭제할 글 번호를 입력하세요.");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        map = bdao.readFile();
        board = map.get(num);
        do {
            System.out.println("글 비밀번호를 입력하세요.");
            int pw = sc.nextInt();
            if (pw == Integer.parseInt(board.getPw())) {
                System.out.println("비밀번호가 일치합니다. 게시글이 삭제되었습니다.");
                bdao.deleteMap(board);
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }
        } while (true);
    }
}

