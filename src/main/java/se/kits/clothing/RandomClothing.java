package se.kits.clothing;

import java.util.Random;

public class RandomClothing {
    private static final String[] SHOES = {
            "Socks",
            "Sneakers",
            "Sandals",
            "Slippers",
            "High heels",
            "Ankle boot",
            "Steel-toe boots",
            "Wellington boots"
    };

    private static final String[] PANTS = {
            "Shorts",
            "Trousers",
            "Oxford bags",
            "Baji",
            "Bell-bottoms",
            "Bermuda shorts",
            "Blood stripe",
            "Boardshorts",
            "Bondage pants",
            "Boxer shorts",
            "Braccae",
            "Breeches",
            "Breeching",
            "Breeks",
            "Capri pants",
            "Cargo pants",
            "Chang kben",
            "Chap boot",
            "Chaps",
            "Chino cloth",
            "Churidar",
            "Codpiece",
            "Compression garment",
            "Culottes",
            "Cycling shorts",
            "Daisy Dukes",
            "Disco pants",
            "Dolphin shorts",
            "Gaiters",
            "Hammer pants",
            "Harem pants",
            "High-rise (fashion)",
            "Hip-huggers",
            "Hockey pants",
            "Jams",
            "Jeans",
            "Jinbei",
            "Jodhpurs",
            "Knickerbockers (clothing)",
            "Leather shorts",
            "Lederhosen",
            "Low-rise pants",
            "Nantucket Reds",
            "Open-crotch pants",
            "Overall",
            "Palazzo trousers",
            "Pantlessness",
            "Parachute pants",
            "Pedal pushers",
            "Pencil suit",
            "Pettipants",
            "Phat pants",
            "Plus fours",
            "Rain pants",
            "Rugby shorts",
            "Running shorts",
            "Sagging",
            "Sampot",
            "Sansabelt",
            "Shalwar kameez",
            "Sharovary",
            "Sirwal",
            "Skort",
            "Slacks",
            "Slim Jeans",
            "Slim-fit pants",
            "Snowboarding pants",
            "Sta-Prest",
            "Stirrup pants",
            "Sweatpants",
            "Tactical pants",
            "Tap pants",
            "Thai fisherman pants",
            "Three quarter pants",
            "Tobi trousers",
            "Turkish trousers",
            "Walk shorts"
    };


    private static final String[] SHIRTS = {
            "Poncho",
            "Sarong",
            "Blazer",
            "Fleece",
            "Tracksuit",
            "Blouse",
            "Robe",
            "T-Shirt",
            "Bra",
            "Hoody",
            "Dinner Jacket",
            "Coat",
            "Top",
            "Dress",
            "Polo Shirt",
            "Sweatshirt",
            "Cardigan",
            "Corset",
            "Jacket"
    };

    private static final String[] COLORS = {
            "Blue", "Red", "Black", "White", "Green"
    };

    public static Clothing getRandomShirt() {
        final Random random = new Random();
        return new Clothing(SHIRTS[random.nextInt(SHIRTS.length)], COLORS[random.nextInt(COLORS.length)]);
    }

    public static Clothing getRandomPants() {
        final Random random = new Random();
        return new Clothing(PANTS[random.nextInt(PANTS.length)], COLORS[random.nextInt(COLORS.length)]);
    }

    public static Clothing getRandomShoes() {
        final Random random = new Random();
        return new Clothing(SHOES[random.nextInt(SHOES.length)], COLORS[random.nextInt(COLORS.length)]);
    }
}
