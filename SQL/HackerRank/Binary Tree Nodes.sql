-- problem url = "https://www.hackerrank.com/challenges/binary-search-tree-1/problem"
-- 문제 해결 = "https://velog.io/@jonghyun3668/SQL-%EB%AC%B8%EC%A0%9C%ED%92%80%EC%9D%B4-HackerRank-Binary-Tree-Nodes"

SELECT DISTINCT NOW.N,
    CASE 
        WHEN NOW.P IS NULL THEN 'Root'
        WHEN CHILD.P IS NULL THEN 'Leaf'
        ELSE 'Inner'
    END
FROM BST NOW
    LEFT JOIN BST CHILD ON NOW.N = CHILD.P
    LEFT JOIN BST PARENT ON NOW.P = PARENT.N
ORDER BY NOW.N