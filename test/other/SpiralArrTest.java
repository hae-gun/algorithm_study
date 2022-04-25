package other;

import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

class SpiralArrTest {

    @Test
    void 소용돌이_배열_테스트(){
        SpiralArr arr = new SpiralArr();
        Assertions.assertArrayEquals(arr.makeSpiral(4), new int[][]{{1,2,3,4},{12,13,14,5},{11,16,15,6},{10,9,8,7}});
    }
    

}