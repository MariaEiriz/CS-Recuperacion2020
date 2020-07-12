package com.example.tetris;
import org.junit.Assert;
import org.junit.Test;

public class TableroTest {
    @Test
    public void TestPuedeMoverse()
    {
        Tablero tablero = new Tablero();
        Assert.assertEquals(true, tablero.puedeMoverse(new Pieza(1,1),1,1,false));
    }
}
