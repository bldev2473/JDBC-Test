package DAO;

import Model.Boarder;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BoarderDAO {
    FileWriter fw = null;
    BufferedWriter bw = null;
    Map<Integer, Boarder> hm = new HashMap<Integer, Boarder>();
    String msg = "";

    public void writeFile(Boarder boarder) {
        hm.put(boarder.getNum(), boarder);
        try {
            fw = new FileWriter("/Users/bldev/Documents/자바 학습/FileIO/bms.txt");
            bw = new BufferedWriter(fw);
            Iterator<Integer> it = hm.keySet().iterator();
            Integer key = 0;
            while (it.hasNext()) {
                key = it.next();
                boarder = hm.get(key);
                msg = boarder.getNum() + ", "
                        + boarder.getWriter() + ", "
                        + boarder.getSubject() + ", "
                        + boarder.getContents() + ", "
                        + boarder.getRegDate() + ", "
                        + boarder.getPw() + "\n";
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

    public Map<Integer, Boarder> readFile() {
        String msg = "";
        String[] stringArr = null;
        try {
            fr = new FileReader("/Users/bldev/Documents/자바 학습/FileIO/bms.txt");
            br = new BufferedReader(fr);
            while ((msg = br.readLine()) != null) {
                stringArr = msg.split(", ");
                Boarder boarder = new Boarder();
                boarder.setNum(Integer.parseInt(stringArr[0]));
                boarder.setWriter(stringArr[1]);
                boarder.setSubject(stringArr[2]);
                boarder.setContents(stringArr[3]);
                boarder.setRegDate(stringArr[4]);
                boarder.setPw(stringArr[5]);
                hm.put(boarder.getNum(), boarder);
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

    public void modifyMap(Boarder boarder) {
        hm.replace(boarder.getNum(), boarder);
        try {
            fw = new FileWriter("/Users/bldev/Documents/자바 학습/FileIO/bms.txt");
            bw = new BufferedWriter(fw);
            Iterator<Integer> it = hm.keySet().iterator();
            Integer key = 0;
            while (it.hasNext()) {
                key = it.next();
                boarder = hm.get(key);
                msg = boarder.getNum() + ", "
                        + boarder.getWriter() + ", "
                        + boarder.getSubject() + ", "
                        + boarder.getContents() + ", "
                        + boarder.getRegDate() + ", "
                        + boarder.getPw() + "\n";
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

    public void deleteMap(Boarder boarder) {
        hm.remove(boarder.getNum());
        try {
            fw = new FileWriter("/Users/bldev/Documents/자바 학습/FileIO/bms.txt");
            bw = new BufferedWriter(fw);
            Iterator<Integer> it = hm.keySet().iterator();
            Integer key = 0;
            while (it.hasNext()) {
                key = it.next();
                boarder = hm.get(key);
                msg = boarder.getNum() + ", "
                        + boarder.getWriter() + ", "
                        + boarder.getSubject() + ", "
                        + boarder.getContents() + ", "
                        + boarder.getRegDate() + ", "
                        + boarder.getPw() + "\n";
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
