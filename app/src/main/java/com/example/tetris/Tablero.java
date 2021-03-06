package com.example.tetris;

import android.graphics.Color;
import android.graphics.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {

    private final static int ALTURA_TABLERO = 20;
    private final static int ANCHURA_TABLERO = 10;
    private static int[][] tab = new int[ANCHURA_TABLERO][ALTURA_TABLERO];
    private final Random random = new Random();
    private ArrayList<Pieza> listaPiezas = new ArrayList<Pieza>();
    private final static int numeroPiezas = 7;
    private static int colorCuadrado = 1;
    private static int colorZPieza = 2;
    private static int colorIPieza = 3;
    private static int colorTPieza = 4;
    private static int colorSPieza = 5;
    private static int colorLPieza = 6;
    private static int colorJPieza = 7;
    private static final int[] vectorPiezas2Pos0 = new int[]{};
    private static final int[] vectorPiezas2Pos1 = new int[]{};


    private Pieza auxTroll;

    public Tablero() {
        listaPiezas.add(new Pieza(random.nextInt(numeroPiezas) + 1, 0));
        listaPiezas.add(new Pieza(random.nextInt(numeroPiezas) + 1, 0));
    }

    public void generarPieza(int altura) {
        listaPiezas.add(new Pieza(random.nextInt(numeroPiezas) + 1, altura));
    }

    public void borrarPieza() {
        listaPiezas.remove(0);
    }

    public int parseaColor(int x, int y) {
        if (tab[x][y] == 0) return Color.parseColor("#001c21");// azul oscuro fondo
        if (tab[x][y] == 1) return Color.parseColor("#f000ff"); //rosa
        if (tab[x][y] == 2) return Color.parseColor("#4deeea"); //azul claro
        if (tab[x][y] == 3) return Color.parseColor("#001eff"); //azul oscuro
        if (tab[x][y] == 4) return Color.parseColor("#74ee15"); //verde
        if (tab[x][y] == 5) return Color.parseColor("#FF8000"); //naranja
        if (tab[x][y] == 6) return Color.parseColor("#ffe700"); //amarillo
        if (tab[x][y] == 7) return Color.parseColor("#FF0000"); //rojo
        if (tab[x][y] == 8) return Color.parseColor("#acacac"); //gris pieza bloqueo
        if (tab[x][y] == 9) return Color.parseColor("#FFFFFF"); //pieza troll
        if (tab[x][y] == 10) return Color.parseColor("#FFFFFF"); //pieza troll

        return -1;
    }

    public void cambiarColores1Linea() {
        int aux = random.nextInt(numeroPiezas) + 1;
        for (int y = 19; y > 0; y--) {
            for (int x = 0; x < 10; x++) {
                if (tab[x][y] != 0 && tab[x][y] != 8 && tab[x][y] != 9 && tab[x][y] != 10) {
                    tab[x][y] = aux;
                }
            }
        }
    }

    public void cambiarColoresMultiLinea() {
        for (int y = 19; y > 0; y--) {
            for (int x = 0; x < 10; x++) {
                if (tab[x][y] != 0 && tab[x][y] != 8 && tab[x][y] != 9 && tab[x][y] != 10) {
                    tab[x][y] = random.nextInt(numeroPiezas) + 1;
                }
            }
        }
    }

    public void comerTablero(int y) {
        for (int j = 0; j < y; j++) {
            for (int x = 0; x < getAnchoTablero(); x++) {
                tab[x][j] = 8;
            }
        }
    }

    public void elDestructor(int fila) {
        ponerFila0(fila);
        for (int y = fila; y >= 1; y--) {
            for (int x = 0; x < getAnchoTablero(); x++) {
                this.tab[x][y] = this.tab[x][y - 1];
            }
        }
    }

    public void ponerFila0(int y) {
        for (int x = 0; x < 10; x++) {
            tab[x][y] = 0;
        }
    }

    public List<Integer> detectarFilas(Pieza troll) {
        int contador = 0;
        List<Integer> l = new ArrayList<>();
        for (int y = 19; y >= 0; y--) {
            contador = 0;
            for (int x = 0; x < getAnchoTablero(); x++) {
                if (tab[x][y] != 0 && tab[x][y] != 8) {
                    contador++;
                }
            }
            if (contador == 10) {
                if (troll != null) {
                    obtenerPosTroll(troll);
                    borrarPieza(troll);
                }
                l.add(y);
                elDestructor(y);
                y++;
            }
        }
        if (troll != null && auxTroll != null) ponerPieza(auxTroll);
        return l;
    }

    private void obtenerPosTroll(Pieza troll) {
        auxTroll = new Pieza(troll.id, troll.getAltura());
        auxTroll.copiarPieza(troll);
    }

    public ArrayList<Pieza> getListaPiezas() {
        return listaPiezas;
    }

    public void setListaPiezas(ArrayList<Pieza> listaPiezas) {
        this.listaPiezas = listaPiezas;
    }

    public Pieza getPieza() {
        return listaPiezas.get(0);
    }

    public void ponerPieza(Pieza pieza) {
        tab[pieza.x1][pieza.y1] = pieza.idColor;
        tab[pieza.x2][pieza.y2] = pieza.idColor;
        tab[pieza.x3][pieza.y3] = pieza.idColor;
        tab[pieza.x4][pieza.y4] = pieza.idColor;
    }

    public void borrarPieza(Pieza pieza) {
        tab[pieza.x1][pieza.y1] = 0;
        tab[pieza.x2][pieza.y2] = 0;
        tab[pieza.x3][pieza.y3] = 0;
        tab[pieza.x4][pieza.y4] = 0;
    }
    public Pieza cambiarVectorPos(Pieza p, int[] array){
        p.x1 = p.x1 + array[0];
        p.y1 = p.y1 + array[1];
        p.x2 = p.x2 + array[2];
        p.y2 = p.y2 + array[3];
        p.x3 = p.x3 + array[4];
        p.y3 = p.y3 + array[5];
        p.x4 = p.x4 + array[6];
        p.y4 = p.y4 + array[7];
        return p;
    }
    public Pieza rotarPieza2 (Pieza p){
        int[] vector = new int[0];
        if (p.pos ==0){
            vector = new int[]{1,0,0,1,-1,0,-2,1};
            p.pos = 1;
        }else if (p.pos ==1){
            vector = new int[]{-1,0,0,-1,1,0,2,-1};
            p.pos = 0;
        }
       return cambiarVectorPos(p,vector);
    }
    public Pieza rotarPieza3 (Pieza p){
        int[] vector = new int[0];
        if (p.pos ==0){
            vector = new int[]{0,0,1,-1,2,-2,3,-3};
            p.pos = 1;
        }else if (p.pos ==1){
            vector = new int[]{0,0,-1,1,-2,2,-3,3};
            p.pos = 0;
        }
        return cambiarVectorPos(p,vector);
    }
    public Pieza rotarPieza4 (Pieza p){
        int[] vector = new int[0];
        if (p.pos ==0){
            vector = new int[]{1,0,0,1,-1,2,-1,0};
            p.pos = 1;
        }else if (p.pos ==1){
            vector = new int[]{1,1,0,0,-1,-1,1,-1};
            p.pos = 2;
        }else if (p.pos == 2){
            vector = new int[]{2,1,-1,0,0,-1,0,1};
            p.pos = 3;
        }else if(p.pos == 3){
            vector = new int[]{0,-2,1,-1,2,0,0,0};
            p.pos = 0;
        }
        return cambiarVectorPos(p,vector);
    }
    public Pieza rotarPieza5 (Pieza p){
        int[] vector = new int[0];
        if (p.pos ==0){
            vector = new int[]{0,1,-1,2,-1,0,0,-1};
            p.pos = 1;
        }else if (p.pos ==1){
            vector = new int[]{0,-1,1,-2,1,0,0,1};
            p.pos = 0;
        }
        return cambiarVectorPos(p,vector);
    }
    public Pieza rotarPieza6 (Pieza p){
        int[] vector = new int[0];
        if (p.pos ==0){
            vector = new int[]{1,0,0,1,-1,0,-2,-1};
            p.pos = 1;
        }else if (p.pos ==1){
            vector = new int[]{0,2,-1,1,0,0,1,-1};
            p.pos = 2;
        }else if (p.pos == 2){
            vector = new int[]{-2,-1,-1,-2,0,-1,1,0};
            p.pos = 3;
        }else if(p.pos == 3){
            vector = new int[]{1,-1,2,0,1,1,0,2};
            p.pos = 0;
        }
        return cambiarVectorPos(p,vector);
    }
    public Pieza rotarPieza7 (Pieza p){
        int[] vector = new int[0];
        if (p.pos ==0){
            vector = new int[]{2,1,1,2,1,0,0,-1};
            p.pos = 1;
        }else if (p.pos ==1){
            vector = new int[]{-1,1,-2,0,0,0,1,-1};
            p.pos = 2;
        }else if (p.pos == 2){
            vector = new int[]{-1,0,0,-1,0,1,1,2};
            p.pos = 3;
        }else if(p.pos == 3){
            vector = new int[]{0,-2,1,-1,-1-1,-2,0};
            p.pos = 0;
        }
        return cambiarVectorPos(p,vector);
    }
    public void rotarPieza(Pieza p) {
        switch (p.id) {
            case 2:
                rotarPieza2(p);
                break;
            case 3:
                rotarPieza3(p);
                break;
            case 4:
                rotarPieza4(p);
                break;
            case 5:
                rotarPieza5(p);
                break;
            case 6:
                rotarPieza6(p);
                break;
            case 7:
                rotarPieza7(p);
                break;
            default:
                break;
        }
    }

    public void moverPiezas(Pieza pieza, char x) {
        if (pieza != null) {
            switch (x) {
                case 'i':
                    if (puedeMoverse(pieza, -1, 0, false)) {
                        borrarPieza(pieza);
                        pieza.mover(-1, 0);
                        ponerPieza(pieza);
                    }
                    break;
                case 'd':
                    if (puedeMoverse(pieza, 1, 0, false)) {
                        borrarPieza(pieza);
                        pieza.mover(1, 0);
                        ponerPieza(pieza);
                    }
                    break;
                case 'a':
                    if (puedeMoverse(pieza, 0, 1, false)) {
                        borrarPieza(pieza);
                        pieza.mover(0, 1);
                        ponerPieza(pieza);
                    }
                    break;
                default:
                    break;
            }
        }

    }

    public boolean puedeMoverse(Pieza pieza, int x, int y, boolean vengoDeRotar) {
        int n = 0;
        if (pieza == null) {
            return true;
        }
        Point xy1 = new Point(pieza.x1, pieza.y1);
        Point xy2 = new Point(pieza.x2, pieza.y2);
        Point xy3 = new Point(pieza.x3, pieza.y3);
        Point xy4 = new Point(pieza.x4, pieza.y4);

        Point aux1 = new Point(pieza.x1 + x, pieza.y1 + y);
        Point aux2 = new Point(pieza.x2 + x, pieza.y2 + y);
        Point aux3 = new Point(pieza.x3 + x, pieza.y3 + y);
        Point aux4 = new Point(pieza.x4 + x, pieza.y4 + y);

        ArrayList<Point> puntos = new ArrayList<Point>();
        puntos.add(aux1);
        puntos.add(aux2);
        puntos.add(aux3);
        puntos.add(aux4);

        for (Point a : puntos) {
            if ((a.x < ANCHURA_TABLERO && a.x >= 0 && a.y >= 0 && a.y < ALTURA_TABLERO && (tab[a.x][a.y] == 0 || tab[a.x][a.y] == 8))
                 ||((a.equals(xy1) || a.equals(xy2) || a.equals(xy3) || a.equals(xy4))&&!vengoDeRotar)) {
                n++;
            }
        }
        if (n == 4) {
            return true;
        }

        return false;
    }


    public void comprobarRotar(Pieza p) {
        Pieza aux = new Pieza(p.id, 0);

        aux.pos = p.pos;
        aux.x1 = p.x1;
        aux.y1 = p.y1;
        aux.x2 = p.x2;
        aux.y2 = p.y2;
        aux.x3 = p.x3;
        aux.y3 = p.y3;
        aux.x4 = p.x4;
        aux.y4 = p.y4;

        rotarPieza(aux);
        if (puedeMoverse(aux, 0, 0, true)) {
            rotarPieza(p);
        }
    }


    public static void setColorCuadrado(int colorCuadrado) {
        Tablero.colorCuadrado = colorCuadrado;
    }

    public static void setColorZPieza(int colorZPieza) {
        Tablero.colorZPieza = colorZPieza;
    }

    public static void setColorIPieza(int colorIPieza) {
        Tablero.colorIPieza = colorIPieza;
    }

    public static void setColorTPieza(int colorTPieza) {
        Tablero.colorTPieza = colorTPieza;
    }

    public static void setColorSPieza(int colorSPieza) {
        Tablero.colorSPieza = colorSPieza;
    }

    public static void setColorLPieza(int colorLPieza) {
        Tablero.colorLPieza = colorLPieza;
    }

    public static void setColorJPieza(int colorJPieza) {
        Tablero.colorJPieza = colorJPieza;
    }

    public static int getColorCuadrado() {
        return colorCuadrado;
    }

    public static int getColorZPieza() {
        return colorZPieza;
    }

    public static int getColorIPieza() {
        return colorIPieza;
    }

    public static int getColorTPieza() {
        return colorTPieza;
    }

    public static int getColorSPieza() {
        return colorSPieza;
    }

    public static int getColorLPieza() {
        return colorLPieza;
    }

    public static int getColorJPieza() {
        return colorJPieza;
    }
    public int getAlturaTablero() {
        return this.ALTURA_TABLERO;
    }

    public int getAnchoTablero() {
        return this.ANCHURA_TABLERO;
    }

    public static int[][] getTab() {
        return tab;
    }


}