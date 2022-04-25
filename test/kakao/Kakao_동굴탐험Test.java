package kakao;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class Kakao_동굴탐험Test {
    //n	path	order	result
//    9	{{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}}	{{8,5},{6,7},{4,1}}	true
//    9	{{8,1},{0,1},{1,2},{0,7},{4,7},{0,3},{7,5},{3,6}}	{{4,1},{5,2}}	true
//    9	{{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}}	{{4,1},{8,7},{6,5}}	false
    @Test
    void 테스트1(){
        int n = 9;
        int[][] path = new int[][]{{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
        int[][] order = new int[][]{{8,5},{6,7},{4,1}};
        Assertions.assertTrue(Kakao_동굴탐험.solution(n, path, order));
    }
    @Test
    void 테스트2(){
        int n = 9;
        int[][] path = new int[][]{{8,1},{0,1},{1,2},{0,7},{4,7},{0,3},{7,5},{3,6}};
        int[][] order = new int[][]{{4,1},{5,2}};
        Assertions.assertTrue(Kakao_동굴탐험.solution(n, path, order));
    }
    @Test
    void 테스트3(){
        int n = 9;
        int[][] path = new int[][]{{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
        int[][] order = new int[][]{{4,1},{8,7},{6,5}};
        Assertions.assertFalse(Kakao_동굴탐험.solution(n, path, order));
    }
}