import React from 'react';

const Banner: React.FC = () => {
  return (
    <section className="banner-wrapper">
      {/* Banner Superior Grande */}
      <div className="sale-top">
        <small>Super Oferta</small>
        <h1>Nuevos productos Precios de lanzamiento</h1>
        <button className="btn">
          <a href="#">Comprar ahora</a>
        </button>
      </div>

      {/* 4 Banners Inferiores */}
      <div className="sale-bottom">
        <div className="banner banner1">
          <small>Super oferta</small>
          <h3>Nuevos productos</h3>
          <p>Precios de lanzamiento</p>
        </div>
        <div className="banner banner2">
          <small>Super oferta</small>
          <h3>Nuevos productos</h3>
          <p>Precios de lanzamiento</p>
        </div>
        <div className="banner banner3">
          <small>Super oferta</small>
          <h3>Nuevos productos</h3>
          <p>Precios de lanzamiento</p>
        </div>
        <div className="banner banner4">
          <small>Super oferta</small>
          <h3>Nuevos productos</h3>
          <p>Precios de lanzamiento</p>
        </div>
      </div>
    </section>
  );
};

export default Banner;
