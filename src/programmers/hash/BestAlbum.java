package programmers.hash;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/42579#
public class BestAlbum {

    public static void main(String[] args) {
        BestAlbum bestAlbum = new BestAlbum();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] result = bestAlbum.solution(genres, plays);
        for (int musicId : result) {
            System.out.println(musicId);
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        final Map<String, Integer> genreToPalys = new HashMap<>(); // 장르 음악 전체 조회수 Map
        final Map<String, List<Music>> top2MusicsMap = new HashMap<>(); // 장르 내부 top2 음악
        final List<Integer> musicIds = new ArrayList<>(); // 결과 musicId 담을 리스트

        for (int musicId = 0; musicId < genres.length; musicId++) {
            // 장르별 음악 재생수 집계
            String genre = genres[musicId];
            int playCnt = plays[musicId];
            Integer genrePlayCnt = genreToPalys.getOrDefault(genre, 0);
            genreToPalys.put(genre, genrePlayCnt + playCnt);

            // 장르별 top2 음악 집계
            // 리스트에 내림차순 정렬해서 담고 나중에 앞에 두개만 꺼내기
            List<Music> top2Musics = top2MusicsMap.getOrDefault(genre, new ArrayList<>());
            top2Musics.add(new Music(musicId, playCnt));
            Collections.sort(top2Musics); // 오름차순 정렬
            top2MusicsMap.put(genre, top2Musics); // 처음 담는 경우도 커버를 위해 put
        }

        // 장르 음악별 내림차순 정렬
        final List<String> genresByPlays = genreToPalys.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue()) // 내림차순
                .map(map -> map.getKey())
                .collect(Collectors.toList()); // 프로그래머스 자바는 11까지 지원해서 .toList() 사용못함

        // 장르별 top2 음악의 id를 musicIds에 담는다.
        for (String genre : genresByPlays) {
            List<Music> top2Musics = top2MusicsMap.get(genre);
            int lastIdx = 1;
            if (top2Musics.size() == 1) lastIdx = 0; // 음악의 갯수가 1개인 경우 처리
            for (int i = 0; i <= lastIdx; i++) {
                Integer musicId = top2Musics.get(i).index;
                musicIds.add(musicId);
            }
        }

        return musicIds.stream().mapToInt(i -> i).toArray();
    }

    static class Music implements Comparable<Music> {
        int index;
        int playCnt;

        public Music(int index, int playCnt) {
            this.index = index;
            this.playCnt = playCnt;
        }

        // 재생수 내림차순 정리
        public int compareTo(Music other) {
            return other.playCnt - this.playCnt;
        }
    }
}
