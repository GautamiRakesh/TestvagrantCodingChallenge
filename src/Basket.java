public class Basket {
    private String pname;
    private double uprice;
    private double gst;
    private int quant;

    public Basket(String n, double up, double gstPer, int q) {
        this.pname = n;
        this.uprice = up;
        this.gst = gstPer;
        this.quant = q;
    }
    public double calPrice() {
        Double a=uprice * quant * (1 + gst / 100);
        return a;
    }

    public double calGst() {
        Double b=uprice * quant * (gst / 100);
        return b;
    }

    public double calDis() {
        if (uprice >= 500) {
            return calPrice() * 0.95;
        } else {
            return calPrice();
        }
    }

    public String getName() {
        return pname;
    }

    public static Basket findMaxGst(Basket[] basket) {
        Basket maxGstP = basket[0];
        double maxGstAmount = maxGstP.calGst();

        for (int i = 0; i < basket.length; i++) {
            double currentGstAmount = basket[i].calGst();
            if (currentGstAmount > maxGstAmount) {
                maxGstAmount = currentGstAmount;
                maxGstP = basket[i];
            }
        }

        return maxGstP;
    }

    public static void main(String[] args) {
        // Create products
        Basket[] products = {
                new Basket("Wallet", 1100, 18, 1),
                new Basket("Umbrella", 900, 12, 4),
                new Basket("Cigarette", 200, 28, 3),
                new Basket("Honey", 100, 0, 2)
        };

        Basket mGstProduct = findMaxGst(products);
        double totalAmount = 0.0;
        for (Basket product : products) {
            totalAmount += product.calDis();
        }
        System.out.println("Product with maximum GST: " + mGstProduct.getName());
        System.out.printf("Total: %.2f\n", totalAmount);
    }
}
