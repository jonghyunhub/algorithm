package programmers.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/77486
public class NetworkMarketing {

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        NetworkMarketing networkMarketing = new NetworkMarketing();
        int[] solution = networkMarketing.solution2(enroll, referral, seller, amount);
        for (int i : solution) {
            System.out.print(i + " ");
        }
    }

    // 트리를 순회하면서 이익을 계산 => 이렇게 계산하면 상납금이 뭉개지는 경우가 존재
    // ex) 250, 350 => 2 + 3 = 5원 상납 / 250 + 350 = 600 => 6원상납
    public int[] solution1(String[] enroll, String[] referral, String[] seller, int[] amount) {
        final Map<String, List<String>> sellerTree = new HashMap<>(); // 판매원들 부모,자식 트리
        final Map<String, List<Integer>> sellerAmount = new HashMap<>(); // 각 판매원이 직접 판매한 값 매핑
        final Map<String, Integer> sellerProfits = new HashMap<>();// 판매원들의 총 이익 계산 매핑
        List<Integer> answer = new ArrayList<>();

        // enroll, referral을 입력받아서 트리를 인접리스트로 표현한다.
        for (int i = 0; i < enroll.length; i++) {
            List<String> children = sellerTree.getOrDefault(referral[i], new ArrayList<>());
            children.add(enroll[i]);
            sellerTree.put(referral[i], children);
        }

        // 각 판매원들의 판매금액을 이름 - 판매금액으로 매핑(이름으로 판매금액 찾기 위해)
        for (int i = 0; i < seller.length; i++) {
            // 이미 있는 이름이면 리스트에 더해준다.
            List<Integer> alreadyAmounts = sellerAmount.getOrDefault(seller[i], new ArrayList<>());
            alreadyAmounts.add(amount[i] * 100);
            sellerAmount.put(seller[i], alreadyAmounts);
        }

        String center = "-";
        calChildrenProfits(center,
                sellerTree,
                sellerAmount,
                sellerProfits);

        // 마지막 정답 처리
        for (int i = 0; i < enroll.length; i++) {
            answer.add(sellerProfits.get(enroll[i]));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    // 자식들을 순회하면서 이익의 합 계산
    public int calChildrenProfits(String now,
                                  Map<String, List<String>> tree,
                                  Map<String, List<Integer>> sellerAmount,
                                  Map<String, Integer> sellerProfits) {
        // 본인이 직접 판매하여 구한 수익금 계산
        // 1. 현재 판매원이 부모에게 보내는 상납금 => (int) 판매금액 * 0.1
        // 2. 현재 판매원이 판매한 이익을 계산 => 판매금액 - 상납금
        double parentMoneyRate = now.equals("-") ? 0.0 : 0.1; // 부모에게 보내는 상납금 비율, 제일 맨 위 판매원은 상납금 없이 100% 다 먹음
        int parentMoney = 0;
        int nowAmountProfit = 0;
        List<Integer> orDefault = sellerAmount.getOrDefault(now, new ArrayList<>());// 직접 팔아서 번돈
        for (int nowAmount : orDefault) {
            parentMoney += (int) (nowAmount * parentMoneyRate); // 상납금
            nowAmountProfit += nowAmount - parentMoney; // 현재 셀러가 먹는돈
        }

        // tree의 key로 현재 이름이 포함되어있지 않으면 자식이 없다는 의미 -> 상납금 리턴
        // 본인이 직접 벌어서 번 돈 현재 노드의 이익으로 저장
        if (!tree.containsKey(now)) {
            sellerProfits.put(now, nowAmountProfit);
            return parentMoney;
        }

        // 자식이 있으면 자식들이 보내는 상납금을 모두 합해서 현재 이익으로 처리
        List<String> children = tree.get(now);
        for (String child : children) {
            int childrenMoney = calChildrenProfits(child,
                    tree,
                    sellerAmount,
                    sellerProfits);
            int parentCharge = (int) (childrenMoney * parentMoneyRate); // 자식이 준 돈 부모 수수료 처리
            parentMoney += parentCharge;
            nowAmountProfit += (childrenMoney - parentCharge);
        }

        // 현재 seller의 최종 이익을 저장후 상납금 리턴
        sellerProfits.put(now, nowAmountProfit);
        return parentMoney;
    }


    // sellerAmount를 순회하면서 각 판매원이 판매한 금액이 발생할 때 마다 계산
    // 트리의 반대 방향으로 매핑이 필요
    public int[] solution2(String[] enroll, String[] referral, String[] seller, int[] amount) {
        final Map<String, String> sellerMap = new HashMap<>(); // 자식 -> 부모 매핑 맵
        final Map<String, List<Integer>> sellerAmount = new HashMap<>(); // 각 판매원이 직접 판매한 값 매핑
        final Map<String, Integer> sellerProfits = new HashMap<>();// 판매원들의 총 이익 계산 매핑
        List<Integer> answer = new ArrayList<>();

        // enroll, referral을 입력받아서 자식 -> 부모 매핑관계를 표현한다.
        for (int i = 0; i < enroll.length; i++) {
            sellerMap.put(enroll[i], referral[i]);
        }

        // 각 판매원들의 판매금액을 이름 - 판매금액으로 매핑(이름으로 판매금액 찾기 위해)
        for (int i = 0; i < seller.length; i++) {
            // 이미 있는 이름이면 리스트에 더해준다.
            List<Integer> alreadyAmounts = sellerAmount.getOrDefault(seller[i], new ArrayList<>());
            alreadyAmounts.add(amount[i] * 100);
            sellerAmount.put(seller[i], alreadyAmounts);
        }

        // sellerAmount를 순회하면서 각 판매원이 판매한 금액이 발생할 때 마다 계산
        for (String sellerName : sellerAmount.keySet()) {
            List<Integer> amounts = sellerAmount.get(sellerName);
            for (int nowAmount : amounts) {
                String nowSeller = sellerName;
                int nowProfit = nowAmount;
                // 루트 부모가 나올때까지 반복해서 부모들의 이익을 계산
                while (true) {
                    int parentProfit = (int) (nowProfit * 0.1);
                    int nowAmountProfit = nowProfit - parentProfit;
                    // 현재 이익 계산
                    sellerProfits.put(nowSeller, sellerProfits.getOrDefault(nowSeller, 0) + nowAmountProfit);
                    // [부모 상납 종료 조건]
                    // 루트를 만나면 계산 종료
                    // 상납할 금액이 10원 미만인 경우 => 9원이면 부모한테 0.9원 상납 => 0원됨
                    if (sellerMap.get(nowSeller).equals("-") || nowProfit < 10) {
                        break;
                    }
                    nowSeller = sellerMap.get(nowSeller);
                    nowProfit = parentProfit;
                }
            }
        }

        // 마지막 정답 처리
        for (int i = 0; i < enroll.length; i++) {
            answer.add(sellerProfits.getOrDefault(enroll[i], 0));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }


}
