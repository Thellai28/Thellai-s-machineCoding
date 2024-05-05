package controller;

import model.Product;
import service.UserIoService;
import service.VendingMachineService;

import java.util.List;

public class VendingMachineController {

    public static void showAndDispenseProduct(){
        List<Product> availableProducts = VendingMachineService.fetchAllAvailableProducts();
        if( availableProducts.size() > 0 ){
            System.out.println("📃📃Please select a product from the below list");
            int chosenProduct = UserIoService.displayProductListAndGetResponse(availableProducts);

            if( chosenProduct > 0 && chosenProduct < availableProducts.size() ){
                Product product = availableProducts.get(chosenProduct);
                int quantity = UserIoService.getQuantityDetails();

                while( quantity <= 0 || quantity > product.getQuantity() ){
                    System.out.println("‼️❌ invalid input : Please enter a valid quantity between 1 & "
                            + product.getQuantity());
                    System.out.println();
                    quantity = UserIoService.getQuantityDetails();
                }

                product.setQuantity( product.getQuantity() - quantity );
                float payableAmount = calculatePrice(product.getPrice(), quantity);
                receivePaymentAndReturnChange(payableAmount);
                UserIoService.printHorizontalLines();

            }else{
                System.out.println("‼️❌ invalid input : Choose the product between 1 & " + availableProducts.size() );
                UserIoService.printHorizontalLines();
                showAndDispenseProduct();// recursive call :
            }
        }else {
            System.out.println("☹️☹️ Sorry, no product is available in vending machine currently");
            UserIoService.printHorizontalLines();
        }
    }

    private static float calculatePrice( float productPrice, int quantity ){
        final float GST_PERCENTAGE = 7.5f;
        float price = productPrice * quantity;
        float gstAmount = (price * GST_PERCENTAGE) / 100;
        return price + gstAmount;
    }

    private static void receivePaymentAndReturnChange( float payableAmount ){
        float amountFromUser = UserIoService.getPaymentFromUser( payableAmount );

        while( amountFromUser < payableAmount ){
            System.out.println("‼️❌ Insufficient Fund : The amount entered is lesser than the actual amount");
            amountFromUser = UserIoService.getPaymentFromUser( payableAmount );
        }
        float remainingAmount = amountFromUser - payableAmount;

        if( remainingAmount > 0.0f){
            String formattedString = String.format("🎉🎉 Your order is placed, " +
                    "Here is your change : %.2f💲 , Thank you visit again 😃😃", remainingAmount);
            System.out.println(formattedString);
        }else{
            System.out.println("🎉🎉 Your order is placed, Thanks you visit again 😃😃");
        }
    }


    public static void receiveReturnedProductAndReturnChange(){
        List<Product> allProductsList = VendingMachineService.getAllProductsFromVendingMachine();
        int chosenProduct = UserIoService.displayProductListAndGetResponse(allProductsList);

        if( chosenProduct > 0 && chosenProduct < allProductsList.size() ){
            Product product = allProductsList.get(chosenProduct);
            int returnedQuantity = UserIoService.getQuantityDetails();

            product.setQuantity(product.getQuantity() + returnedQuantity );
            float refundableAmount = calculatePrice(product.getPrice(), returnedQuantity); // Reusing the code:

            String formattedString = String.format("🎉🎉your return request is successful," +
                    " please collect the cash %.2f 💲", refundableAmount);

            System.out.println(formattedString);
            UserIoService.printHorizontalLines();

            UserIoService.displayProducts(VendingMachineService.getAllProductsFromVendingMachine());
            UserIoService.printHorizontalLines();

        }else{
            System.out.println("‼️❌ invalid input : please choose a valid Product");
            UserIoService.printHorizontalLines();
            receiveReturnedProductAndReturnChange();
        }
    }

    public static void addNewItemIntoVendingMachine(){
        String name = UserIoService.getProductName();
        float price = UserIoService.getProductPrice();
        int quantity = UserIoService.getQuantityDetails();

        Product newProduct = new Product(name, price, quantity);
        VendingMachineService.addProductIntoProductList(newProduct);

        System.out.println("🎉🎉 Product successfully added into the vending machine ");
        UserIoService.printHorizontalLines();

        UserIoService.displayProducts(VendingMachineService.getAllProductsFromVendingMachine());
        UserIoService.printHorizontalLines();
    }

}
