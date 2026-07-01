import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/storefront.css';

const StorefrontHeader: React.FC = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  return (
    <nav>
      {/* NAV TOP */}
      <div className="nav-top">
        <div className="nav-top-content nav-top-left">
          <ul className="main-menu">
            <li>
              <span>English</span> <i className="ri-arrow-down-s-line"></i>
              <ul className="sub-menu">
                <li>
                  <span>English</span>
                </li>
                <li>
                  <span>Spanish</span>
                </li>
              </ul>
            </li>
          </ul>
          <ul className="main-menu">
            <li>
              <span>USD</span> <i className="ri-arrow-down-s-line"></i>
              <ul className="sub-menu">
                <li>
                  <span>USD</span>
                </li>
                <li>
                  <span>COP</span>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div className="nav-top-content nav-top-right">
          <ul className="main-menu">
            <li>
              <span>Entregas gratis en compras sobre $100</span>
            </li>
            <li>
              <a href="#">Flash Sale</a>
            </li>
            <li>
              <Link to="/about">Nosotros</Link>
            </li>
            <li>
              <Link to="/contact">Contacto</Link>
            </li>
          </ul>
        </div>
      </div>

      {/* NAV MIDDLE */}
      <div className="nav-middel">
        <div className="logo">
          <Link to="/">
            <h2>
              <span>AB</span>Commerce
            </h2>
          </Link>
        </div>

        <div className="nav-middel-input-box">
          <select>
            <option>Categorías</option>
            <option>Cámaras</option>
            <option>Accesorios</option>
            <option>Laptops</option>
          </select>
          <input type="text" placeholder="Buscar productos..." />
          <button className="btn">
            <a href="#">Buscar</a>
          </button>
        </div>

        <div className="nav-middel-right">
          <div className="nav-middel-right-content">
            <div className="nav-middel-right-icon">
              <i className="ri-phone-line"></i>
            </div>
            <div className="nav-middel-right-info">
              <span>¿Tiene preguntas?</span>
              <span>+09 9987 7931</span>
            </div>
          </div>

          <div className="nav-middel-right-content">
            <div className="nav-middel-right-icon">
              <i className="ri-user-line"></i>
            </div>
            <div className="nav-middel-right-info">
              <span>Cuenta</span>
              <span>Ingresar</span>
            </div>
          </div>

          <div className="nav-middel-right-content">
            <div className="nav-middel-right-icon">
              <i className="ri-shopping-cart-line"></i>
            </div>
            <div className="nav-middel-right-info">
              <span>Mi</span>
              <span>Carrito</span>
            </div>
          </div>

          <div className="nav-middel-right-content" onClick={() => setIsMobileMenuOpen(true)}>
            <div className="nav-middel-right-icon">
              <i id="open-menu" className="ri-menu-line"></i>
            </div>
          </div>
        </div>
      </div>

      {/* NAV BOTTOM */}
      <div className={`nav-bottom ${isMobileMenuOpen ? 'active' : ''}`}>
        <i id="close-menu" className="ri-close-line" onClick={() => setIsMobileMenuOpen(false)}></i>

        <div className="nav-bottom-left">
          <div className="categories">
            <div className="categories-text">
              <i className="ri-menu-2-line"></i>
              <span>Categorías</span>
            </div>
            <i className="ri-arrow-down-s-line"></i>
          </div>
          <ul className="categories-item-menu">
            <li>
              <a href="#">Celulares</a>
            </li>
            <li>
              <a href="#">Laptops</a>
            </li>
            <li>
              <a href="#">Cámaras</a>
            </li>
            <li>
              <a href="#">Audífonos</a>
            </li>
            <li>
              <a href="#">PC Gaming</a>
            </li>
            <li>
              <a href="#">Tablets</a>
            </li>
            <li>
              <a href="#">Televisiones</a>
            </li>
          </ul>
        </div>

        <ul className="nav-bottom-menu">
          <li>
            <Link to="/">Inicio</Link>
          </li>
          <li>
            <Link to="/products/laptops">Laptops</Link>
          </li>
          <li>
            <Link to="/products/phones">Celulares</Link>
          </li>
          <li>
            <Link to="/products/headphones">Audífonos</Link>
          </li>
          <li>
            <Link to="/products/cameras">Cámaras</Link>
          </li>
        </ul>

        <div className="vislist">
          <i className="ri-heart-line"></i>
          <i className="ri-shopping-cart-line"></i>
        </div>

        <div className="today-deals">
          <span>Promoción de hoy</span>
          <span>Hot</span>
        </div>
      </div>
    </nav>
  );
};

export default StorefrontHeader;
