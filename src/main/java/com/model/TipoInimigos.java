package com.model;


    public enum  TipoInimigos{
        GOBLIN(55, 8, 18, false),
        ORC(90, 12, 26, false),
        TROLL(130, 14, 32, false),
        ESQUELETO(70, 10, 20, false),
        ZUMBI(85, 11, 24, false),
        BOSS_DRAGAO(240, 20, 85, true),
        DEMONIO(150, 15, 38, false),
        VAMPIRO(135, 13, 34, false),
        LOBISOMEM(145, 14, 36, false);

        private int vidaBase;
        private int ataqueBase;
        private int ouroBase;
        private boolean boss;

        TipoInimigos(int vidaBase, int ataqueBase, int ouroBase, boolean boss) {
            this.vidaBase = vidaBase;
            this.ataqueBase = ataqueBase;
            this.ouroBase = ouroBase;
            this.boss = boss;
        }

        public int getVidaBase(){
            return vidaBase;
        }

        public int getAtaqueBase(){
            return ataqueBase;
        }

        public int getOuroBase() {
            return ouroBase;
        }

        public boolean isBoss() {
            return boss;
        }

        public int calcularVida(int estagio, int indiceDaFase) {
            double escalaEstagio = Math.pow(1.16, Math.max(0, estagio - 1));
            double escalaFase = 1.0 + (Math.max(1, indiceDaFase) - 1) * 0.05;
            double bonusBoss = boss ? 1.9 : 1.0;
            return (int) Math.round(vidaBase * escalaEstagio * escalaFase * bonusBoss);
        }

        public int calcularAtaque(int estagio, int indiceDaFase) {
            double escalaEstagio = Math.pow(1.08, Math.max(0, estagio - 1));
            double escalaFase = 1.0 + (Math.max(1, indiceDaFase) - 1) * 0.03;
            double bonusBoss = boss ? 1.25 : 1.0;
            return (int) Math.round(ataqueBase * escalaEstagio * escalaFase * bonusBoss);
        }

        public int calcularOuro(int estagio) {
            double escala = Math.pow(1.18, Math.max(0, estagio - 1));
            double bonusBoss = boss ? 3.0 : 1.0;
            return (int) Math.round(ouroBase * escala * bonusBoss);
        }

        public int calcularXp(int estagio) {
            double bonusBoss = boss ? 2.5 : 1.0;
            return (int) Math.round((20 + estagio * 5) * bonusBoss);
        }
    
    }
    

