package com.constructionhelper;

import net.runelite.api.HashTable;

import java.util.Hashtable;

public class ConstructionItem {
    int id;
    String name;
    Hashtable<String, ConstructionMaterial> materials;

    Hashtable<String, ConstructionMaterial> getMaterials() {
        return materials;
    }

    public ConstructionItem(int id, String name, Hashtable<String, ConstructionMaterial> materials) {
        this.id = id;
        this.name = name;
        this.materials = materials;
    }

}
