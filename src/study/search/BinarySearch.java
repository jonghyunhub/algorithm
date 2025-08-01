package study.search;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] input = {1, 3, 5, 5, 5};
//        int[] input = {5};
        int target = 5;
        int search = binarySearch.upperBound(input, target);
        System.out.println(search);
    }


    /**
     * 입력: [1, 3, 5, 7, 9, 11], target = 7
     * 출력: 3 (인덱스)
     * 못찾으면: -1
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
                continue;
            }
            left = mid + 1;
        }
        return -1;
    }

    /**
     * 2단계: Lower Bound
     * 문제: target 이상인 첫 번째 원소의 인덱스 찾기
     * 입력: [1, 3, 5, 5, 5, 7, 9], target = 5
     * 출력: 2 (첫 번째 5의 인덱스)
     * <p>
     * 입력: [1, 3, 7, 9], target = 5
     * 출력: 2 (5 이상인 첫 번째 값 7의 인덱스)
     */
    public int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if(arr[mid] < target){
                left = mid + 1;
                continue;
            }

            right = mid;
        }

        return left;
    }


    /**
     * 3단계: Upper Bound
     * 문제: target 초과인 첫 번째 원소의 인덱스 찾기
     * 입력: [1, 3, 5, 5, 5, 7, 9], target = 5
     * 출력: 5 (5 초과인 첫 번째 값 7의 인덱스)
     *
     * - 엣지케이스
     * int[] arr = {1, 3, 5, 5, 5};
     * int target = 5;
     * 기대값: 5 (배열 끝을 넘어감)
     */
    public int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right){
            int mid = (left + right) / 2;

            if(arr[mid] > target){
                right = mid;
                continue;
            }

            left = mid + 1;
        }

        return left;
    }


    /**
     * 4단계: 조건 만족하는 최솟값
     * 문제: "X일 동안 모든 작업을 완료할 수 있는가?"
     * 작업들: [3, 6, 7, 11] (각 작업에 필요한 시간)
     * 하루에 최대 8시간 작업 가능
     * 최소 며칠이 필요한가?
     * <p>
     * 판별함수: canFinish(days, tasks, maxHoursPerDay)
     */
    public int minDaysToFinish(int[] tasks, int maxHoursPerDay) {
        return -1;
    }

}
