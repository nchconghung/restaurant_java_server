package server.DAO;

import server.Models.ProductModel;

public interface ProductRepositoryCustom {
	ProductModel create(String imgUrl, String name, int price, String category, String describe);
	ProductModel update(String imgUrl, String name, int price, String category, String describe, String id);
}
