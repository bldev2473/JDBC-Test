package Controller;

import java.util.Scanner;

import DAO.MemberDAO;
import Model.Member;

public class MemberController implements MController {
    Scanner sc = new Scanner(System.in);

    public void memberPrint() {
        String inputStr1 = null;
        String inputStr2 = null;
        do {
            System.out.println("회원 관리는 M, 게시판 관리는 B를 입력해주세요.");
            inputStr1 = sc.nextLine();
            switch (inputStr1) {
                case "M":
                    do {
                        System.out.println("회원 관리 페이지입니다.");
                        System.out.println("E> 회원 등록");
                        System.out.println("M> 회원 수정");
                        System.out.println("D> 회원 삭제");
                        System.out.println("S> 회원 검색");
                        System.out.println("L> 회원 리스트");
                        System.out.println("Q> 메인 화면으로");
                        do {
                            inputStr2 = sc.nextLine();
                            switch (inputStr2) {
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
                                    break;
                                default:
                                    System.out.println("올바른 값을 입력해주세요.");
                                    continue;
                            }
                            System.out.println("메인 화면으로 가려면 Y를 입력해주세요.");
                            String result = sc.nextLine();
                            if (result.equals("y") || result.equals("Y")) {
                                break;
                            }
                        } while (true);
                    } while (true);
                case "B":
                    break;
                default:
                    break;
            }
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
        MemberDAO mdao = new MemberDAO();
        mdao.JDBCsqlInsert(mem);
    }

    public void modifyMember() {
        MemberDAO mdao = new MemberDAO();
        String inputStr;
        System.out.println("회원 수정 페이지입니다.");
        System.out.println("아이디를 입력해주세요.");
        String id = sc.nextLine();
        do {
            System.out.println("수정할 항목을 입력해주세요.");
            String attribute = sc.nextLine();
            switch (attribute) {
                case "이름":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStr = sc.nextLine();
                    mdao.JDBCsqlUpdate(id, "member_name", inputStr);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "성별":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStr = sc.nextLine();
                    mdao.JDBCsqlUpdate(id, "member_gender", inputStr);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "생년월일":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStr = sc.nextLine();
                    mdao.JDBCsqlUpdate(id, "member_birth", inputStr);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "주소":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStr = sc.nextLine();
                    mdao.JDBCsqlUpdate(id, "member_address", inputStr);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                case "연락처":
                    System.out.println("수정할 내용을 입력해주세요.");
                    inputStr = sc.nextLine();
                    mdao.JDBCsqlUpdate(id, "member_phone_number", inputStr);
                    System.out.println("수정이 완료되었습니다.");
                    break;
                default:
                    System.out.println("올바른 값을 입력해주세요.");
                    continue;
            }
            System.out.println("메인 화면으로 가려면 Y를 입력해주세요.");
            String result = sc.nextLine();
            if (result.equals("y") || result.equals("Y")) {
                break;
            }
        } while (true);
        memberPrint();
    }

    public void deleteMember() {
        MemberDAO mdao = new MemberDAO();
        System.out.println("회원 삭제 페이지입니다.");
        System.out.println("아이디를 입력해주세요.");
        String id = sc.nextLine();
        mdao.JDBCsqlDelete(id);
        System.out.println("삭제가 완료되었습니다.");
    }

    public void searchMember() {
        MemberDAO mdao = new MemberDAO();
        System.out.println("회원 검색 페이지입니다.");
        System.out.println("아이디를 입력해주세요.");
        String id = sc.nextLine();
        mdao.JDBCsqlSelect(id);
    }

    public void listMember() {
        MemberDAO mdao = new MemberDAO();
        System.out.println("회원 목록 페이지입니다.");
        mdao.JDBCsqlSelectAll();
    }
}