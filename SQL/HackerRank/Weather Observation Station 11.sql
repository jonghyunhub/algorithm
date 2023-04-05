-- problem url = "https://www.hackerrank.com/challenges/weather-observation-station-11/problem?h_r=internal-search"
-- 해결 과정 = "https://velog.io/@jonghyun3668/SQL-%EB%AC%B8%EC%A0%9C%ED%92%80%EC%9D%B4-HackerRank-Weather-Observation-Station-11"


SELECT DISTINCT CITY
FROM STATION
WHERE 
    LEFT(CITY,1) NOT IN ('A', 'E', 'I', 'O', 'U') 
    OR RIGHT(CITY,1)NOT IN ('A', 'E', 'I', 'O', 'U')