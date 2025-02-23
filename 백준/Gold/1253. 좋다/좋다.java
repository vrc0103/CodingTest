import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int count;
    static int pivot;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        int countZero = 0;

        count = Integer.parseInt(br.readLine().trim());

        nums = new int[count];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < count; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if(nums[i] == 0) {
                countZero++;
            }
        }

        if(countZero == 3) {
            res++;
        }

        Arrays.sort(nums);

        for(int i = 0; i < count; i++) {
            if(nums[i] >= 0) {
                pivot = i;

                if(nums[pivot] == 0 && nums[pivot + 1] == 0) {
                    pivot++;
                }

                break;
            }
        }
    }

    static void getRes() {
        if(count < 3) {
            return;
        }

        for(int i = 0; i < count; i++) {
            comb(i);
        }
    }

    static void comb(int target) {
        if(nums[target] >= 0) {
            // 더 큰 양수 + 음수
            for(int i = target + 1; i < count; i++) {
                for(int j = pivot; j >= 0; j--) {
                    if(i == j || j == target) {
                        continue;
                    }

                    if(nums[i] + nums[j] == nums[target]) {
                        res++;
                        return;
                    }
                }
            }
            // 작은 양수 2개
            for(int i = pivot; i < target - 1; i++) {
                for(int j = i + 1; j < target; j++) {
                    if(nums[i] + nums[j] == nums[target]) {
                        res++;
                        return;
                    }
                }
            }
        } else {
            // 더 작은 음수 + 양수
            for(int i = target - 1; i >= 0; i--) {
                for(int j = pivot; j < count; j++) {
                    if(nums[i] + nums[j] == nums[target]) {
                        res++;
                        return;
                    }
                }
            }
            // 큰 음수 2개
            for(int i = target + 1; i < pivot; i++) {
                for(int j = i + 1; j <= pivot; j++) {
                    if(nums[i] + nums[j] == nums[target]) {
                        res++;
                        return;
                    }
                }
            }
        }
    }
}