package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.Items.TipoItem;

public class Bolsa {
    private List<Items> item;
    private int capacidade;

    public Bolsa() {
        this.capacidade = 10;
        this.item = new ArrayList<>();

        item.add(new Items("Pocao de Vida", TipoItem.CURA, 20));
        item.add(new Items("Luva de Aco", TipoItem.DANO_POR_TOQUE, 5, 1, 30, 1.25, 0.20));
        item.add(new Items("Pergaminho Flamejante", TipoItem.DANO_POR_SEGUNDO, 3, 1, 36, 1.27, 0.18));
        item.add(new Items("Anel da Fortuna", TipoItem.OURO_BONUS, 0.05, 1, 40, 1.30, 0.14));
    }

    public boolean adicionarItem(Items item) {
        if (this.item.size() < capacidade) {
            this.item.add(item);
            System.out.println(item.getNome() + " foi adicionado a bolsa.");
            return true;
        } else {
            System.out.println("A bolsa esta cheia!");
            return false;
        }
    }

    public void listarItems() {
        System.out.println("Itens na bolsa:");
        for (int i = 0; i < item.size(); i++) {
            System.out.println(i + " - " + item.get(i).getNome());
        }
    }

    public List<Items> getItems() {
        return this.item;
    }

    public void removerItem(Items item) {
        this.item.remove(item);
        System.out.println(item.getNome() + " foi removido da bolsa.");
    }
}
