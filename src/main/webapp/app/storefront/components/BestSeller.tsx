import React, { useEffect, useState } from 'react';
import { IProduct, getBestSellers } from '../services/product.service';
import ProductCard from './ProductCard';

const BestSeller: React.FC = () => {
  const [products, setProducts] = useState<IProduct[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        setLoading(true);
        const data = await getBestSellers(8);
        console.warn('Productos recibidos:', data);
        setProducts(data);
      } catch (err) {
        setError('Error al cargar los productos');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  return (
    <section className="best-seller">
      <div className="heading">
        <h2>Mas vendidos</h2>
        <div className="side-links">
          <a href="#">Celulares</a>
          <a href="#">Laptops</a>
          <a href="#">Iwatchs</a>
          <a href="#">Accesorios</a>
        </div>
      </div>

      {loading && <p>Cargando productos...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {!loading && !error && (
        <div className="card-container">
          {products.length > 0 ? (
            products.map(product => <ProductCard key={product.id} product={product} />)
          ) : (
            <p>No hay productos disponibles en este momento.</p>
          )}
        </div>
      )}
    </section>
  );
};

export default BestSeller;
