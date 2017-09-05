package com.pservice.utilities;

import com.pservice.Constants;
//import com.pservice.model.Merchant;
//import com.pservice.model.Product;
//import com.pservice.model.ProductMerchant;
//import com.pservice.model.ProductTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.coviam.model.*;


/**
 * Created by coviam on 10/08/17.
 */
public class Utils {
//    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
//        return map.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (e1, e2) -> e1,
//                        LinkedHashMap::new
//                ));
//    }


    public static <K, V> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Iterator<Map.Entry<K, V>> it = list.iterator(); it.hasNext();) {
            Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static List<ProductTable> getProductList(Map<ProductTable,Double> productDoubleMap) {
        Set set = productDoubleMap.entrySet();
        // Get an iterator
        Iterator i = set.iterator();

        // Display elements
        List<ProductTable> productTableList =new ArrayList<ProductTable>();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            ProductTable productTable =(ProductTable)me.getKey();
            productTableList.add(productTable);
        }
        return productTableList;
    }

//    public static ProductTable getBestProduct(Map<ProductTable,Double> productDoubleMap){
//        Set set = productDoubleMap.entrySet();
//        Iterator i = set.iterator();
//        Map.Entry me = (Map.Entry) i.next();
//        ProductTable productTable =(ProductTable)me.getKey();
//        return productTable;
//    }

    public static Product setProduct(ProductTable productTable, ProductMerchant productMerchant,Merchant merchant){
        Product product=new Product(productTable.getId(),productTable.getProductId(),productTable.getProductName(),productTable.getProductDescription(),productTable.getProductCategory());
        product.setMerchantId(productMerchant.getMerchantId());
        product.setProductPrice(productMerchant.getProductPrice());
        product.setProductStock(productMerchant.getProductStock());
        product.setMerchantName(merchant.getMerchantName());
        return product;
    }

    public String generateRandomString(){
        Random r = new Random(); // just create one and keep it around
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";

        final int N = 8;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        String randomName = sb.toString();
            return randomName;
    }


    public static Double configFactor(Float merchantRating,Double productPrice, Integer productQuantity ){
        Double factor=(Constants.MERCHANT_RATING_WEIGHTAGE*merchantRating + Constants.STOCK_WEIGHTAGE* productQuantity)/(10* productPrice);
        factor = BigDecimal.valueOf(factor).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return factor;
    }

}
