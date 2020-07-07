package com.example.tetris;
import org.junit.Assert;
import org.junit.Test;
public class VelocidadPiezaTest {
    @Test
    public void TestVelocidad()
    {
        VelocidadPieza velocidad = new VelocidadPieza();
        Assert.assertEquals(true, velocidad.esVelocidadValida(1));
        //Assert.assertEquals(true, selectorVelocidad.esVelocidadValida(10));
    }
}
