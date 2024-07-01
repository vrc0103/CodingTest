import java.io.*;

public class SWEA1213 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        int nTest;
        int nCount;
        String sWord, sString;

        for (int tc = 1; tc <= 10; tc++) {
            nCount = 0;
            sb = new StringBuilder();

            nTest = Integer.parseInt(br.readLine());
            sWord = br.readLine();
            sString = br.readLine();

            for (int idx = 0; idx < sString.length() - sWord.length() + 1; idx++) { // 문자열에서 검색할 단어 찾기
                if (sString.charAt(idx) == sWord.charAt(0)) {// 첫 글자와 일치하는 글자를 찾으면
                    for (int idxWord = 0; idxWord < sWord.length(); idxWord++) {
                        if (sString.charAt(idx + idxWord) != sWord.charAt(idxWord)) {// 단어의 각 글자 비교
                            break;
                        }
                        if (idxWord == sWord.length() - 1) { // 찾을 단어와 일치하면 nCount 1증가
                            nCount++;
                        }
                    }
                }
            }

            sb.append("#").append(nTest).append(" ").append(nCount);
            System.out.println(sb);
        }
    }
}
