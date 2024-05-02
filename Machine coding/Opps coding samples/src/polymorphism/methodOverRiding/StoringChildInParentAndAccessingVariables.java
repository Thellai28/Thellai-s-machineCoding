package polymorphism.methodOverRiding;

public class StoringChildInParentAndAccessingVariables {
    static class oldGenerationTelevision{
        final String famousBrands = "Onida";

        public void displayCartoon(String generation){
            System.out.println("Hi im from "+ generation + " famous cartoons are 'tom & jerry', 'timon & pumba'..");
        }
    }

    static class newGenerationTelevision extends oldGenerationTelevision{
        final String famousBrands = "Xiaomi or Redmi";

        public void displayCartoon(String generation){
            if(generation.equals("OLD")){
                super.displayCartoon("OLD");
            }else{
                System.out.println("Hi im from "+ generation + " famous cartoons are 'OGGY & OOGY', 'Chot bheem'..");
            }
        }
    }
    public static void main( String[] args ) {

        oldGenerationTelevision television = new newGenerationTelevision();
        System.out.println("Famous television brands " + television.famousBrands);
        television.displayCartoon("NEW");
        television.displayCartoon("OLD");
    }
}
