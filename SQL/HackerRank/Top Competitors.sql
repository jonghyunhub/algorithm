-- problem url = "https://www.hackerrank.com/challenges/full-score/problem?h_r=profile"
-- 해결 과정 = "https://velog.io/@jonghyun3668/SQL-%EB%AC%B8%EC%A0%9C%ED%92%80%EC%9D%B4-HackerRank-Top-Competitors"

SELECT Ha.hacker_id, Ha.name
FROM Submissions Sub
    JOIN Hackers Ha ON Sub.hacker_id = Ha.hacker_id
    JOIN Challenges Ch ON Sub.challenge_id = Ch.challenge_id
    JOIN Difficulty Di ON Ch.difficulty_level = Di.difficulty_level
WHERE Sub.score = Di.score
GROUP BY Ha.hacker_id, Ha.name
HAVING COUNT(Ha.name) > 1
ORDER BY COUNT(Ha.name) DESC, Ha.hacker_id ASC