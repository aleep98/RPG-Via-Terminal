package com.teste.Inimigo;


    public enum  TipoInimigos{
        GOBLIN(50, 10),
        ORC(100, 20),
        TROLL(150, 30),
        ESQUELETO(75, 15),
        ZUMBI(80, 25),
        DRAGAO(200, 40),
        DEMONIO(120, 35),
        VAMPIRO(90, 20),
        LOBISOMEM(110, 30);

        private int vida;
        private int ataque;

        TipoInimigos(int vida, int ataque) {
            this.vida = vida;
            this.ataque = ataque;
        }

        public int getVida(){
            return vida;
        }

        public int getAtaque(){
            return ataque;
        }
    
    }
    

