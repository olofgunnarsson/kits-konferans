package se.kits.clothing;

import java.util.Random;

public class RandomClothing {
    private static final String[] CLOTHING = {
            "Boxers",
            "Tights",
            "Poncho",
            "Sarong",
            "Blazer",
            "Fleece",
            "Pajamas",
            "Kilt",
            "Tracksuit",
            "Socks",
            "Gown",
            "Swimwear",
            "Lingerie",
            "Suit",
            "Shoes",
            "Shawl",
            "Waistcoat",
            "Blouse",
            "Shirt",
            "Hat",
            "Robe",
            "T-Shirt",
            "Bra",
            "Jeans",
            "Hoody",
            "Boots",
            "Skirt",
            "Bow Tie",
            "Dinner Jacket",
            "Coat",
            "Top",
            "Belt",
            "Dress",
            "Cufflinks",
            "Shorts",
            "Camisole",
            "Underwear",
            "Nightgown",
            "Polo Shirt",
            "Overalls",
            "Sweatshirt",
            "Cargos",
            "Sunglasses",
            "Knickers",
            "Tie",
            "Briefs",
            "Gloves",
            "Slippers",
            "Bikini",
            "Cardigan",
            "Corset",
            "Swimming Shorts",
            "Tankini",
            "Stockings",
            "Scarf",
            "Jacket",
            "Jogging Suit",
            "Cummerbund",
            "Thong",
            "Cravat",
            "Sandals"
    };

    private static final String[] COLORS = {
            "Blue", "Red", "Black", "White", "Green"
    };

    public static Clothing getRandomCloth() {
        final Random random = new Random();
        return new Clothing(CLOTHING[random.nextInt(CLOTHING.length)], COLORS[random.nextInt(COLORS.length)]);
    }
}
