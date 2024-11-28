-- problem url = "https://www.hackerrank.com/challenges/placements/problem"

-- Write a query to_2023 output the names of those students whose best friends got offered a higher salary than them.
-- Names must be ordered by the salary amount offered to_2023 the best friends.
-- It is guaranteed that no two students got same salary offer.

SELECT S.name
FROM Students S
    JOIN Packages S_P ON S.ID = S_P.ID
    JOIN Friends F ON S.ID = F.ID
    JOIN Packages F_P ON F.Friend_ID = F_P.ID
WHERE S_P.salary < F_P.salary
ORDER BY F_P.salary ASC