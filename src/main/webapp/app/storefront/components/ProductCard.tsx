import React from 'react';
import { IProduct, getProductImageUrl } from '../services/product.service';

interface ProductCardProps {
  product: IProduct;
}

const ProductCard: React.FC<ProductCardProps> = ({ product }) => {
  const imageUrl = getProductImageUrl(product);

  return (
    <div className="product-card">
      <div className="wishlist">
        <i className="ri-heart-line"></i>
      </div>

      <img src={imageUrl} alt={product.productName} />

      <span className="product-title">{product.productName}</span>

      <div className="rating">
        <i className="ri-star-fill"></i>
        <i className="ri-star-fill"></i>
        <i className="ri-star-fill"></i>
        <i className="ri-star-fill"></i>
        <i className="ri-star-half-fill"></i>
      </div>

      <div className="price">
        {product.purchasePrice !== undefined && product.purchasePrice !== null && (
          <span className="lined">${product.purchasePrice.toFixed(2)}</span>
        )}
        <strong>${product.sellingPrice.toFixed(2)}</strong>
      </div>

      <button className="addtocardbtn">Añadir al carrito</button>

      <div className="hidden">
        <ul>
          <li>
            <i className="ri-eye-line"></i> Vista rápida
          </li>
          <li>
            <i className="ri-compare-line"></i> Comparar
          </li>
        </ul>
      </div>
    </div>
  );
};

export default ProductCard;
