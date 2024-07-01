/*
 * 첫 줄은 케이스 수 T
 * 다음 줄에 학생 수 N, 학생 번호 K
 * 각 학생의 중간(35%), 기말(45%), 과제(20%) 점수
 */

import java.io.*;
import java.util.*;

public class SWEA1983 {
    public static class Course {
        int nStdNum;
        int nMid, nFin, nHW;
        double dPoint;

        Course(int nStdNum, int nMid, int nFin, int nHW) {
            this.nStdNum = nStdNum;
            this.nMid = nMid;
            this.nFin = nFin;
            this.nHW = nHW;
            this.dPoint = 0.35 * (double) nMid + 0.45 * (double) nFin + 0.20 * (double) nHW;
        }
    }

    public static String gradeCheck(Course[] cStdnt, int nK) {
        int nStdIdx; // 등수
        String sGrade; // 학점
        Course cTmp;

        for (int i = 0; i < cStdnt.length; i++) { // 점수 기준으로 내림차순 버블정렬
            for (int j = 0; j < cStdnt.length - 1 - i; j++) {
                if (cStdnt[j].dPoint < cStdnt[j + 1].dPoint) {
                    cTmp = cStdnt[j];
                    cStdnt[j] = cStdnt[j + 1];
                    cStdnt[j + 1] = cTmp;
                }
            }
        }

        for (nStdIdx = 0; nStdIdx < cStdnt.length; nStdIdx++) { // 정렬된 클래스에서 학생번호로 등수 찾기
            if (cStdnt[nStdIdx].nStdNum == nK) {
                break;
            }
        }

        /*
         * String[] sGrade = {"A+", ... , "D0"};
         * return sGrade[nStdIdx];
         * 이렇게 했으면 더 짧고 보기 좋았을듯
         */

        switch (nStdIdx * 10 / cStdnt.length) {
            case 0: {
                sGrade = "A+";
                break;
            }
            case 1: {
                sGrade = "A0";
                break;
            }
            case 2: {
                sGrade = "A-";
                break;
            }
            case 3: {
                sGrade = "B+";
                break;
            }
            case 4: {
                sGrade = "B0";
                break;
            }
            case 5: {
                sGrade = "B-";
                break;
            }
            case 6: {
                sGrade = "C+";
                break;
            }
            case 7: {
                sGrade = "C0";
                break;
            }
            case 8: {
                sGrade = "C-";
                break;
            }
            default: {
                sGrade = "D0";
                break;
            }
        }

        return sGrade;
    }

    public static void main(String[] args) throws IOException {
        int nT;
        int nN, nK;
        Course[] cStdnt;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            st = new StringTokenizer(br.readLine().trim());
            nN = Integer.parseInt(st.nextToken());
            nK = Integer.parseInt(st.nextToken());
            cStdnt = new Course[nN];

            for (int stdnt = 0; stdnt < nN; stdnt++) {
                st = new StringTokenizer(br.readLine().trim());
                cStdnt[stdnt] = new Course(stdnt + 1, Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            System.out.println("#" + test + " " + gradeCheck(cStdnt, nK));
        }
    }
}
