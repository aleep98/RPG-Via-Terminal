package com.teste.Inimigo;


    public enum  TipoInimigos{
        GOBLIN(50, 10),
        ORC(100, 20),
        TROLL(100, 30),
        ESQUELETO(50, 15),
        ZUMBI(60, 25),
        DRAGAO(100, 40),
        DEMONIO(100, 35),
        VAMPIRO(100, 20),
        LOBISOMEM(100, 30);

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
    

