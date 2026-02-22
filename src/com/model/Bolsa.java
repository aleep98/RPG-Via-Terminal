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

        item.add(new Items("Poção de Vida", TipoItem.CURA, 20));
        item.add(new Items("Pergaminho Flamejante", TipoItem.ATAQUE_TEMPORARIO, 10));
        item.add(new Items("Poção Fúria", TipoItem.ATAQUE_TEMPORARIO, 15));
    }

    public void adicionarItem(Items item) {
        if (this.item.size() < capacidade) {
            this.item.add(item);
            System.out.println(item.getNome() + " foi adicionado à bolsa.");
        } else {
            System.out.println("A bolsa está cheia!");
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