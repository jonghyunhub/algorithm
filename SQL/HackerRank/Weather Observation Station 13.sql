problem url = "https://www.hackerrank.com/challenges/weather-observation-station-13/problem?h_r=internal-search"

SELECT TRUNCATE(SUM(LAT_N),4)
FROM STATION
WHERE LAT_N > 38.7880 and LAT_N < 137.2345