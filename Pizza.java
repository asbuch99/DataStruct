Size.java:

// enumeration Size
public enum Size {
        SMALL("small"),MEDIUM("medium"),LARGE("large"),EXTRALARGE("extra-large");

        public final String label;

        /**
         * Constructor
         * @param label
         */
        private Size(String label) {
            this.label = label;
        }
        /**
         * @return label
         */
        public String getLabel() {
                return label;
        }
}
ToppingType.java:

/**
 * ToppingType enumeration
 */
public enum ToppingType {
        MUSHROOMS("mushrooms"),SAUSAGE("sausage"),ANCHOVIES("anchovies"),
        SPINACH("spinach"),RICOTTACHESSE("ricotta cheese"),GREENOLIVES("green olives"),
        ONIONS("onions"),MEATBALL("meatball"),TUNA("tuna"),
        PINEAPPLE("pineapple"),CHICKEN("chicken"),SUNDRIEDTOMATOES("sun dried tomatoes"),
        GREENPEPPERS("green peppers"),ARTICHOKES("artichokes"),FETA("feta"),
        BANANAPEPPERS("banana peppers"),STEAK("steak"),STEWEDTOMATOES("stewed tomatoes"),
        ROASTEDPEPPERS("roasted peppers"),TOMATOTES("tomatoes"),EGGPLANT("egg plant"),
        BLACKOLIVES("black olives"),HAM("ham"),GARLIC("garlic"),
        PEPPERONI("pepperoni"),BACON("bacon"),JALAPENO("jalapeno"),
        DOUBLECHEESE("double cheese"),BROCCOLI("broccoli"),KALAMATAOLIVES("kalamata olives");
        
        public final String label;

        /**
         * Constructor
         * @param label
         */
        private ToppingType(String label) {
            this.label = label;
        }
        
        /**
         * @return the label
         */
        public String getLabel() {
                return label;
        }
}
Topping.java:

/**
 * Topping class
 */
public class Topping {
        // variables of Topping class
        private ToppingType toppingType;
        private boolean half;

        /**
         * constructor
         * 
         * @param toppingType
         * @param half
         */
        public Topping(ToppingType toppingType, boolean half) {
                this.toppingType = toppingType;
                this.half = half;
        }

        /**
         * @return the toppingType
         */
        public ToppingType getToppingType() {
                return toppingType;
        }

        /**
         * @return the half
         */
        public boolean isHalf() {
                return half;
        }

        @Override
        public String toString() {
                // print the label of the toppingType
                String result = toppingType.getLabel();
                // check if the half in true
                if (half == true) {
                        result += " 1/2";
                }
                return result;
        }

}
Pizza.java:

public class Pizza {
        // variables
        private Size size;
        private int numToppings;
        private Topping[] toppings = new Topping[numToppings];

        /**
         * Constructor
         * 
         * @param size
         * @param numToppings
         * @param toppings
         */
        public Pizza(Size size, int numToppings, Topping[] toppings) {
                this.size = size;
                this.numToppings = numToppings;
                this.toppings = toppings;
        }

        /**
         * 
         * @return the topping value
         */
        public double getToppingCount() {
                // declare an array to hold double toppings
                ToppingType[] doubleToppings = { ToppingType.RICOTTACHESSE, ToppingType.MEATBALL, ToppingType.CHICKEN,
                                ToppingType.SUNDRIEDTOMATOES, ToppingType.ARTICHOKES, ToppingType.FETA, ToppingType.STEAK,
                                ToppingType.EGGPLANT, ToppingType.GARLIC, ToppingType.KALAMATAOLIVES };

                double toppingsVal = 0;

                // iterate through the toppings array
                for (int index = 0; index < toppings.length; index++) {
                        double current = 0;
                        // check if the half
                        if (toppings[index].isHalf()) {
                                current = 0.5;
                        } else {
                                current = 1;
                        }

                        // check if the topping is tuna, triple the value
                        if (toppings[index].getToppingType().equals(ToppingType.TUNA)) {
                                current *= 3;
                        } else {
                                // iterate through the double toppings array
                                for (int i = 0; i < doubleToppings.length; i++) {
                                        if (toppings[index].getToppingType().equals(doubleToppings[i])) {
                                                // double the value
                                                current *= 2;
                                                break;
                                        }
                                }
                        }
                        // add the current value to the total topping value
                        toppingsVal += current;
                }
                return toppingsVal;

        }

        /**
         * 
         * @return the price of the pizza
         */
        public double getPrice() {
                double price = 0;
                // declare a price based on the table
                double[][] priceArray = { { 12.25, 15.3, 18.4, 24.6 }, { 13.15, 16.35, 19.6, 25.95 },
                                { 14.05, 17.4, 20.8, 27.3 }, { 14.95, 18.45, 22.00, 28.65 }, { 15.85, 19.5, 23.2, 30.00 },
                                { 16.75, 20.55, 24.4, 31.35 }, { 17.65, 21.6, 25.6, 32.7 }, { 18.55, 22.65, 26.8, 34.05 },
                                { 19.45, 23.7, 28, 35.4 } };
                // get topping count
                double toppingCount = getToppingCount();
                String toppingCountStr = String.valueOf(toppingCount);
                // check the vlaue of the topping and size calculate the price
                switch (toppingCountStr) {
                case "0.0":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[0][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[0][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[0][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[0][3];
                        }
                        break;
                case "0.5":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[1][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[1][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[1][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[1][3];
                        }
                        break;
                case "1.0":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[2][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[2][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[2][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[2][3];
                        }
                        break;
                case "1.5":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[3][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[3][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[3][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[3][3];
                        }
                        break;
                case "2.0":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[4][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[4][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[4][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[4][3];
                        }
                        break;
                case "2.5":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[5][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[5][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[5][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[5][3];
                        }
                        break;
                case "3.0":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[6][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[6][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[6][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[6][3];
                        }
                        break;
                case "3.5":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[7][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[7][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[7][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[7][3];
                        }
                        break;
                case "4.0":
                        if (size.equals(Size.SMALL)) {
                                price += priceArray[8][0];
                        } else if (size.equals(Size.MEDIUM)) {
                                price += priceArray[8][1];
                        } else if (size.equals(Size.LARGE)) {
                                price += priceArray[8][2];
                        } else if (size.equals(Size.EXTRALARGE)) {
                                price += priceArray[8][3];
                        }
                        break;
                }
                // return thr pizza price
                return price;
        }

        @Override
        public String toString() {
                String result = size.getLabel() + " : ";
                // if the plain pizza
                if (numToppings == 0) {
                        result += "plain";
                } else {
                        // iterate through the toppings array
                        for (int topping = 0; topping < toppings.length; topping++) {
                                if (topping != toppings.length - 1) {
                                        result += toppings[topping].toString() + ", ";
                                } else {
                                        result += toppings[topping].toString();
                                }
                        }
                }

                return result;
        }

}
PizzaMain.java:

public class PizzaMain {
        public static void main(String[] args) {
                Pizza pizza1 = new Pizza(Size.SMALL,0, new Topping[] {});
                Pizza pizza2 = new Pizza(Size.EXTRALARGE,4, new Topping[] {
                                new Topping(ToppingType.PEPPERONI, false),
                                new Topping(ToppingType.SAUSAGE, true),
                                new Topping(ToppingType.ONIONS, true),
                                new Topping(ToppingType.TUNA, true)});
                Pizza pizza3 = new Pizza(Size.LARGE,8, new Topping[] {
                                new Topping(ToppingType.PINEAPPLE, true),
                                new Topping(ToppingType.SPINACH, true),
                                new Topping(ToppingType.ONIONS, true),
                                new Topping(ToppingType.JALAPENO, true),
                                new Topping(ToppingType.BACON, true),
                                new Topping(ToppingType.GREENPEPPERS, true),
                                new Topping(ToppingType.ANCHOVIES, true),
                                new Topping(ToppingType.BLACKOLIVES, true)});
                Pizza[] order = {pizza1, pizza2, pizza3};
                double total = 0;
                for(Pizza m: order) {
                        System.out.printf("%s\nPrice = %5.2f\n\n",m.toString(),m.getPrice());
                        total += m.getPrice();
                }
                
                System.out.printf("Total price: %7.2f\n",total);
        }
}