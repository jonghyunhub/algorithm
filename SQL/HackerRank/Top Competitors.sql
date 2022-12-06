SELECT Ha.hacker_id, Ha.name
FROM Submissions Sub
    JOIN Hackers Ha ON Sub.hacker_id = Ha.hacker_id
    JOIN Challenges Ch ON Sub.challenge_id = Ch.challenge_id
    JOIN Difficulty Di ON Ch.difficulty_level = Di.difficulty_level
WHERE Sub.score = Di.score
GROUP BY Ha.hacker_id, Ha.name
HAVING COUNT(Ha.name) > 1
ORDER BY COUNT(Ha.name) DESC, Ha.hacker_id ASC