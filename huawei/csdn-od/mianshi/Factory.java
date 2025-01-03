public class Factory {

//    public static void main(String[] args) {
//
//    }
//
//
//    class FactoryA{
//        public ProductA generateA(){
//            return new ProductA();
//        }
//    }
//
//    class FactoryB{
//
//    }
//    class FactoryC{
//
//    }
//
//    class ProductA{
//
//    }
//    class ProductB{
//
//    }
//    class ProductC{
//
//    }

    public Product generate(String type){
        if(type.equals("A")){
            return new ProductA();
        }
        if(type.equals("A")){
            return new ProductB();
        }
        else{
            return new ProductC();
        }
    }

    interface Product{

    }

    class ProductA implements Product{

    }
    class ProductB implements Product{

    }
    class ProductC implements Product{

    }
}
