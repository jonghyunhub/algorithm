package leetcode;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/3sum/description/
public class MakeSumCombinations {
    public static void main(String[] args) {
        MakeSumCombinations makeSumCombinations = new MakeSumCombinations();
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> answer = makeSumCombinations.threeSum2(nums1);
        System.out.println(answer);
    }

    // 풀이 1 : 브루트 포스 방식 <- 이렇게 하면 시간복잡도 O(N^3)으로 시간 초과 발생
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        makeCombinations(answer, new ArrayList<>(), nums, 0);
        return answer.stream().
                map(list -> list.stream().sorted().toList())
                .distinct()
                .collect(Collectors.toList());
    }

    public void makeCombinations(List<List<Integer>> answer,
                                 List<Integer> tmp,
                                 int[] origins,
                                 int now) {
        if (tmp.size() == 3) {
            int add = 0;
            for(int num : tmp){
                add += num;
            }
            if (add == 0) {
                answer.add(new ArrayList<>(tmp));
                return;
            }
        }

        for (int i = now; i < origins.length; i++) {
            tmp.add(origins[i]);
            makeCombinations(answer, tmp, origins, i+1);
            tmp.remove(tmp.size() - 1);
        }
    }

    // 풀이 2 : 정렬 + 투 포인터 방식
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);

        // 첫번째 수를 고정점으로 선택
        for (int i = 0; i < nums.length; i++) {
            // 첫번째 수 중복 건너뛰기
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 나머지 두 수를 투 포인터로 탐색
            int left = i+ 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    answer.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                }
                else if (sum < 0) {
                    /**
                     * [-4, -1, -1, 0, 1, 2]
                     *   ↑   ↑            ↑
                     *   i   L            R
                     *
                     * sum = -4 + (-1) + 2 = -3 < 0
                     * → left++ (더 큰 수 필요)
                     */
                    left++;
                } else {
                    /**
                     * [-4, -1, -1, 0, 1, 2]
                     *               ↑ ↑   ↑
                     *               i L   R
                     *
                     * sum = 0 + 1 + 2 = 3 > 0
                     * → right--
                     */
                    right--;
                }
            }
        }

        return answer.stream().
                map(list -> list.stream().sorted().toList())
                .distinct()
                .collect(Collectors.toList());
    }
}
