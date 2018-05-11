package Controller;

import java.util.Scanner;

import DAO.MemberDAO;
import Main.ProjectMain;
import Model.Member;

public class MemberController implements MController {
    Scanner sc = new Scanner(System.in);
    private MemberDAO mdao;
    private String inputStr;
    private String inputId;
    private String inputPw;

    public void memberPrint() {
        do {
            System.out.println("==================");
            System.out.println("회원 관리 페이지입니다.");
            System.out.println("==================");
            System.out.println("E> 회원 등록");
            System.out.println("M> 회원 수정");
            System.out.println("D> 회원 삭제");
            System.out.println("S> 회원 검색");
            System.out.println("L> 회원 리스트");
            System.out.println("Q> 메인 화면으로");
            do {
                inputStr = sc.nextLine();
                switch (inputStr) {
                    case "E":
                        enrollMember();
                        break;
                    case "M":
                        modifyMember();
                        break;
                    case "D":
                        deleteMember();
                        break;
                    case "S":
                        searchMember();
                        break;
                    case "L":
                        listMember();
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

    public void enrollMember() {
        Member mem = new Member();
        System.out.println("사용하실 아이디를 입력해주세요.");
        mem.setId(sc.nextLine());
        System.out.println("사용하실 비밀번호를 입력해주세요.");
        mem.setPw(sc.nextLine());
        System.out.println("이름을 입력하세요.");
        mem.setName(sc.nextLine());
        System.out.println("성별을 입력하세요. (예: 남 또는 여)");
        mem.setGender(sc.nextLine());
        System.out.println("생년월일을 입력하세요. (예: 940623)");
        mem.setBirth(sc.nextLine());
        System.out.println("주소를 입력하세요.");
        mem.setAddress(sc.nextLine());
        System.out.println("연락처를 입력하세요. (예: 010-1234-5678)");
        mem.setPhone_number(sc.nextLine());
        System.out.println("회원 등록이 완료되었습니다.");
        mdao = new MemberDAO();
        mdao.JDBCsqlInsert(mem);
    }

    public void modifyMember() {
        mdao = new MemberDAO();
        String inputStrToEdit;
        System.out.println("회원 수정 페이지입니다.");
        System.out.println("아이디를 입력해주세요.");
        inputId = sc.nextLine();
        do {
            System.out.println("비밀번호를 입력해주세요.");
            inputPw = sc.nextLine();
            if (mdao.JDBCsqlSelectPwWhereId(inputId).equals(inputPw)) {
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }
        } while (true);

        do {
            System.out.println("수정할 항목을 입력해주세요.");
            String attribute = sc.nextLine();
            switch (attribute) {
                case "비밀번호":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStrToEdit = sc.nextLine();
                    mdao.JDBCsqlUpdate(inputId, "member_pw", inputStrToEdit);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "이름":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStrToEdit = sc.nextLine();
                    mdao.JDBCsqlUpdate(inputId, "member_name", inputStrToEdit);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "성별":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStrToEdit = sc.nextLine();
                    mdao.JDBCsqlUpdate(inputId, "member_gender", inputStrToEdit);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "생년월일":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStrToEdit = sc.nextLine();
                    mdao.JDBCsqlUpdate(inputId, "member_birth", inputStrToEdit);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "주소":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStrToEdit = sc.nextLine();
                    mdao.JDBCsqlUpdate(inputId, "member_address", inputStrToEdit);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "연락처":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStrToEdit = sc.nextLine();
                    mdao.JDBCsqlUpdate(inputId, "member_phone_number", inputStrToEdit);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                default:
                    System.out.println("올바른 값을 입력해주세요.");
                    continue;
            }
            System.out.println("메인 화면으로 가려면 Y를 입력해주세요.");
            inputStr = sc.nextLine();
            if (inputStr.equals("y") || inputStr.equals("Y")) {
                break;
            }
        } while (true);
        memberPrint();
    }

    public void deleteMember() {
        mdao = new MemberDAO();
        System.out.println("회원 삭제 페이지입니다.");
        System.out.println("아이디를 입력해주세요.");
        inputId = sc.nextLine();
        mdao.JDBCsqlDelete(inputId);
        System.out.println("삭제가 완료되었습니다.");
    }

    public void searchMember() {
        mdao = new MemberDAO();
        System.out.println("회원 검색 페이지입니다.");
        System.out.println("아이디를 입력해주세요.");
        inputId = sc.nextLine();
        mdao.JDBCsqlSelectAllWhereId(inputId);
    }

    public void listMember() {
        mdao = new MemberDAO();
        System.out.println("회원 목록 페이지입니다.");
        mdao.JDBCsqlSelectAll();
    }
}