package DAO;

import Model.Board;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BoardDAO {
    FileWriter fw = null;
    BufferedWriter bw = null;
    Map<Integer, Board> hm = new HashMap<Integer, Board>();
    String msg = "";

    public void writeFile(Board board) {
        hm.put(board.getNum(), board);
        try {
            fw = new FileWriter("/Users/bldev/Documents/자바 학습/FileIO/bms.txt");
            bw = new BufferedWriter(fw);
            Iterator<Integer> it = hm.keySet().iterator();
            Integer key = 0;
            while (it.hasNext()) {
                key = it.next();
                board = hm.get(key);
                msg = board.getNum() + ", "
                        + board.getWriter() + ", "
                        + board.getSubject() + ", "
                        + board.getContents() + ", "
                        + board.getRegDate() + ", "
                        + board.getPw() + "\n";
            }
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    FileReader fr = null;
    BufferedReader br = null;

    public Map<Integer, Board> readFile() {
        String msg = "";
        String[] stringArr = null;
        try {
            fr = new FileReader("/Users/bldev/Documents/자바 학습/FileIO/bms.txt");
            br = new BufferedReader(fr);
            while ((msg = br.readLine()) != null) {
                stringArr = msg.split(", ");
                Board board = new Board();
                board.setNum(Integer.parseInt(stringArr[0]));
                board.setWriter(stringArr[1]);
                board.setSubject(stringArr[2]);
                board.setContents(stringArr[3]);
                board.setRegDate(stringArr[4]);
                board.setPw(stringArr[5]);
                hm.put(board.getNum(), board);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return hm;
    }

    public void modifyMap(Board board) {
        hm.replace(board.getNum(), board);
        try {
            fw = new FileWriter("/Users/bldev/Documents/자바 학습/FileIO/bms.txt");
            bw = new BufferedWriter(fw);
            Iterator<Integer> it = hm.keySet().iterator();
            Integer key = 0;
            while (it.hasNext()) {
                key = it.next();
                board = hm.get(key);
                msg = board.getNum() + ", "
                        + board.getWriter() + ", "
                        + board.getSubject() + ", "
                        + board.getContents() + ", "
                        + board.getRegDate() + ", "
                        + board.getPw() + "\n";
            }
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteMap(Board board) {
        hm.remove(board.getNum());
        try {
            fw = new FileWriter("/Users/bldev/Documents/자바 학습/FileIO/bms.txt");
            bw = new BufferedWriter(fw);
            Iterator<Integer> it = hm.keySet().iterator();
            Integer key = 0;
            while (it.hasNext()) {
                key = it.next();
                board = hm.get(key);
                msg = board.getNum() + ", "
                        + board.getWriter() + ", "
                        + board.getSubject() + ", "
                        + board.getContents() + ", "
                        + board.getRegDate() + ", "
                        + board.getPw() + "\n";
            }
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
