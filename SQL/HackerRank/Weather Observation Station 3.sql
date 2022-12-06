problem url = "https://www.hackerrank.com/challenges/weather-observation-station-3/problem"

SELECT DISTINCT city
FROM STATION
WHERE ID % 2 = 0